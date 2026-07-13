<!-- markdownlint-disable -->

# Advanced Java Workshop

This repository contains workshop materials and Java examples for Advanced Java practice.

## Workshop Index

Add each program, notes file, or exercise under the correct day section. This works like the index at the start of a book, so files are easy to find later.

### Repository Tools

| Topic | Description | File |
| --- | --- | --- |
| Ignore rules | Keeps local AI instructions, Java build output, and IDE files out of Git. | [.gitignore](.gitignore) |
| VS Code terminal setup | Opens new workspace terminals with repo Java commands already loaded. | [.vscode/settings.json](.vscode/settings.json) |
| Java VS Code tasks | Compile and run the active Java file using that day's `out/` directory. | [.vscode/tasks.json](.vscode/tasks.json) |
| Repo Java commands | Optional PowerShell commands that make `javac` and `java` use each day's `out/` folder automatically. | [.vscode/java-repo-commands.ps1](.vscode/java-repo-commands.ps1) |

### Day 1

| Topic | Description | File |
| --- | --- | --- |
| Student marks summary | Calculates total marks, average marks, highest marks, and grade using arrays, loops, methods, and conditions. | [day_1/StudentMarksSummary.java](day_1/StudentMarksSummary.java) |

### Day1

| Topic | Description | File |
| --- | --- | --- |
| String | Cleans a raw student name, formats a display name, and generates a course username using Java String methods. | [Day1/StringExample.java](Day1/StringExample.java) |
| Array | Stores expense categories and amounts in arrays, then calculates total expense and the highest expense category. | [Day1/ArrayExample.java](Day1/ArrayExample.java) |
| Encapsulation | Models a bank account with private fields and controlled deposit, withdrawal, and getter methods. | [Day1/EncapsulationExample.java](Day1/EncapsulationExample.java) |
| Inheritance | Reuses common employee details in a parent class and extends them with full-time salary details. | [Day1/InheritanceExample.java](Day1/InheritanceExample.java) |
| Polymorphism | Uses one parent reference type to call different payment behavior for card and UPI payments. | [Day1/PolymorphismExample.java](Day1/PolymorphismExample.java) |
| Constructor | Creates course enrollment objects using a default constructor, parameterized constructor, and constructor chaining. | [Day1/ConstructorExample.java](Day1/ConstructorExample.java) |
| Abstraction | Defines a common report template with an abstract method implemented by sales and attendance reports. | [Day1/AbstractionExample.java](Day1/AbstractionExample.java) |

## Running Java Programs

Create day folders using this pattern:

```text
day_1/
day_2/
day_3/
```

Put that day's Java files inside the matching folder.

### Option 1: Repo Java Commands

New VS Code terminals load the repo Java commands automatically. In an already-open PowerShell terminal, run this once from the repository root:

```powershell
. .\.vscode\java-repo-commands.ps1
```

After that, normal commands become simpler inside this terminal session:

```powershell
javac .\day_1\HelloWorld.java
java HelloWorld
```

The custom `javac` command compiles to `day_1/out/`. The custom `java` command uses the current folder's `out/` folder, or finds the matching class in a day folder's `out/` directory when there is only one match.

To use the real commands without this behavior, pass output/classpath manually:

```powershell
javac -d .\somewhere .\day_1\HelloWorld.java
java -cp .\day_1\out HelloWorld
```

### Option 2: VS Code Tasks

To compile and run with that same day's `out/` folder, open the Java file and run this VS Code task:

```text
Terminal > Run Task > Java: run active file from day out
```

The task compiles to `day_1/out/` and then runs the class from that folder. The build task is also the default build task, so `Terminal > Run Build Task` compiles the open Java file into its day folder's `out/` directory.

Manual command pattern:

```powershell
javac -d .\day_1\out .\day_1\HelloWorld.java
java -cp .\day_1\out HelloWorld
```

