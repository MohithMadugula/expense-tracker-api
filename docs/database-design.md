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