CREATE TABLE IF NOT EXISTS book_loans (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    book_id BIGINT REFERENCES books(id),
    loan_date TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP,
    UNIQUE(book_id, return_date)
); 