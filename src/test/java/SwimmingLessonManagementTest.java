package src.test.java;

import org.junit.jupiter.api.Test;
import src.main.java.SwimmingLessonManagement;
import src.main.java.enums.DayOfWeek;
import src.main.java.enums.TimeSlot;
import src.main.java.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SwimmingLessonManagementTest {
    @Test
    void sortByTimetableID() {
        SwimmingLessonManagement management = new SwimmingLessonManagement();
        List<TimetableItem> timetableItems = new ArrayList<>();

        management.addLesson(2, DayOfWeek.MONDAY, TimeSlot.M4_5PM, 0, new Coach("Bob"));
        management.addLesson(3, DayOfWeek.MONDAY, TimeSlot.M5_6PM, 1, new Coach("Alice"));
        management.addLesson(1, DayOfWeek.MONDAY, TimeSlot.M6_7PM, 2, new Coach("Bob"));

        // Add items with different TimetabelItemId values
        timetableItems.add(new TimetableItem(2, management.getLessons().get(0), LocalDate.now()));
        timetableItems.add(new TimetableItem(3, management.getLessons().get(0), LocalDate.now()));
        timetableItems.add(new TimetableItem(1, management.getLessons().get(0), LocalDate.now()));

        // Call the method to be tested
        management.sortByTimetableID(timetableItems);

        // Assert the expected order
        assertEquals(List.of(1, 2, 3), timetableItems.stream().map(timetableItem -> timetableItem.getTimetabelItemId()).collect(Collectors.toList()));
    }

    @Test
    void addCoach() {
        SwimmingLessonManagement management = new SwimmingLessonManagement();

        // Call the method to add coaches
        management.addCoach();

        // Assert the expected coaches are added
        List<Coach> expectedCoaches = Arrays.asList(new Coach("Alice"), new Coach("Bob"), new Coach("Charlie"));
        assertIterableEquals(List.of("Alice", "Bob", "Charlie"), management.getCoaches().stream().map(coach -> coach.getName()).collect(Collectors.toList()));
    }

    @Test
    void addLearner() {
        SwimmingLessonManagement management = new SwimmingLessonManagement();

        // Valid learner details
        String name = "Alice";
        String gender = "Female";
        String email = "alice@example.com";
        int age = 8;
        String emergencyContact = "123-456-7890";
        int gradeLevel = 3;

        // Call the addLearner method
        management.addLearner(name, gender, email, age, emergencyContact, gradeLevel);

        // Assert that the learner was added
        assertEquals(1, management.getLearners().size());  // Assuming a getter for learners
        Learner addedLearner = management.getLearners().get(0);
        assertAll(
                () -> assertEquals(name, addedLearner.getName()),
                () -> assertEquals(gender, addedLearner.getGender()),
                () -> assertEquals(email, addedLearner.getEmaill()),
                () -> assertEquals(age, addedLearner.getAge()),
                () -> assertEquals(emergencyContact, addedLearner.getEmergencyContact()),
                () -> assertEquals(gradeLevel, addedLearner.getGradeLevel())
        );
    }

    @Test
    void bookLesson() {
        // Create instances (assuming class names)
        SwimmingLessonManagement management = new SwimmingLessonManagement();
        String name = "Alice";
        String gender = "Female";
        String email = "alice@example.com";
        int age = 8;
        String emergencyContact = "123-456-7890";
        int gradeLevel = 3;

        // Call the addLearner method
        management.addLearner(name, gender, email, age, emergencyContact, gradeLevel);

        // Add learner and timetable item (assuming setters for learners and timetableItems)
        management.addLesson(1, DayOfWeek.MONDAY, TimeSlot.M6_7PM, 2, new Coach("Bob"));
        management.addTimeTableItem(1, 0, LocalDate.now());
        // Call bookLesson
        management.bookLesson("alice@example.com", "1");

        // Assertions
        assertEquals(1, management.getBookings().size());
    }

    @Test
    void cancelBooking() {
        // Create instances (assuming class names)
        SwimmingLessonManagement management = new SwimmingLessonManagement();
        String name = "Alice";
        String gender = "Female";
        String email = "alice@example.com";
        int age = 8;
        String emergencyContact = "123-456-7890";
        int gradeLevel = 3;

        // Call the addLearner method
        management.addLearner(name, gender, email, age, emergencyContact, gradeLevel);

        // Add learner and timetable item (assuming setters for learners and timetableItems)
        management.addLesson(1, DayOfWeek.MONDAY, TimeSlot.M6_7PM, 2, new Coach("Bob"));
        management.addTimeTableItem(1, 0, LocalDate.now());
        // Call bookLesson
        management.bookLesson("alice@example.com", "1");
        management.cancelBooking(0);
        assertEquals(0, management.getBookings().size());
    }
}