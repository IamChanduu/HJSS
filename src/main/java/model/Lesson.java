package src.main.java.model;

import src.main.java.enums.DayOfWeek;
import src.main.java.enums.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private int lessonId;
    private DayOfWeek day;
    private TimeSlot timeSlot;
    private int gradeLevel;
    private Coach coach;
    private int capacity;
    private List<Learner> bookedLearners;
    private List<Review> reviews;


    public Lesson(int lessonId, DayOfWeek day, TimeSlot timeSlot, int gradeLevel, Coach coach) {
        this.lessonId = lessonId;
        this.day = day;
        this.timeSlot = timeSlot;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.capacity = 4;
        this.bookedLearners = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public DayOfWeek getDay() {
        return day;
    }

    public int getLessonId() {
        return lessonId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setBookedLearners(List<Learner> bookedLearners) {
        this.bookedLearners = bookedLearners;
    }

    public List<Learner> getBookedLearners() {
        return bookedLearners;
    }

    public boolean hasVacancy() {
        return bookedLearners.size() < capacity;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public boolean cancelBooking(Learner learner) {
        return bookedLearners.remove(learner);
    }

    public void addReview(Learner learner, int rating, String review) {
        // Logic to store review and rating for learner
    }
    @Override
    public String toString() {
        return "Lesson{" +
                "lessonId=" + lessonId +
                ", day=" + day +
                ", timeSlot=" + timeSlot +
                ", gradeLevel=" + gradeLevel +
                ", coach=" + coach +
                ", capacity=" + capacity +
                ", bookedLearners=" + bookedLearners +
                ", reviews=" + reviews +
                '}';
    }
}


