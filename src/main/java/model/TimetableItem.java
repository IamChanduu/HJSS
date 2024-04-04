package src.main.java.model;

import java.time.LocalDate;

public class TimetableItem {
    private Lesson lesson;
    private LocalDate lessonDate;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(LocalDate lessonDate) {
        this.lessonDate = lessonDate;
    }

    public TimetableItem(Lesson lesson, LocalDate lessonDate) {
        this.lesson = lesson;
        this.lessonDate = lessonDate;
    }

    public TimetableItem() {
    }

    @Override
    public String toString() {
        return "TimetableItem{" +
                "lesson=" + lesson +
                ", lessonDate=" + lessonDate +
                '}';
    }
}
