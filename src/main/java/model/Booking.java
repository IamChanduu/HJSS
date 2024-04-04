package src.main.java.model;

import java.time.LocalDate;

public class Booking {
    private Learner learner;
    private Lesson lesson;
    private LocalDate startDate;
    private LocalDate endDate;

    public Learner getLearner() {
        return learner;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Booking(Learner learner, Lesson lesson, LocalDate startDate, LocalDate endDate) {
        this.learner = learner;
        this.lesson = lesson;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking() {
    }

    @Override
    public String toString() {
        return "Booking{" +
                "learner=" + learner +
                ", lesson=" + lesson +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
