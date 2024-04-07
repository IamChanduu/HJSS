package src.main.java.model;

import java.time.LocalDate;

public class Booking {
    int bookingId;
    private Learner learner;
    private Lesson lesson;
    private LocalDate startDate;


    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Learner getLearner() {
        return learner;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public LocalDate getStartDate() {
        return startDate;
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


    public Booking() {
    }

    public Booking(int bookingId, Learner learner, Lesson lesson, LocalDate startDate) {
        this.bookingId = bookingId;
        this.learner = learner;
        this.lesson = lesson;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", learner=" + learner +
                ", lesson=" + lesson +
                ", startDate=" + startDate +
                '}';
    }
}
