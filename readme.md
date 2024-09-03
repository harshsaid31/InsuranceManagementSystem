Project: Insurance Management System

The following repository contains the code of Insurance Management System (console based application). This project is developed using Core Java and MySQL.

Features:
1. Policy Management
2. Customer Management
3. Claim Management
	
Pre-requisite for running Application:
1. Java Development Kit (JDK) 8 or above
2. MySQL Server
3. Eclipse IDE
	
Project setup steps:

1. Clone the repository


2. Open the project using eclipse IDE


3. Configure the Database 

Create MySQL Database using following command

```shell
CREATE DATABASE insurance;
```

Update the database credentials in the `DatabaseConnection.java` or a configuration file:

```shell
private static final String URL = "jdbc:mysql://localhost:3306/insurance";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```


4. Create the tables by executing the following MySQL commands

```shell
CREATE TABLE Policy (
policy_id INT AUTO_INCREMENT PRIMARY KEY,
policy_number VARCHAR(255) UNIQUE NOT NULL,
type VARCHAR(255) NOT NULL,
coverage_amount DOUBLE NOT NULL,
premium_amount DOUBLE NOT NULL
);
```

```shell
CREATE TABLE Customer (
customer_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
phone_number VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL
);
```

```shell
CREATE TABLE Claim (
claim_id INT AUTO_INCREMENT PRIMARY KEY,
policy_id INT,
customer_id INT,
claim_date DATE NOT NULL,
status VARCHAR(50) NOT NULL DEFAULT "Submitted",
FOREIGN KEY (policy_id) REFERENCES Policy(policy_id),
FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);
```

5. After following the above steps run the application by navigating to `App.java` file. Right-click on the file and click on run the java application. The console application with start running.



