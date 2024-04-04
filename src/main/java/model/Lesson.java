package src.main.java.model;

import src.main.java.enums.DayOfWeek;
import src.main.java.enums.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private DayOfWeek day;
    private TimeSlot timeSlot;
    private int gradeLevel;
    private Coach coach;
    private int capacity;
    private List<Learner> bookedLearners;

    public Lesson(DayOfWeek day, TimeSlot timeSlot, int gradeLevel, Coach coach) {
        this.day = day;
        this.timeSlot = timeSlot;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.capacity = 4;
        this.bookedLearners = new ArrayList<>();
    }

    public DayOfWeek getDay() {
        return day;
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

    public List<Learner> getBookedLearners() {
        return bookedLearners;
    }

    public boolean hasVacancy() {
        return bookedLearners.size() < capacity;
    }

//    public boolean bookLesson(Learner learner) {
//        if (hasVacancy() && learner.canBookLesson(gradeLevel)) {
//            bookedLearners.add(learner);
//            return true;
//        }
//        return false;
//    }

    public boolean cancelBooking(Learner learner) {
        return bookedLearners.remove(learner);
    }

    public void addReview(Learner learner, int rating, String review) {
        // Logic to store review and rating for learner
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "day=" + day +
                ", timeSlot=" + timeSlot +
                ", gradeLevel=" + gradeLevel +
                ", coach=" + coach +
                ", bookedLearners=" + bookedLearners.size() + "/" + capacity +
                '}';
    }
}


