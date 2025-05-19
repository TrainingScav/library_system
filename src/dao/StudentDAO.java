package dao;

import dto.Student;
import util.DatabaseUtil;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDAO {

    // 새 학생을 데이터베이스에 추가하는 기능
    public void addStudent(Student student) throws SQLException {
        // 쿼리문

        String sql = "INSERT INTO students (name, student_id) values (?, ?)";

        try (Connection conn = DatabaseUtil.getConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getStudentId());

            pstmt.executeUpdate();
        }
    }

    // 모든 학생 목록을 조회하는 기능
    public List<Student> getAllStudents() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM STUDENTS";

        try (Connection conn = DatabaseUtil.getConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String studentId = rs.getString("student_id");

                Student student = new Student(id, name, studentId);
                studentList.add(student);
            }

        }

        return studentList;
    }


    // 학생 Student_ID로 학생 인증(로그인용) 기능 만들기
    public Student authenticateStudent(String searchStudentId) throws SQLException {
        //List<Student> studentList = new ArrayList<>();
        Student searchStudentInfo = new Student();
        String sql = "SELECT * FROM STUDENTS WHERE student_id = ? ";

        try(Connection conn = DatabaseUtil.getConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, searchStudentId);

            ResultSet rs = pstmt.executeQuery();
            boolean isGetSearchedRows = false;
            while (rs.next()) {
                isGetSearchedRows = true;
                searchStudentInfo.setId(rs.getInt("id"));
                searchStudentInfo.setName(rs.getString("name"));
                searchStudentInfo.setStudentId(rs.getString("student_id"));
            }

            if (!isGetSearchedRows) {
                //System.out.println("조회된 정보가 없습니다.");
                return null;
            }
        }
        // 학생이 정확한 학번을 입력하면 Student 객체가 만들어져서 리턴 됨

        // 학생이 잘못된 학번을 입력하면 null 값을 반환함

        return searchStudentInfo;
    }

    // 테스트 코드
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("학생 정보관리 프로그램");
            System.out.println("1.학생정보 등록, 2.학생정보 전체조회, 3.특정 학생정보 조회, 4.프로그램 종료");
            System.out.print("입력 : ");
            String selectedNum = sc.nextLine();

            if (selectedNum.trim().equals("1")){
                System.out.println("등록할 학생의 이름을 입력 해주세요.");
                System.out.print("입력 : ");
                String targetName = sc.nextLine();
                System.out.println("등록할 학생의 학생ID를 입력 해주세요.");
                System.out.print("입력 : ");
                String targetStudentId = sc.nextLine();

                try {
                    Student student = new Student();
                    student.setName(targetName);
                    student.setStudentId(targetStudentId);
                    studentDAO.addStudent(student);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else if (selectedNum.trim().equals("2")){

            } else if (selectedNum.trim().equals("3")){

            } else if (selectedNum.trim().equals("4")){

            } else {
                System.out.println("정확한 값을 입력 해주세요.");
            }
        }

        /*
        Student student1 = new Student();
        student1.setName("이순신");
        student1.setStudentId("20250002");

        try {
            studentDAO.addStudent(student1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
         */

        /*
        try {
            for (int i = 0; i < studentDAO.getAllStudents().size(); i++) {
                System.out.println(studentDAO.getAllStudents().get(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */

        /*
        try {
            Student studentInfo = studentDAO.authenticateStudent("20250002");

            if (studentInfo == null) {
                System.out.println("조회된 정보가 없습니다.");
            } else {
                System.out.println("id : " + studentInfo.getId());
                System.out.println("name : " + studentInfo.getName());
                System.out.println("studentId : " + studentInfo.getStudentId());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */

    }
}
