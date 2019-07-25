package ObjectDefinitions;


public class Student implements Comparable<Student>{
    private String firstName;
    private String lastName;
    private Gender gender;
    private String dateOfBirth;
    private String id;

    public Student(){

    }

    public Student(String firstName, String lastName, Gender gender, String dateOfBirth, String id){
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.dateOfBirth=dateOfBirth;
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student: " + firstName +" "+ lastName +", gender: " + gender +", date of birth: "+dateOfBirth+
                ", id number: " + id;
    }

    @Override
    public int compareTo(Student o) {
        return lastName.compareTo(o.getLastName());
    }
}
