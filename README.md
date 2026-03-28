# CS151-Project2-Library

## Overview
This is a Java Library Management System developed for CS151.
The system simulates a library where members can borrow books, staff
can manage the collection, and the system tracks loans and fines automatically.
Users log in with an ID and PIN, and are shown a menu based on their role.

## Design
The system consists the following classes:

- **Book** - represents a book in the library. Supports checkout, return, and reservations.
- **Catalog** - manages the collection of books. Supports searching by title, author, and ID.
- **Loan** - represents a borrowing transaction between a member and a book. Handles fines and due dates.
- **Member** - represents library members that can have library accounts and track their library activity.
- **LibraryStaff** - represents a library employee. Manages member accounts, books, and library operations.
- **Library** - represents the library itself. It has operating hours, location, and tracks members and books.

The system also includes:
- **LibraryUser** (Abstract Class) - shared base class for Member and LibraryStaff, holds common attributes like name, id, email, and phone.
- **Borrowable** (Interface) - implemented by Book to enforce checkout/return behavior.
- **Authentication** (Interface) - implemented by Member and LibraryStaff to enforce login behavior.

Custom exceptions for error handling:
- **InvalidPinException** - thrown when a PIN is null or empty.
- **BookNotFoundException** - thrown when a book cannot be found.
- **MemberNotFoundException** - thrown when a member cannot be found.

## Installation Instructions
On your machine in the terminal window do the following (we assume you installed git):

1. mkdir NewDirForProjectGrading
2. cd NewDirForProjectGrading
3. Clone the repository: `git clone https://github.com/angelinary/CS151-Project2-Library`
4. Open the project in IntelliJ IDEA
5. Make sure JDK 23 is configured
6. Run `Main.java` to start the program. Use 018134168 for user ID, and 00 for PIN. 

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
| Team Member            | Classes                            | Responsibilities                   |
|_________________________________________________________________________________________________
| Yanin Arevalos | LibraryStaff, Loan, Authentication interface, InvalidPinException, BookNotFoundException, MemberNotFoundException | Staff menu, loan tracking, fines, authentication, exception handling, unit tests |
| Angelina Ryabechenkova | LibraryUser, Library, Main, Member | Create Library, create member menu, manage members, sorting and book display, login tracking, functionality, system integration |
| Keyi Tan | Book, Catalog, Borrowable interface | Book management, catalog search, borrowable behavior, book checkout and return |
| Cagla Sah | LibraryCard, Reservation | Library card management, study room reservations |
