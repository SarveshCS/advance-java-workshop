<!-- markdownlint-disable -->

# Question 1 - JDBC Project

## Exact Question

Project Name: `JdbcProject`

Tasks:

1. Download and configure the MySQL Connector/J.
2. Establish a JDBC connection to a MySQL database (`NIET` or any database of your choice).
3. Create a table named `STUDENT` with:
   - ID (Primary Key, Auto Increment)
   - Name
   - Age
   - Department
   - City
4. Insert at least 10 sample records.
5. Retrieve and display all records using an SQL `SELECT` query.
6. Use proper exception handling and display the records neatly.

## Solution Summary

This folder contains a simple console JDBC program named `JdbcProject.java`.

| Setting | Value |
| --- | --- |
| Host | `localhost` |
| Port | `3306` |
| Database | `NIET` |
| Username | `root` |
| Password | empty password |

The program creates the `STUDENT` table with the exact fields from the question, inserts 10 sample records, and displays all records in a simple structured format.

## Files

| File | Purpose |
| --- | --- |
| [JdbcProject.java](JdbcProject.java) | Complete JDBC answer for creating the table, inserting records, and displaying records. |

## How to Run

Make sure MySQL is running and the database exists:

```sql
CREATE DATABASE IF NOT EXISTS NIET;
```

Compile from the repository root:

```powershell
javac -cp ".\lib\mysql-connector-j-8.4.0.jar" -d .\day_4\Question_1\out .\day_4\Question_1\JdbcProject.java
```

Run from the repository root:

```powershell
java -cp ".\day_4\Question_1\out;.\lib\mysql-connector-j-8.4.0.jar" JdbcProject
```