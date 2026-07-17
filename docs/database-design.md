                USERS
             ----------------
             user_id (PK)
             name
             email
             password
                  |
      -----------------------------
      |            |             |
      |            |             |
EXPENSES      INCOME       BUDGET
|
|
CATEGORY

# Expense Tracker Database Design

## Users Table

| Column | Data Type | Constraints |
|---------|-----------|-------------|
| user_id | BIGINT | Primary Key, Auto Increment |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(150) | UNIQUE, NOT NULL |
| password | VARCHAR(255) | NOT NULL |
| phone_number | VARCHAR(15) | UNIQUE |
| created_at | TIMESTAMP | NOT NULL |
| updated_at | TIMESTAMP | NOT NULL |


                         USERS
                     +-----------+
                     | user_id   |
                     +-----------+
                           |
        +------------------+------------------+
        |                  |                  |
        |                  |                  |
        |                  |                  |
     +---------------+   +---------------+   +---------------+
    |  CATEGORIES   |   |   EXPENSES    |   |    INCOME     |
    +---------------+   +---------------+   +---------------+
    | category_id   |<--| category_id   |   | income_id     |
    | user_id       |   | user_id       |   | user_id       |
    +---------------+   +---------------+   +---------------+
        |
        |
    +---------------+
    |    BUDGETS    |
    +---------------+
    | budget_id     |
    | user_id       |
    +---------------+



# Expense Tracker API - Database Design

## Overview

The Expense Tracker API is designed to help users manage their personal finances by tracking income, expenses, categories, and monthly budgets.

The database follows a relational design where each user owns their own categories, expenses, income records, and budgets.

---

# Entity Relationship

    User
    │
    ├── Categories
    │
    ├── Expenses
    │     │
    │     └── Category
    │
    ├── Income
    │
    └── Budgets

---

# 1. Users Table

## Purpose

Stores user account information required for authentication and profile management.

| Column | Description |
|---------|-------------|
| user_id | Primary Key |
| username | Unique username |
| email | Unique email address |
| password | Stores hashed password |
| phone_number | User contact number |
| created_at | Account creation timestamp |
| updated_at | Last updated timestamp |

### Business Rules

- Username must be unique.
- Email must be unique.
- Password is stored as a hashed value.
- Phone number is optional.
- User can update email after verification.

---

# 2. Categories Table

## Purpose

Stores expense and income categories created by individual users.

| Column | Description |
|---------|-------------|
| category_id | Primary Key |
| user_id | Owner of the category |
| category_name | Name of category |
| description | Optional description |
| status | ACTIVE / INACTIVE |
| created_at | Creation timestamp |
| updated_at | Last updated timestamp |

### Business Rules

- Categories are user-specific.
- Category names should be unique per user.
- Description is optional.
- Categories are soft deleted using the status column.
- Historical expense records must remain intact.

---

# 3. Expenses Table

## Purpose

Stores all expense transactions made by users.

| Column | Description |
|---------|-------------|
| expense_id | Primary Key |
| user_id | Owner of expense |
| category_id | Expense category |
| expense_name | Expense title |
| amount | Expense amount |
| expense_date | Date of expense |
| payment_mode | Cash / UPI / Card / Bank Transfer |
| notes | Optional remarks |
| created_at | Creation timestamp |
| updated_at | Last updated timestamp |

### Business Rules

- Expense amount must be greater than zero.
- Expense date cannot be a future date.
- Expense name is mandatory.
- Notes are optional.
- Amount will use DECIMAL instead of DOUBLE.
- Payment mode is stored directly in the table.

---

# 4. Income Table

## Purpose

Stores all income transactions.

| Column | Description |
|---------|-------------|
| income_id | Primary Key |
| user_id | Owner of income |
| category_id | Income category |
| income_name | Income title |
| amount | Income amount |
| income_date | Date received |
| payment_mode | Bank Transfer / Cash / UPI |
| notes | Optional remarks |
| created_at | Creation timestamp |
| updated_at | Last updated timestamp |

### Business Rules

- Every income entry represents a separate transaction.
- Amount must be greater than zero.
- Income date cannot be in the future.
- Payment mode is stored in the table.
- Amount uses DECIMAL datatype.

---

# 5. Budgets Table

## Purpose

Stores monthly budgets for users.

| Column | Description |
|---------|-------------|
| budget_id | Primary Key |
| user_id | Owner |
| budget_amount | Monthly budget amount |
| month | Budget month |
| year | Budget year |
| status | ACTIVE / INACTIVE |
| created_at | Creation timestamp |
| updated_at | Last updated timestamp |

### Business Rules

- One active budget per month per user.
- Budget can be updated.
- Remaining budget is calculated dynamically.
- Budget amount uses DECIMAL datatype.

---

# Relationships

Users (1) ------ (N) Categories

Users (1) ------ (N) Expenses

Users (1) ------ (N) Income

Users (1) ------ (N) Budgets

Categories (1) ------ (N) Expenses

---

# Table Classification

## Master Tables

- Users
- Categories

## Transaction Tables

- Expenses
- Income

## Planning Table

- Budgets

---

# Design Decisions

### Why user_id instead of username?

Usernames can change.
Primary keys should remain constant.

---

### Why DECIMAL instead of DOUBLE?

Financial applications require precise decimal calculations.
DOUBLE can introduce floating-point precision errors.

---

### Why store payment_mode in the Expense and Income tables?

The list of payment modes is small and rarely changes.
Creating a separate table would add unnecessary complexity.

---

### Why use status in Category

Instead of deleting categories, they are marked as INACTIVE.
This preserves historical expense records.

---

### Why store created_at and updated_at?

These fields provide audit information and help with debugging, reporting, and tracking changes.

---

