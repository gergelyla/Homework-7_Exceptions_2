import ObjectDefinitions.Gender;
import ObjectDefinitions.Student;
import CalculationsValidations.AgeValidationException;
import CalculationsValidations.IdValidationException;
import RepositoriesAndActions.StudentRepositorySorted;
import CalculationsValidations.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.log(Level.INFO, "ENTER MAIN");
        StudentRepositorySorted<Student> studentRepositorySorted = createStudentStudentRepositorySorted();

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Creating students and populating repository if input valid (ordered by last name):");
        System.out.println("-------------------------------------------------------------------------------------------");
        LOGGER.log(Level.INFO, "Creating students and populating repository if input valid (ordered by last name)");

        Student student1 = new Student("Laszlo", "Gergely", Gender.Male, stringToDate("1980-12-22"), "1801222125794");
        Student student2 = new Student("Ioana", "Popescu", Gender.Female, stringToDate("1989-05-10"), "2890510125794");
        Student student3 = new Student("Liviu", "Coman", Gender.Male, stringToDate("1966-10-05"), "1661005355794");
        Student student4 = new Student("Ana", "Stancu", Gender.Female, stringToDate("2000-07-09"), "2000709345824");
        Student student5 = new Student("George", "Niculescu", Gender.Male, stringToDate("1975-01-17"), "1750117579437");


        addStudentsToRepository(studentRepositorySorted, student1);
        addStudentsToRepository(studentRepositorySorted, student2);
        addStudentsToRepository(studentRepositorySorted, student3);
        addStudentsToRepository(studentRepositorySorted, student4);
        addStudentsToRepository(studentRepositorySorted, student5);

        String id = "1661005355794";
        deleteStudentById(studentRepositorySorted, id);

        String age = "17";
        listBySpecificAge(studentRepositorySorted, age);

        listByLastName(studentRepositorySorted);

        listByDateOfBirth(studentRepositorySorted);
    }

    private static LocalDate stringToDate(String stringDate) throws DateTimeParseException {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(stringDate, dateFormatter);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static void listByDateOfBirth(StudentRepositorySorted<Student> studentRepositorySorted) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("List students ordered by date of birth:");
        System.out.println("-------------------------------------------------------------------------------------------");
        LOGGER.log(Level.INFO, "List students ordered by date of birth name");
        studentRepositorySorted.listByDateOfBirth();
    }

    private static void listByLastName(StudentRepositorySorted<Student> studentRepositorySorted) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("List students ordered by last name:");
        System.out.println("-------------------------------------------------------------------------------------------");
        LOGGER.log(Level.INFO, "List students ordered by last name");
        studentRepositorySorted.listByLastName();
    }

    private static void listBySpecificAge(StudentRepositorySorted<Student> studentRepositorySorted, String age) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Retrieve students of certain age:");
        System.out.println("-------------------------------------------------------------------------------------------");
        LOGGER.log(Level.INFO, "Retrieve students of certain age");
        System.out.println("Searching for students of age " + age + " ...");
        try {
            studentRepositorySorted.retrieveByAge(age);
        } catch (AgeValidationException e) {
            LOGGER.log(Level.SEVERE, "Input not valid! " + e.getMessage());
            System.out.println("Input not valid! " + e.getMessage());
        }
    }

    private static void deleteStudentById(StudentRepositorySorted<Student> studentRepositorySorted, String id) {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Deleting student based on ID number:");
        System.out.println("-------------------------------------------------------------------------------------------");
        LOGGER.log(Level.INFO, "Deleting student based on ID number");
        System.out.println("Introduced ID number: " + id + " Checking validity...");
        try {
            studentRepositorySorted.delete(id);
        } catch (IdValidationException e) {
            System.out.println("ID number not valid! " + e.getMessage() + " Input correct ID number! ");
            LOGGER.log(Level.SEVERE, "Input not valid! " + e.getMessage());
        }
    }

    private static void addStudentsToRepository(StudentRepositorySorted<Student> studentRepositorySorted, Student student) {
        try {
            studentRepositorySorted.add(student);
            LOGGER.log(Level.INFO, "Student succesfully added!");
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Birth date unparseble! Mandatory format: yyyy-MM-dd ");
        } catch (ValidationException e) {
            LOGGER.log(Level.SEVERE, "Input not valid! " + e.getMessage());
        }
    }

    private static StudentRepositorySorted<Student> createStudentStudentRepositorySorted() {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Creating student repository:");
        System.out.println("-------------------------------------------------------------------------------------------");
        LOGGER.log(Level.INFO, "Creating student repository");
        StudentRepositorySorted<Student> studentRepositorySorted = new StudentRepositorySorted<>();
        System.out.println(studentRepositorySorted);
        return studentRepositorySorted;
    }
}
