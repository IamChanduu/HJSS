package src.main.java.model;

public class Learner {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int gradeLevel;
    private int bookedLessons;
    private int canceledLessons;
    private int attendedLessons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public int getBookedLessons() {
        return bookedLessons;
    }

    public void setBookedLessons(int bookedLessons) {
        this.bookedLessons = bookedLessons;
    }

    public int getCanceledLessons() {
        return canceledLessons;
    }

    public void setCanceledLessons(int canceledLessons) {
        this.canceledLessons = canceledLessons;
    }

    public int getAttendedLessons() {
        return attendedLessons;
    }

    public void setAttendedLessons(int attendedLessons) {
        this.attendedLessons = attendedLessons;
    }

    public Learner(String name, String gender, int age, String emergencyContact, int gradeLevel, int bookedLessons, int canceledLessons, int attendedLessons) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.gradeLevel = gradeLevel;
        this.bookedLessons = bookedLessons;
        this.canceledLessons = canceledLessons;
        this.attendedLessons = attendedLessons;
    }

    public Learner() {
    }

    @Override
    public String toString() {
        return "Learner{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", gradeLevel=" + gradeLevel +
                ", bookedLessons=" + bookedLessons +
                ", canceledLessons=" + canceledLessons +
                ", attendedLessons=" + attendedLessons +
                '}';
    }
}
