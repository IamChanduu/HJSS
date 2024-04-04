package src.main.java.model;

public class Learner {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int gradeLevel;

    public Learner(String name, String gender, int age, String emergencyContact, int gradeLevel) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.gradeLevel = gradeLevel;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public boolean canBookLesson(int lessonGrade) {
        return gradeLevel == lessonGrade || gradeLevel + 1 == lessonGrade;
    }

    @Override
    public String toString() {
        return "src.main.java.model.Learner{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", gradeLevel=" + gradeLevel +
                '}';
    }
}
