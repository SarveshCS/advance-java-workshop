<!-- markdownlint-disable -->

# Question 2 - JDBC Project Update

## Exact Question

Project Name: `JdbcProjectUpdate`

Tasks:

1. Connect to the MySQL database using JDBC.
2. Perform operations on the `STUDENT` table:
   - Update an existing student record.
   - Delete a student record by ID.
   - Insert at least 2 new student records.
3. Display the updated records using a `SELECT` query.
4. Follow proper exception handling and formatted output.

## Solution Summary

This folder contains a console JDBC program named `JdbcProjectUpdate.java`. It connects to the same MySQL database used in the workshop, works on the `STUDENT` table, updates one record, deletes one record, inserts two new records, and then displays the final table.

The database settings are:

| Setting | Value |
| --- | --- |
| Host | `127.0.0.1` |
| Port | `3306` |
| Database | `college_workshop` |
| Username | `root` |
| Password | empty password |

## Files

| File | Purpose |
| --- | --- |
| [JdbcProjectUpdate.java](JdbcProjectUpdate.java) | Complete JDBC answer for update, delete, insert, and display operations on `STUDENT`. |

## How to Run

Make sure MySQL is running and the database exists:

```sql
CREATE DATABASE IF NOT EXISTS college_workshop;
```

Compile from the repository root:

```powershell
javac -cp ".\lib\mysql-connector-j-8.4.0.jar" -d .\day_4\Question_2\out .\day_4\Question_2\JdbcProjectUpdate.java
```

Run from the repository root:

```powershell
java -cp ".\day_4\Question_2\out;.\lib\mysql-connector-j-8.4.0.jar" JdbcProjectUpdate
```