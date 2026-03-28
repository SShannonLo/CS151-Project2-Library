# CS151-Project2-Library

## Overview
This is a Java-based Library Management System developed for CS151 Spring 2026.
The system simulates a real-world library where members can borrow books, staff
can manage the collection, and the system tracks loans and fines automatically.
Users log in with an ID and PIN, and are shown a menu based on their role.

## Design
The system is built around the following classes:

- **Book** - represents a book in the library. Supports checkout, return, and reservations.
- **Catalog** - manages the collection of books. Supports searching by title, author, and ID.
- **Loan** - represents a borrowing transaction between a member and a book. Handles fines and due dates.
- **Member** - represents a library member. Can borrow books, pay fines, and manage their account.
- **LibraryStaff** - represents a library employee. Can add/remove books, register members, and waive fines.
- **Library** - manages all users and the book catalog. Handles login and enforces max capacity of 100 users and 100 books.
- **Reservation** - allows members to reserve study rooms.
- **LibraryCard** - represents a member's library card with activation and expiration logic.

The system also includes:
- **LibraryUser** (Abstract Class) - shared base class for Member and LibraryStaff, holds common attributes like name, id, email, and phone.
- **Borrowable** (Interface) - implemented by Book to enforce checkout/return behavior.
- **Authentication** (Interface) - implemented by Member and LibraryStaff to enforce login behavior.

Custom exceptions for error handling:
- **InvalidPinException** - thrown when a PIN is null or empty.
- **BookNotFoundException** - thrown when a book cannot be found.
- **MemberNotFoundException** - thrown when a member cannot be found.

## Installation Instructions
1. Clone the repository: `git clone https://github.com/angelinary/CS151-Project2-Library`
2. Open the project in IntelliJ IDEA
3. Make sure JDK 23 is configured
4. Run `Main.java` to start the program

## Usage
When the program starts it will display library hours and prompt you to log in.

**Test credentials:**
- Member: ID `018134168`, PIN `00` (Angelina)
- Member: ID `018134169`, PIN `11` (David)
- Staff: ID `110110110`, PIN `22` (Librarian Bob)

**Member menu options:** View profile, browse books, checkout book, pay fees, close account, return book, view/reserve study rooms.

**Staff menu options:** Add a book, remove a book, view all books, waive a member's fine, search books by title, update role, logout.

Type `X` to exit the program.

## Contributions
| Team Member | Classes | Responsibilities |
|---|---|---|
| Yanin Arevalos | LibraryStaff, Loan, Authentication interface, InvalidPinException, BookNotFoundException, MemberNotFoundException | Staff menu, loan tracking, fines, authentication, exception handling, unit tests |
| Angelina Ryabechenkova | LibraryUser, Library, Main, Member | Abstract base class, library management, main menu flow, member functionality, system integration |
| Keyi Tan | Book, Catalog, Borrowable interface | Book management, catalog search, borrowable behavior, book checkout and return |
| Cagla Sah | LibraryCard, Reservation | Library card management, study room reservations |