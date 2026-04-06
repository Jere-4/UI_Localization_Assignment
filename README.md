# Fuel Calculator – Database Setup Guide

This project uses a **MySQL/MariaDB database** to store:

- Localized UI strings (instead of property files)
- Fuel calculation records

All UI text is loaded **dynamically from the database** based on the selected language.

---

## 1. Prerequisites

Before setting up the database, ensure you have the following installed:

- **MySQL or MariaDB**
- **Java JDK 17 or newer**
- **MySQL Connector/J (JDBC driver)**  
  (added via Maven or manually as a JAR dependency)

Make sure the database server is **running**.

---

## 2. Database Overview

The database is named: fuel_calculator_localization

It contains two tables:

### localization_strings
Stores all UI text for each supported language.

- `key` – UI identifier (e.g. `distance`, `calculate`)
- `value` – Localized text
- `language` – Language code (`en`, `fr`, `jp`, `fa`)

### calculation_records
Stores each fuel calculation made in the application.

- `distance` – Trip distance
- `consumption` – Fuel consumption
- `price` – Fuel price
- `total_fuel` – Calculated fuel
- `total_cost` – Calculated cost
- `language` – Active language

---

## 3. Creating and Populating the Database

A complete SQL setup file is provided: database_script.sql

This file:

- Drops any existing database with the same name
- Creates the database and tables
- Inserts **all localization strings** for all supported languages

### Supported Languages

| Language | Code |
|---------|------|
| English | en   |
| French  | fr   |
| Japanese | jp  |
| Persian | fa  |

The language codes **must match exactly** with those used in the Java code.

---

## 4. Importing the SQL File

### Option A: Using MySQL Workbench

1. Open **MySQL Workbench**
2. Connect to your local MySQL server
3. Open a new SQL tab
4. Paste the contents of `database_script.sql`
5. Click **Execute**

---

### Option B: Using Command Line

From the directory containing the SQL file:

mysql -u root -p database_script.sql

Enter your MySQL password when prompted.

---

## 5. Verifying the Data

After importing, verify that localization data exists for all languages:

SELECT language, `key`, value
FROM localization_strings
ORDER BY language, `key`;

Each language must contain six keys:

title,  distance, consumption, price, calculate, result

If any key is missing, the UI will display placeholders like:
!distance!

---

## 6. Database Connection Configuration

The application connects to the database using:
jdbc:mysql://localhost:3306/fuel_calculator_localization

Update credentials in:
DatabaseConnection.java

Example:

private static final String USER = "root";
private static final String PASSWORD = "your_password";

---

## 7. JDBC Driver Requirement

The MySQL JDBC driver must be available on the classpath.

Maven dependency (recommended):


    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>

---

## 8. Common Issues

UI shows !distance!, !price!, etc.

- Localization key is missing in the database
- Language codes in the database do not match the application
- Database was not re-imported after changes

Database connection errors

- MySQL service is not running
- Incorrect username or password
- JDBC driver missing from classpath
