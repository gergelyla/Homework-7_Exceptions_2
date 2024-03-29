package RepositoriesAndActions;

import CalculationsValidations.AgeValidationException;
import CalculationsValidations.IdValidationException;
import CalculationsValidations.ValidationException;
import ObjectDefinitions.Gender;
import ObjectDefinitions.Student;

import java.time.LocalDate;
import java.time.Period;

import java.util.*;

public class StudentRepositorySorted<T extends Student> implements IStudentRepository<T> {
    private Set<Student> studentRepositorySorted = new TreeSet<Student>(new StudentLastNameComparator());

    @Override
    public void add(Student obj) throws ValidationException {
        try {
            validateInput(obj);
            studentRepositorySorted.add(obj);
            System.out.print("New repository: ");
            printRepository();
        } finally {
        }
    }

    @Override
    public void delete(String id) throws IdValidationException {
        try {
            System.out.println("Deleting student: " + validateId(id));
            studentRepositorySorted.remove(validateId(id));
            System.out.println("New repository: ");
            printRepository();
            //System.out.println(studentRepositorySorted);
        } finally {
        }

    }

    @Override
    public void retrieveByAge(String age) throws AgeValidationException {
        boolean existingStudent = false;
        Student studentOfAge;
        int studentsAge = 0;
        try {
            ageValid(age);
            System.out.println("List of students that are of the age " + age + ":");
            Iterator membersOfSortedRepository = studentRepositorySorted.iterator();
            while (membersOfSortedRepository.hasNext()) {
                studentOfAge = (Student) membersOfSortedRepository.next();
                studentsAge = calculateAge(studentOfAge);
                if (studentsAge == Integer.parseInt(age)) {
                    System.out.println(studentOfAge + " age: " + studentsAge);
                    existingStudent = true;
                }
            }
            if (existingStudent == false) {
                System.out.println("No student by the age of " + age);
            }
        } finally {
        }
    }

    @Override
    public void listByLastName() {
        printRepository();
    }

    @Override
    public void listByDateOfBirth() {
        Set<Student> listByDateOfBirth = new TreeSet<Student>(new StudentBirthDayComparator());
        Iterator membersOfSortedRepository = studentRepositorySorted.iterator();
        while (membersOfSortedRepository.hasNext()) {
            listByDateOfBirth.add((Student) membersOfSortedRepository.next());
        }
        Iterator membersOfListByDateOfBirth = listByDateOfBirth.iterator();
        while (membersOfListByDateOfBirth.hasNext()) {
            System.out.println(membersOfListByDateOfBirth.next());
        }
        //System.out.println(listByDateOfBirth);
    }

    private boolean ageValid(String age) throws AgeValidationException {
        boolean validAge = true;
        try {
            int intAge = Integer.parseInt(age);
            if (18 > intAge) {
                validAge = false;
                throw new AgeValidationException("Age must be 18 or more!");
            }
            return validAge;
        } catch (NumberFormatException e) {
            throw new AgeValidationException("Age should not be empty!");
        } finally {
        }
    }

    private boolean birthDateValid(Student obj) {
        boolean validBirthDate = false;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (1900 <= obj.getDateOfBirth().getYear() && obj.getDateOfBirth().getYear() <= (currentYear - 18)) {
            validBirthDate = true;
        }
        return validBirthDate;
    }

    private Student validateId(String id) throws IdValidationException {
        boolean existingStudent = false;

        if (id.length() != 13) {
            throw new IdValidationException("ID number must have 13 digits! ");
        }
        Student deletedStudent = new Student();
        Iterator membersOfSortedRepository = studentRepositorySorted.iterator();
        while (membersOfSortedRepository.hasNext()) {
            deletedStudent = (Student) membersOfSortedRepository.next();
            if (!deletedStudent.getId().equals(null) && deletedStudent.getId().equals(id)) {
                existingStudent = true;
                break;
            }
        }
        if (existingStudent == false) {
            throw new IdValidationException("Student does not exist!");
        }
        return (deletedStudent);
    }

    private void validateInput(Student obj) throws ValidationException {
        if (obj.getFirstName().equals(null) || obj.getFirstName().length() == 0) {
            throw new ValidationException("Missing first name! Repository remains unchanged!");
        }
        if (obj.getLastName().equals(null) || obj.getLastName().length() == 0) {
            throw new ValidationException("Missing last name! Repository remains unchanged!");
        }
        if (!obj.getGender().equals(Gender.MALE) && !obj.getGender().equals(Gender.FEMALE)) {
            throw new ValidationException("Not a valid gender! Repository remains unchanged!");
        }
        if (obj.getDateOfBirth().equals(null)/*||obj.getDateOfBirth().length()==0*/) {
            throw new NullPointerException("Birth date unparseble! Repository remains unchanged!");
        }
        if (birthDateValid(obj) == false) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            throw new ValidationException("Birthdate must be within the year 1900 and " + (currentYear - 18) + "! Repository remains unchanged!");
        }
        if (obj.getId().equals(null) || obj.getId().length() == 0) {
            throw new ValidationException("Missing ID number! Repository remains unchanged!");
        }
    }

    private int calculateAge(Student obj) {
        int studentAge = 0;

        /*String[] birthDate=obj.getDateOfBirth().split("/",0 );*/
        LocalDate today = LocalDate.now();
        Period p = Period.between(obj.getDateOfBirth(), today);
        studentAge = p.getYears();
        return studentAge;
    }

    private void printRepository() {
        Iterator membersOfSortedRepository = studentRepositorySorted.iterator();
        while (membersOfSortedRepository.hasNext()) {
            System.out.println(membersOfSortedRepository.next());
        }
    }

}
