package service;

import dao.BookDAO;
import dao.BorrowDAO;
import dao.StudentDAO;
import dto.Book;
import dto.Borrow;
import dto.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * 비즈니스 로직을 처리하는 서비스 클래스
 */
public class LibraryService {
    private final BookDAO BOOK_DAO = new BookDAO();
    private final StudentDAO STUDENT_DAO = new StudentDAO();
    private final BorrowDAO BORROW_DAO = new BorrowDAO();

    // 책을 추가하는 서비스
    public void addBook(Book book) throws SQLException {
        // 입력값 유효성 검사
        if (book.getTitle() == null || book.getAuthor() == null || book.getTitle().trim().isEmpty()
                || book.getAuthor().trim().isEmpty()) {
            throw new SQLException("도서 제목과 저자는 필수입력 항목입니다.");
        }
        // 유효성 검사 통과후 BookDAO 에게 일을 협력 요청 한다.
        BOOK_DAO.addBook(book);
    }

    // 책을 전체 조회하는 서비스
    public List<Book> getAllBooks() throws SQLException {

        return BOOK_DAO.getAllBooks();
    }

    // 책 이름으로 조회하는 서비스
    public List<Book> searchBooksByTitle(String title) throws SQLException {
        // 입력값 유효성 검사
        if (title == null || title.trim().isEmpty()) {
            throw new SQLException("검색 제목을 입력 해 주세요.");
        }

        return BOOK_DAO.searchBooksTitle(title);
    }

    // 학생을 추가하는 서비스 기능
    public void addStudent(Student studnt) throws SQLException {
        // 유효성 검사 (직접 구현)

        STUDENT_DAO.addStudent(studnt);
    }

    // 전체 학생 목록을 조회 하는 서비스
    public List<Student> getAllStudents() throws SQLException {

        return STUDENT_DAO.getAllStudents();
    }

    // 도서를 대출하는 서비스
    public void borrowBook(int bookId, int studentId) throws SQLException {
        // 유효성 검사
        if (bookId <= 0 || studentId <= 0) {
            throw new SQLException("유효한 도서 ID와 학생 ID를 입력 해 주세요.");
        }
        // borrowDAO 객체에게 협력 요청하고,
        // borrows 테이블에 insert 처리에 책임은 BorrowDAO 객체가 가진다
        BORROW_DAO.borrowBook(bookId,studentId);
    }

    // 현재 대출중인 도서 목록을 보여주는 서비스
    public List<Borrow> getBorrowedBooks() throws SQLException {

        return BORROW_DAO.getBorrowedBooks();
    }

    // 도서 대출을 반납하는 서비스
    public void returnBook(int bookId, int studentId) throws SQLException {
        // 유효성 검사
        if (bookId <= 0 || studentId <= 0) {
            throw new SQLException("유효한 도서 ID와 학생 ID를 입력 해 주세요.");
        }

        BORROW_DAO.returnBook(bookId,studentId);
    }

    // 학생 ID로 학생 인증하는 서비스
    public Student authenticateStudent(String studentId) throws SQLException {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new SQLException("학번을 입력해주세요.");
        }

        return STUDENT_DAO.authenticateStudent(studentId);
    }
}
