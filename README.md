<p align="center">
  <img src="src/resources/OIP.jpeg" alt="Siksa Higher Education Logo" width="120" />
</p>

<h1 align="center">📚 Siksa Higher Education — Class Management System</h1>

<p align="center">
  <strong>A full-featured Java Swing desktop application for managing classes, students, teachers, payments, marks, timetables, and institutional finances.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/IDE-NetBeans-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white" />
  <img src="https://img.shields.io/badge/UI-FlatLaf%20Dark-blueviolet?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" />
</p>

---

## 🌟 Overview

**Siksa Higher Education Class Management System** is a comprehensive desktop application built with **Java Swing** designed for educational institutions to streamline their day-to-day administrative operations. From student enrollment and fee management to teacher timetabling and automated email notifications — everything is managed through a sleek, modern dark-themed interface powered by **FlatLaf**.

---

## ✨ Key Features

### 🎓 Student Management
- Register new students with personal details (name, email, city, gender, stream, year)
- View, search, filter, and sort student records
- Student profile management with complete CRUD operations
- Assign students to classes and streams

### 👨‍🏫 Teacher Management
- Register and manage teacher profiles
- Assign teachers to subjects and classes
- Teacher-specific dashboard with personal timetable view

### 💰 Payment & Fee Management
- Record student fee payments with multiple payment methods
- Invoice generation with itemized billing
- Payment history tracking by student, class, month, and year
- Print invoices using **JasperReports**

### 📊 Dashboard & Analytics
- Interactive **Polar Area Charts** for data visualization
- Real-time statistics on students, teachers, and staff
- Category-wise breakdown (Admin, HR, IT, Intern)
- Overview of institutional data at a glance

### 📝 Marks & Results
- Record student marks by subject, class, and month
- Generate and print result reports
- Mark-sheet PDF generation via JasperReports

### 📅 Timetable Management
- Create and manage class timetables for teachers
- Filter timetable by class, subject, month, and class type
- Print timetable reports

### 💼 Income & Salary Management
- Track institutional income
- Salary management for staff
- Monthly income reports with print support
- Real-time clock display on the income dashboard

### 📧 System Mailer
- Built-in email functionality using **JavaMail API**
- Send emails directly to students and staff from the application
- Authenticated SMTP email sending

### 📄 Report Generation
- Pre-built **JasperReports** templates for:
  - Student reports
  - Teacher reports
  - Staff reports
  - Income reports
  - Mark tables & results
  - Timetable reports
  - Invoice / payment receipts

### 🔐 Role-Based Authentication
- Separate sign-in screens for different modules:
  - Dashboard admin login
  - Student management login
  - Teacher management login
  - Payment login
  - Income/Salary login
  - Mailer login

---

## 🛠️ Tech Stack

| Layer           | Technology                                                        |
| --------------- | ----------------------------------------------------------------- |
| **Language**    | Java 17+                                                          |
| **GUI**         | Java Swing with [FlatLaf](https://www.formdev.com/flatlaf/) Dark Theme |
| **Database**    | MySQL 8.0                                                         |
| **DB Driver**   | MySQL Connector/J 9.0.0                                           |
| **Reports**     | JasperReports 7.0.0                                               |
| **PDF**         | iText / OpenPDF                                                   |
| **Email**       | JavaMail API (javax.mail 1.6.2)                                   |
| **Charts**      | Custom Polar Area Chart component                                 |
| **Animations**  | TimingFramework 0.55                                              |
| **Calendar**    | JCalendar 1.4                                                     |
| **SVG**         | JSVG 1.4.0                                                        |
| **JSON**        | Jackson Databind 2.17.2                                           |
| **Notifications** | Swing Toast Notifications 1.0.3                                |
| **IDE**         | Apache NetBeans                                                   |
| **Build**       | Apache Ant                                                        |

---

## 📁 Project Structure

```
F_M_System/
├── src/
│   ├── Gui/                        # All GUI screens (JFrame forms)
│   │   ├── Home.java               # Main landing page
│   │   ├── Dashboard.java          # Admin dashboard with charts & analytics
│   │   ├── StudentClz.java         # Student-class assignment management
│   │   ├── StudentProfile.java     # Student profile view
│   │   ├── StudentMarks.java       # Student marks entry & viewing
│   │   ├── Payment.java            # Fee payment processing
│   │   ├── timetableTeacher.java   # Teacher timetable management
│   │   ├── income_Salary.java      # Income & salary tracking
│   │   ├── Mail_sending.java       # Email sending interface
│   │   ├── markPrint.java          # Mark printing screen
│   │   ├── Mailer_login.java       # Mailer authentication
│   │   ├── paymentLogin.java       # Payment module authentication
│   │   ├── signin.java             # Dashboard sign-in
│   │   ├── signin1.java            # Student module sign-in
│   │   ├── signin12t.java          # Teacher module sign-in
│   │   └── signinIncome.java       # Income module sign-in
│   │
│   ├── Model/                      # Data models & database layer
│   │   ├── MYSQL.java              # MySQL connection & query helper
│   │   └── invoiceItem.java        # Invoice item data model
│   │
│   ├── chart/                      # Custom chart components
│   │   ├── PolarAreaChart.java     # Polar area chart renderer
│   │   ├── ModelPolarAreaChart.java # Chart data model
│   │   └── PolarAreaLabel.java     # Chart label component
│   │
│   ├── reports/                    # JasperReports compiled templates (.jasper)
│   │   ├── StudentRepo.jasper
│   │   ├── TeacherRepo.jasper
│   │   ├── StaffRepo.jasper
│   │   ├── InvoiceSiksa.jasper
│   │   ├── incomereport.jasper
│   │   ├── markTable.jasper
│   │   ├── results.jasper
│   │   ├── timetable.jasper
│   │   └── ...
│   │
│   └── resources/                  # Icons, images & assets
│       ├── OIP.jpeg                # Logo
│       └── icons8-*.png            # UI icons
│
├── lib/                            # External JAR dependencies
├── build.xml                       # Ant build script
├── manifest.mf                     # JAR manifest
└── README.md                       # This file
```

---

## ⚙️ Prerequisites

Before running the project, ensure you have the following installed:

- **Java JDK 17** or higher → [Download](https://www.oracle.com/java/technologies/downloads/)
- **MySQL Server 8.0** → [Download](https://dev.mysql.com/downloads/mysql/)
- **Apache NetBeans IDE** (recommended) → [Download](https://netbeans.apache.org/download/)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Dilshan-DevX/Class-Management-System.git
cd Class-Management-System
```

### 2. Set Up the Database

Create a MySQL database named `fees_management_system`:

```sql
CREATE DATABASE fees_management_system;
USE fees_management_system;
```

> **Note:** Import the database schema/SQL dump if provided separately, or create tables based on the application's entity structure (students, teachers, staff, classes, subjects, payments, marks, timetable, etc.).

### 3. Configure Database Connection

Update the database credentials in [`MYSQL.java`](src/Model/MYSQL.java):

```java
connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/fees_management_system",
    "your_username",
    "your_password"
);
```

### 4. Open in NetBeans

1. Open **Apache NetBeans IDE**
2. Go to `File → Open Project`
3. Navigate to the cloned project directory and open it
4. NetBeans will automatically detect the Ant-based project

### 5. Build & Run

- **Build:** Right-click the project → `Clean and Build`
- **Run:** Right-click the project → `Run` (or press `F6`)
- The application starts at the **Home** screen with navigation to all modules

---

## 📸 Application Modules

| Module              | Description                                              |
| ------------------- | -------------------------------------------------------- |
| **Home**            | Main landing page with navigation to all modules         |
| **Dashboard**       | Admin analytics with charts, student/teacher/staff data  |
| **Student Management** | Full CRUD for student records with search & filter    |
| **Teacher Management** | Manage teachers, subjects, and class assignments      |
| **Payment**         | Process fees, generate invoices, track payment history   |
| **Marks**           | Enter and view student marks, generate report cards      |
| **Timetable**       | Manage teacher timetables by class, subject, and month   |
| **Income / Salary** | Track institutional income and staff salaries             |
| **System Mailer**   | Send emails to students and staff directly from the app  |

---

## 📊 Database Schema (Key Tables)

| Table              | Purpose                                |
| ------------------ | -------------------------------------- |
| `student`          | Student personal details & enrollment  |
| `teacher`          | Teacher profiles                       |
| `staff`            | Non-teaching staff records             |
| `class`            | Class definitions with subject mapping |
| `subject`          | Subjects offered                       |
| `clz_year`         | Academic years                         |
| `stream`           | Academic streams                       |
| `city`             | Student cities                         |
| `gender`           | Gender options                         |
| `month`            | Months for scheduling                  |
| `marks`            | Student marks by class/subject/month   |
| `payment`          | Fee payment records                    |
| `payment_method`   | Payment method options                 |
| `timetable`        | Class timetable entries                |
| `income`           | Income tracking records                |
| `admin`            | Admin login credentials                |

---

## 🔧 Configuration

### Email Setup (System Mailer)

The system uses **SMTP via JavaMail** for sending emails. To configure:

1. Use a Gmail account with [App Passwords](https://support.google.com/accounts/answer/185833) enabled
2. Update SMTP credentials in the `Dashboard.java` or `Mail_sending.java` file

### Logging

The application uses `java.util.logging` with file-based log rotation:

- Log file: `FM_SYSTEM.log`
- Format: `SimpleFormatter`
- Rotation: Automatic (up to 3 backup files)

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/your-feature`)
3. **Commit** your changes (`git commit -m 'Add some feature'`)
4. **Push** to the branch (`git push origin feature/your-feature`)
5. **Open** a Pull Request

---

## 📝 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Dilshan** — [@Dilshan-DevX](https://github.com/Dilshan-DevX)

> *Copyright © 2024 CodeX.LTD*

---

<p align="center">
  <sub>Built with ❤️ using Java Swing & FlatLaf</sub>
</p>
