# Library Management System

A simple Spring Boot application for managing a library system with user authentication and book lending functionality.

## Setup Instructions

1. Clone the repository
2. Create PostgreSQL database
3. Configure application properties:
   - Copy `src/main/resources/application.properties.example` to `src/main/resources/application.properties`
   - Update the database configuration in `application.properties` with your PostgreSQL credentials
4. Run the application


The application will start on `http://localhost:8080`

## API Documentation

### Authentication Endpoints

#### Register New User

```
POST /api/auth/register
Content-Type: application/json
{
"email": "user@gmail.com",
"password": "Password123"
}
```
Password requirements:
- Minimum 8 characters
- Must contain at least one uppercase letter
- Must contain at least one number
- No special characters allowed

Email must ends with @gmail.com / @yahoo.com / @hotmail.com

#### Login
```
POST /api/auth/login
Content-Type: application/json
{
"email": "user@gmail.com",
"password": "Password123"
}
```
### Book Endpoints

#### Get Book by ID
```
GET /api/books/{id}
```

#### Search Books
```
GET /api/books/search?title={searchTerm}
```

#### Add New Book (Admin Only)
```
POST /api/books
Content-Type: application/json
{
"title": "Book Title",
"author": "Author Name"
}
```

### Loan Endpoints

#### Borrow a Book
```
POST /api/loans/borrow?userId={userId}&bookId={bookId}
```

#### Return a Book
```
POST /api/loans/{loanId}/return
```

#### Get Overdue Loans (Admin Only)
```
GET /api/loans/overdue
```

#### Get Active Loans (Admin Only)
```
GET /api/loans/active
```
### Admin Email and Password

admin@gmail.com
Admin123

## Business Rules

1. Users can only borrow one book at a time
2. A book must be returned before borrowing another
3. Only administrators can:
   - Add new books
   - View overdue loans
   - View active loans
4. Email domains are restricted to common providers (gmail.com, hotmail.com, yahoo.com)

## Security

- All endpoints except `/api/auth/**` require authentication
- Admin endpoints require ADMIN role
- Authentication is stateless using JWT