# 🏦 SmartBank: Bank Account Management System

A Java-based banking application that automates core banking operations including account creation, deposits, withdrawals, and balance inquiries. Built with strong OOP principles and secure database integration.

---

## 🚀 Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java |
| Database Connectivity | JDBC |
| Database | MySQL |
| Concepts | OOP, Transaction Management |
| IDE | IntelliJ IDEA / Eclipse |

---

## ✨ Features

- ✅ Create new bank accounts
- ✅ Deposit money into accounts
- ✅ Withdraw money with balance validation
- ✅ Check account balance
- ✅ View transaction history
- ✅ Secure data persistence using JDBC + MySQL
- ✅ Transaction rollback support for data integrity
- ✅ Modular code using OOP principles (Encapsulation, Inheritance, Polymorphism)

---

## 🗄️ Database Schema

**Tables:**

- `customers` — stores customer personal details
- `accounts` — stores account number, type, and balance
- `transactions` — stores deposit/withdrawal history with timestamps

---

## 📁 Project Structure

```
BankManagementSystem/
├── src/
│   └── com/smartbank/
│       ├── model/
│       │   ├── Account.java
│       │   └── Customer.java
│       ├── dao/
│       │   ├── AccountDAO.java
│       │   └── TransactionDAO.java
│       ├── service/
│       │   └── BankService.java
│       ├── util/
│       │   └── DBConnection.java
│       └── Main.java
└── README.md
```

---

## ⚙️ How to Run

### Prerequisites
- Java 8+
- MySQL
- IntelliJ IDEA or Eclipse IDE
- MySQL Connector JAR (add to classpath)

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/Logamurugan12/SmartBank-Account-Management.git

# 2. Open project in IntelliJ IDEA or Eclipse

# 3. Create MySQL database
CREATE DATABASE smartbankdb;

# 4. Create required tables
CREATE TABLE customers (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  email VARCHAR(100),
  phone VARCHAR(15)
);

CREATE TABLE accounts (
  account_number VARCHAR(20) PRIMARY KEY,
  customer_id INT,
  account_type VARCHAR(20),
  balance DOUBLE,
  FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE transactions (
  id INT PRIMARY KEY AUTO_INCREMENT,
  account_number VARCHAR(20),
  type VARCHAR(10),
  amount DOUBLE,
  transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

# 5. Update DBConnection.java with your MySQL credentials
String url = "jdbc:mysql://localhost:3306/smartbankdb";
String username = "your_username";
String password = "your_password";

# 6. Add MySQL Connector JAR to project classpath

# 7. Run Main.java
```

---

## 💡 OOP Concepts Used

| Concept | Where Used |
|---------|------------|
| Encapsulation | Account, Customer model classes with private fields + getters/setters |
| Inheritance | Base Account class extended by SavingsAccount, CurrentAccount |
| Polymorphism | Method overriding for deposit/withdrawal logic |
| Abstraction | DAO interfaces for database operations |

---

## 👨‍💻 Author

**Logamurugan P**  
📧 logamurugan04@gmail.com  
🔗 [LinkedIn](https://linkedin.com/in/logamurugan-p-20205b24b)  
🐙 [GitHub](https://github.com/Logamurugan12)  
🌐 [Portfolio](https://logamuruganportfolio.netlify.app)
