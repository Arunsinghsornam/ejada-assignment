# 🚀 Automation Framework – Ejada Assignment

## 📌 Overview

This project is a scalable test automation framework built using:

* Java
* Selenium WebDriver
* Cucumber (BDD with Gherkin)
* TestNG
* REST Assured (API Testing)

It covers both **UI automation** and **API testing** with a clean and maintainable design.

---

## 🎯 Key Features

* ✅ Cross-browser testing (Chrome & Firefox)
* ✅ Parallel execution using TestNG
* ✅ Page Object Model (POM) design
* ✅ API automation (GET, POST, PATCH, DELETE)
* ✅ Allure reporting integration
* ✅ Thread-safe execution using ThreadLocal

---

## 📁 Project Structure

```text
src/test/java/com
│
├── pages             → Page Object classes
├── stepdefinitions   → Cucumber step definitions
├── runners           → TestNG runners
├── utils             → Utilities (Driver, Config, Waits, etc.)
│
src/test/resources
├── features          → Feature files (UI + API)
├── config            → config.properties
│
pom.xml
testng.xml
README.md
```

---

## ⚙️ Configuration

Edit `config.properties`:

```properties
browser=chrome
headless=false
base.url=https://www.saucedemo.com/
api.base.url=https://simple-books-api.glitch.me
```

---

## ▶️ How to Run Tests

### Run All Tests:

```bash
mvn clean test
```

### Run via TestNG:

```bash
Run testng.xml
```

---

## 📊 Reports

### Allure Report:

```bash
allure serve target/allure-results
```

---

## 🧪 Test Coverage

### 🔹 UI Automation (SauceDemo)

* Login scenarios:

  * Valid login
  * Invalid login
  * Locked user
  * Empty credentials
* Product order flow:

  * Add to cart
  * Checkout
  * Order confirmation

---

### 🔹 API Automation (Simple Books API)

* GET books
* GET book by ID
* POST client registration (token generation)
* POST order creation
* PATCH order update
* DELETE order

---

## 🧠 Design Highlights

* Thread-safe WebDriver using ThreadLocal
* Reusable WaitHelper for stability
* Clean separation of concerns (POM + Steps + Utils)
* Config-driven execution

---

## 👨‍💻 Author

Karthick Arun
Senior Automation Tester Assignment Submission
