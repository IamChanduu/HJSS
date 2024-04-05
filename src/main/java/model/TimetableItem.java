package src.main.java.model;

import java.time.LocalDate;

public class TimetableItem {
    int timetabelItemId;
    private Lesson lesson;
    private LocalDate lessonDate;

    public int getTimetabelItemId() {
        return timetabelItemId;
    }

    public void setTimetabelItemId(int timetabelItemId) {
        this.timetabelItemId = timetabelItemId;
    }

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

    public TimetableItem() {
    }

    public TimetableItem(int timetabelItemId, Lesson lesson, LocalDate lessonDate) {
        this.timetabelItemId = timetabelItemId;
        this.lesson = lesson;
        this.lessonDate = lessonDate;
    }

    @Override
    public String toString() {
        return "TimetableItem{" +
                "timetabelItemId=" + timetabelItemId +
                ", lesson=" + lesson +
                ", lessonDate=" + lessonDate +
                '}';
    }
}
