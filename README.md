# Employee Database App (Java + JDBC + MySQL)

## 📌 Overview
This is a simple **Java JDBC application** to manage employees in a database.  
It supports basic **CRUD operations**:
- Add Employee
- View Employees
- Update Employee
- Delete Employee

## 🛠 Tools & Technologies
- Java (JDK 8+)
- JDBC
- MySQL (or PostgreSQL)
- VS Code 

## ⚙️ Setup Instructions
1. Install MySQL and create database:
   ```sql
   CREATE DATABASE employee_db;
   USE employee_db;
   CREATE TABLE employee (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       department VARCHAR(100),
       salary DOUBLE
   );
