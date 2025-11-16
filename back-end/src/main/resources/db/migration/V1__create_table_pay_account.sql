CREATE TABLE pay_account (
    id                         BIGSERIAL PRIMARY KEY,
    name                       VARCHAR(255) NOT NULL,
    original_amount            NUMERIC(15,2) NOT NULL,
    adjusted_amount            NUMERIC(15,2) NOT NULL,
    late_days                  INT NOT NULL,
    fine_percentage            NUMERIC(5,2) NOT NULL,
    daily_interest_percentage  NUMERIC(5,3) NOT NULL,
    due_date                   DATE NOT NULL,
    payment_date               DATE NOT NULL
);
