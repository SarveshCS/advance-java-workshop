# Servlet, JSP, and Tomcat Setup Notes

This example uses a Servlet for Java request handling and JSP pages for simple web pages.

## Should You Use Maven?

For a college workshop where the teacher wants to see Tomcat, you do not need Maven.

Use Tomcat directly as the main method. This shows the important Servlet/JSP steps clearly:

1. Write JSP files.
2. Write a Servlet class.
3. Compile the Servlet using Tomcat's Servlet API JAR.
4. Put the compiled class inside `WEB-INF/classes`.
5. Deploy the web application on Tomcat.

Maven is still useful in real projects because it downloads libraries and creates the WAR file automatically. In this workshop example, treat Maven as optional.

## What You Need

- JDK 17 or newer
- XAMPP Tomcat or Apache Tomcat 8.5
- Tomcat's `servlet-api.jar` for compiling the servlet before deployment
- VS Code with the Extension Pack for Java, if you want editor support

This setup uses XAMPP's bundled Tomcat 8.5.96. Tomcat 8.5 uses the `javax.servlet` package.

Tomcat 10 uses `jakarta.servlet`, but XAMPP's Tomcat 8.5 does not. If you use XAMPP Tomcat, keep the imports as `javax.servlet...`.

## Check Installed Tools

Open PowerShell in the repository root and run:

```powershell
java -version
javac -version
```

To check whether XAMPP Tomcat is available, try:

```powershell
$env:CATALINA_HOME = "C:\xampp\tomcat"
$env:JAVA_HOME = "C:\Program Files\Java\jdk-23"
$env:JRE_HOME = "C:\Program Files\Java\jdk-23"
& "C:\xampp\tomcat\bin\version.bat"
```

You do not need to download a separate Servlet API JAR when using XAMPP Tomcat. Use this file that already comes with XAMPP:

```text
C:\xampp\tomcat\lib\servlet-api.jar
```

## Manual Tomcat Build Without Maven

Use this method when you want to show that you understand Tomcat deployment.

These commands use XAMPP Tomcat's Servlet API JAR for compiling.

On this machine, the real JDK tools are here:

```text
C:\Program Files\Java\jdk-23\bin
```

From the repository root:

```powershell
$project = ".\day_2\servlet_jsp_demo"
$build = "$project\manual-build\servlet-jsp-demo"
$jdkBin = "C:\Program Files\Java\jdk-23\bin"

Remove-Item "$project\manual-build" -Recurse -Force -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force -Path "$build\WEB-INF\classes" | Out-Null

Copy-Item "$project\src\main\webapp\*.jsp" $build

& "$jdkBin\javac.exe" -cp "C:\xampp\tomcat\lib\servlet-api.jar" -d "$build\WEB-INF\classes" "$project\src\main\java\HelloServlet.java"
& "$jdkBin\jar.exe" -cvf "$project\manual-build\servlet-jsp-demo.war" -C $build .
```

This creates:

```text
day_2\servlet_jsp_demo\manual-build\servlet-jsp-demo.war
```

## Run on Tomcat

These commands use XAMPP Tomcat here:

```text
C:\xampp\tomcat
```

If you click **Start** for Tomcat in the XAMPP Control Panel, you only need to copy the WAR file into `webapps` and open the browser URL.

Copy the WAR file into Tomcat's `webapps` folder:

```powershell
$tomcatHome = "C:\xampp\tomcat"
Copy-Item .\day_2\servlet_jsp_demo\manual-build\servlet-jsp-demo.war "$tomcatHome\webapps\"
```

Start Tomcat from PowerShell, or click **Start** beside Tomcat in the XAMPP Control Panel:

```powershell
$env:CATALINA_HOME = "C:\xampp\tomcat"
$env:JAVA_HOME = "C:\Program Files\Java\jdk-23"
$env:JRE_HOME = "C:\Program Files\Java\jdk-23"
& "C:\xampp\tomcat\catalina_start.bat"
```

Open this URL in a browser:

```text
http://localhost:8080/servlet-jsp-demo/
```

To stop Tomcat:

```powershell
$env:CATALINA_HOME = "C:\xampp\tomcat"
& "C:\xampp\tomcat\catalina_stop.bat"
```

## What Is `pom.xml`?

`pom.xml` is Maven's project configuration file.

It tells Maven:

- the project name
- the Java version
- the required libraries
- whether the output should be a `.jar` or `.war` file
- which build plugins to use

In this demo, `pom.xml` says the project is a web application and should be packaged as a WAR file. It also uses `javax.servlet-api` because XAMPP Tomcat 8.5 uses `javax.servlet`.

You do not need Maven for the manual Tomcat method, but keeping `pom.xml` is useful because many real Java web projects use Maven.

## What Is the `target` Folder?

`target` is Maven's output folder.

When you run this command:

```powershell
mvn clean package
```

Maven creates compiled classes and the final WAR file inside `target/`.

For example:

```text
target\servlet-jsp-demo.war
```

You usually do not write files inside `target/` manually. It is generated build output and can be deleted safely because Maven can recreate it.

In the manual method, this project uses `manual-build/` for the same idea: temporary build output that can be recreated.

## Do Developers Write This Manually?

For learning, writing the folder structure manually is useful because it shows how Tomcat expects a web application to be arranged.

The important structure is:

```text
servlet-jsp-demo.war
	index.jsp
	result.jsp
	WEB-INF\classes\HelloServlet.class
```

In real projects, developers usually generate this structure with Maven, Gradle, an IDE wizard, Spring Initializr, or another project template. After the project is created, developers mostly write Servlet classes, JSP pages, service classes, and configuration files. The build tool creates the final WAR.

## Optional Maven Build

Use this only if your teacher allows Maven or you want a cleaner project build.

First check Maven:

```powershell
mvn -version
```

If Maven is not installed, you can install it with:

```powershell
winget install Apache.Maven
```

Then build the web application.

From the repository root:

```powershell
Set-Location .\day_2\servlet_jsp_demo
mvn clean package
```

After a successful build, Maven creates this file:

```text
target/servlet-jsp-demo.war
```

## How the Example Works

- `index.jsp` shows the HTML form.
- `HelloServlet.java` receives the form value using `request.getParameter()`.
- The servlet stores a message using `request.setAttribute()`.
- The servlet forwards the request to `result.jsp` using `RequestDispatcher`.
- `result.jsp` displays the message using JSP Expression Language: `${message}`.