package org.IronSchool;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InitialMenu{

    public static String statStringFilter(String statName) {
        Scanner scanner = new Scanner(System.in);
        boolean  invalidStat= false;
        String stat=null;
        while (!invalidStat && stat==null) {
            System.out.println("Enter a "+statName+" that must not be a null string or an empty string");
            stat = scanner.nextLine().trim();
            if (!stat.isEmpty()) {
                invalidStat = true;
            }else {
                System.err.println("The "+statName+" must not be a null string or an empty string");
                stat=null;
            }
        }
        return stat.substring(0,1).toUpperCase()+stat.substring(1).toLowerCase();
    }

    public static int statIntegerFilter(String statName) {
        Scanner scanner = new Scanner(System.in);
        boolean  invalidStat= false;
        int stat=0;
        while (!invalidStat) {
            try {
                System.out.println("Enter a number of "+statName+" which must be a positive integer");
                stat = scanner.nextInt();
                if (stat >0) {
                    invalidStat = true;
                }else {
                    System.err.println("The number of "+statName+" must be a positive integer");
                }
            } catch (InputMismatchException e) {
                System.err.println("The number of "+statName+" must be a positive integer");
                scanner.nextLine();
            }
        }
        return stat;
    }


    //1
    public static String askingNameSchool() {
        return statStringFilter("School name");
    }

    //2
    public static int askedNumberTeachersCreated() {
        return statIntegerFilter("Teachers");
    }

    //3
    public static List<Teacher> enterDetailsEachTeacher(int numberTeachers) {
        List<Teacher> teachers=new ArrayList<>();
        for(int i=0; i<numberTeachers;i++){
            System.out.println("================================");
            System.out.println("Enter the data of Teacher "+(i+1));
            System.out.println("================================");
            String firstName= statStringFilter("Teacher first name");
            String lastName= statStringFilter("Teacher last name");
            String fullName=firstName+" "+lastName;
            double salary=statIntegerFilter("salary");
            teachers.add(new Teacher(fullName,salary));
        }
        return teachers;
    }

    //4
    public static int askedNumberCoursesCreated() {
        return statIntegerFilter("Courses");
    }

    //5
    public static List<Course> enterDetailsEachCourse(int numberCourses) {
        List<Course> courses=new ArrayList<>();
        for(int i=0; i<numberCourses;i++){
            System.out.println("================================");
            System.out.println("Enter the data of Course "+(i+1));
            System.out.println("================================");
            String name= statStringFilter("Course name");
            double price=statIntegerFilter("price");
            courses.add(new Course(name,price));
        }
        return courses;
    }

    //6
    public static int askedNumberStudentsCreated() {
        return statIntegerFilter("Student");
    }

    //7
    public static List<Student> enterDetailsEachStudent(int numberStudents) {
        List<Student> students=new ArrayList<>();
        for(int i=0; i<numberStudents;i++){
            System.out.println("================================");
            System.out.println("Enter the data of Student "+(i+1));
            System.out.println("================================");
            String firstName= statStringFilter("Student first name");
            String lastName= statStringFilter("Student last name");
            String fullName=firstName+" "+lastName;
            String address= statStringFilter("Student address");
            String email="";
            while (!email.contains("@")) {
                email=statStringFilter("Student email");
                if(!email.contains("@")){
                    System.err.println("The email must have a symbol '@'");
                }
            }
            students.add(new Student(fullName,address,email));
        }
        return students;
    }


    public static void showCourses() {
        List<Course> courseList=School.getCourseList();
        System.out.println("List of courses:");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println((i+1) + ": " + courseList.get(i).getName() + " - $" + courseList.get(i).getPrice());
        }
    }

    public static void showTeachers() {
        List<Teacher> teacherList=School.getTeacherList()
        System.out.println("List of teachers:");
        for (int i = 0; i < teacherList.size(); i++) {
            System.out.println((i + 1) + ": " + teacherList.get(i).getName() + " - $" + teacherList.get(i).getSalary());
        }
    }

    public static void showStudents() {
        List<Student> studentList=School.getStudentList()
        System.out.println("List of students:");
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println((i+1) + ": " + studentList.get(i).getName() + " - "+ studentList.get(i).getEmail());
        }
    }

    public static void enrollStudent(int studentId, int courseId) {
        try {
            if (studentId > School.getStudentList().size() || courseId > School.getCourseList().size()) {
                throw new Exception("Invalid student or course ID");
            } else {
                Student student = School.getStudentList().get(studentId);
                Course course = School.getCourseList().get(courseId);
                student.addCourse(course);
                course.addStudent(student);
                course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());
                System.out.println("Student " + student.getName() + " has been enrolled in " + course.getName() + " course.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    public static void assignTeacher(int teacherId, int courseId) {
        try {
            if (teacherId > School.getTeacherList().size() || courseId > School.getCourseList().size()) {
                throw new Exception("Invalid teacher or course ID");
            } else {
                Teacher teacher = School.getTeacherList().get(teacherId);
                Course course = School.getCourseList().get(courseId);
                teacher.addCourse(course);
                course.addTeacher(teacher);
                course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());
                System.out.println("Teacher " + teacher.getName() + " has been enrolled in " + course.getName() + " course.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

public static void lookupCourse(int courseId){
    Course course = School.getCourseList().get(courseId); // implementa un método para buscar un curso por su ID
    if (course != null) {
        System.out.println("ID: " + course.getCourseId());
        System.out.println("Name: " + course.getName());
        System.out.println("Price: " + course.getPrice());
        System.out.println("Money earned: " + course.getMoneyEarned());
        if (course.getTeacher() != null) {
            System.out.println("Teacher: " + course.getTeacher().getName());
        } else {
            System.out.println("Teacher: Not assigned");
        }
    } else {
        System.out.println("Course not found");
    }
}
    public static void lookupStudent(int studentId){
        Student student = School.getStudentList().get(studentId);
        if (student != null) {
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Address: " + student.getAddress());
            System.out.println("Email: " + student.getEmail());
            if (student.getListCourses() != null) {
                System.out.println("Course: " + student.getListCourses());
            } else {
                System.out.println("Course: Not enrolled");
            }
        } else {
            System.out.println("Student not found");
        }


    }

    public static void showProfit(List<Course> courseList, List<Teacher> teacherList) {
        double totalMoneyEarned = 0;
        double totalSalaries = 0;
        for (Course c : courseList) {
            totalMoneyEarned += c.getMoneyEarned();
        }
        for (Teacher t : teacherList) {
            totalSalaries += t.getSalary();
        }
        double profit = totalMoneyEarned - totalSalaries;
        System.out.println("The total profit is: " + profit);
    }

    public static void lookupTeacher(List<Teacher> teacherList, int teacherId) {
        for (Teacher t : teacherList) {
            if (t.getTeacherId() == teacherId) {
                System.out.println("Teacher ID: " + t.getTeacherId());
                System.out.println("Name: " + t.getName());
                System.out.println("Salary: " + t.getSalary());
                return;
            }
        }
        System.out.println("Teacher not found.");
    }



}





