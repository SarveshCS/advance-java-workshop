$script:RealJavac = (Get-Command javac.exe -ErrorAction Stop).Source
$script:RealJava = (Get-Command java.exe -ErrorAction Stop).Source
$script:RepoRoot = Resolve-Path (Join-Path $PSScriptRoot "..")
$script:RepoLib = Join-Path $script:RepoRoot "lib\*"

function javac {
    param(
        [Parameter(ValueFromRemainingArguments = $true)]
        [string[]]$Arguments
    )

    if ($Arguments -contains "-d" -or $Arguments -contains "-cp" -or $Arguments -contains "-classpath" -or $Arguments -contains "--class-path") {
        & $script:RealJavac @Arguments
        return
    }

    $sourceFile = $Arguments | Where-Object {
        $_ -like "*.java" -and (Test-Path $_)
    } | Select-Object -First 1

    if (-not $sourceFile) {
        & $script:RealJavac @Arguments
        return
    }

    $resolvedSource = Resolve-Path $sourceFile
    $sourceFolder = Split-Path -Parent $resolvedSource
    $outFolder = Join-Path $sourceFolder "out"
    $classPath = "$outFolder;$script:RepoLib"

    New-Item -ItemType Directory -Force -Path $outFolder | Out-Null
    & $script:RealJavac -cp $classPath -d $outFolder @Arguments
}

function java {
    param(
        [Parameter(ValueFromRemainingArguments = $true)]
        [string[]]$Arguments
    )

    if ($Arguments -contains "-cp" -or $Arguments -contains "-classpath" -or $Arguments -contains "--class-path" -or $Arguments -contains "-jar") {
        & $script:RealJava @Arguments
        return
    }

    $sourceFile = $Arguments | Where-Object {
        $_ -like "*.java" -and (Test-Path $_)
    } | Select-Object -First 1

    if ($sourceFile) {
        & $script:RealJava -cp $script:RepoLib @Arguments
        return
    }

    $currentOut = Join-Path (Get-Location) "out"
    if (Test-Path $currentOut) {
        & $script:RealJava -cp "$currentOut;$script:RepoLib" @Arguments
        return
    }

    $mainClass = $Arguments | Where-Object {
        $_ -and -not $_.StartsWith("-")
    } | Select-Object -First 1

    if ($mainClass) {
        $classFile = ($mainClass -replace "\.", [System.IO.Path]::DirectorySeparatorChar) + ".class"
        $matches = Get-ChildItem -Path $script:RepoRoot -Directory -Filter "out" -Recurse -ErrorAction SilentlyContinue |
            Where-Object { Test-Path (Join-Path $_.FullName $classFile) }

        if ($matches.Count -eq 1) {
            & $script:RealJava -cp "$($matches[0].FullName);$script:RepoLib" @Arguments
            return
        }

        if ($matches.Count -gt 1) {
            throw "Found $($matches.Count) matching out folders for $mainClass. Run from the correct day folder or pass -cp manually."
        }
    }

    & $script:RealJava @Arguments
}

Write-Host "Repo Java commands enabled: javac writes to the source folder's out directory; java uses the nearest matching out directory and lib/*.jar dependencies."