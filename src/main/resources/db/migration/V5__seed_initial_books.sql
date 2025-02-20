INSERT INTO books (title, author) VALUES
    ('The Lord of the Rings', 'J.R.R. Tolkien'),
    ('Pride and Prejudice', 'Jane Austen'),
    ('To Kill a Mockingbird', 'Harper Lee'),
    ('1984', 'George Orwell'),
    ('The Great Gatsby', 'F. Scott Fitzgerald')
ON CONFLICT DO NOTHING; 