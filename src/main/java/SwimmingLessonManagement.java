package src.main.java;

import src.main.java.enums.DayOfWeek;
import src.main.java.enums.TimeSlot;
import src.main.java.model.Coach;
import src.main.java.model.Learner;
import src.main.java.model.Lesson;
import src.main.java.model.TimetableItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwimmingLessonManagement {

    private List<Lesson> lessons;
    private List<Learner> learners;
    private List<Coach> coaches;
    private List<TimetableItem> timetableItems;

    public List<Learner> getLearners() {
        return learners;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public SwimmingLessonManagement() {
        this.lessons = new ArrayList<>();
        this.learners = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.timetableItems = new ArrayList<>();
    }

    public void createSampleLessons() {
        // Weekday time slots
        addLesson(DayOfWeek.MONDAY, TimeSlot.M4_5PM, 0, coaches.get(0));
        addLesson(DayOfWeek.MONDAY, TimeSlot.M5_6PM, 1, coaches.get(1));
        addLesson(DayOfWeek.MONDAY, TimeSlot.M6_7PM, 2, coaches.get(2));
        addLesson(DayOfWeek.WEDNESDAY, TimeSlot.M4_5PM, 3, coaches.get(0));
        addLesson(DayOfWeek.WEDNESDAY, TimeSlot.M5_6PM, 2, coaches.get(1));
        addLesson(DayOfWeek.WEDNESDAY, TimeSlot.M6_7PM, 1, coaches.get(2));
        addLesson(DayOfWeek.FRIDAY, TimeSlot.M4_5PM, 0, coaches.get(0));
        addLesson(DayOfWeek.FRIDAY, TimeSlot.M5_6PM, 3, coaches.get(1));
        addLesson(DayOfWeek.FRIDAY, TimeSlot.M6_7PM, 2, coaches.get(2));
        // Saturday time slots
        addLesson(DayOfWeek.SATURDAY, TimeSlot.S2_3PM, 1, coaches.get(0));
        addLesson(DayOfWeek.SATURDAY, TimeSlot.S3_4PM, 3, coaches.get(1));
    }

    private void createSampleTimetable() {
        LocalDate date = LocalDate.now();

// Define weekdays and weekend separately
        int[] weekdays = {0, 1, 2};
        int[] weekend = {6, 7};

        for (int week = 1; week <= 4; week++) {
            // Loop through weekdays
            for (int day : weekdays) {
                addTimeTableItem(day, date);
            }

            // Loop through weekend days (add 2 days for each)
            for (int day : weekend) {
                addTimeTableItem(day, date.plusDays(2));
            }

            // Update date to next week's beginning (add 7 days)
            date = date.plusDays(7);
        }
    }

    private void addLesson(DayOfWeek day, TimeSlot timeSlot, int gradeLevel, Coach coach) {
        lessons.add(new Lesson(day, timeSlot, gradeLevel, coach));
    }

    private void addTimeTableItem(int lessonId, LocalDate date) {
        timetableItems.add(new TimetableItem(lessons.get(lessonId), date));
    }

    private Coach getAvailableCoach() {
        // Logic to assign available coach to the lesson (can be random selection)
        for (Coach coach : coaches) {
            // Check if coaches have any lessons assigned for the current day
            boolean hasLessons = false;
            for (Lesson lesson : lessons) {
                if (lesson.getDay() == DayOfWeek.MONDAY && lesson.getCoach().equals(coach)) {
                    hasLessons = true;
                    break; // Exit inner loop if lesson found for the day
                }
            }
            // If coach has no lessons assigned for the current day, return them
            if (!hasLessons) {
                return coach;
            }
        }

        // If no available coach found, return null (handle this case in calling method)
        System.out.println("Warning: No available coach found for this lesson.");
        return null;
    }

    public void addCoach() {
        coaches.add(new Coach("Alice"));
        coaches.add(new Coach("Bob"));
        coaches.add(new Coach("Charlie"));
    }

    public void addLearner(String name, String gender, int age, String emergencyContact, int gradeLevel) {
        if (isValidLearner(age, gradeLevel)) {
            learners.add(new Learner(name, gender, age, emergencyContact, gradeLevel, 0, 0, 0));
        } else {
            System.out.println("Invalid learner details. Age must be between 4 and 11, grade level between 0 and 5.");
        }
    }

    private boolean isValidLearner(int age, int gradeLevel) {
        return age >= 4 && age <= 11 && gradeLevel >= 0 && gradeLevel <= 5;
    }

    public void viewTimetableByDay(DayOfWeek day) {
        System.out.println("Timetable for Day: " + day);
        for (Lesson lesson : lessons) {
            if (lesson.getDay() == day) {
                System.out.println(lesson);
            }
        }
    }

    public void viewTimetableByGrade(int gradeLevel) {
        System.out.println("Timetable for Grade: " + gradeLevel);
        for (Lesson lesson : lessons) {
            if (lesson.getGradeLevel() == gradeLevel) {
                System.out.println(lesson);
            }
        }
    }

    public void viewTimetableByCoach(String coachName) {
        System.out.println("Timetable for src.main.java.model.Coach: " + coachName);
        for (Lesson lesson : lessons) {
            if (lesson.getCoach().getName().equals(coachName)) {
                System.out.println(lesson);
            }
        }
    }

    public void viewTimeTable() {

        System.out.println("+--------+----------+------------+--------+-----------------+-----------------+");
        System.out.println("| Day     | Time Slot| Grade Level | Coach  | Booked Learners | Date");
        System.out.println("+--------+----------+------------+--------+-----------------+-----------------+");

        String currentDay = "";
        for (TimetableItem timetableItem : timetableItems) {
            String day = timetableItem.getLesson().getDay().toString();
            if (!day.equals(currentDay)) {
                System.out.println("+--------+----------+------------+--------+-----------------+-----------------+");  // Print a separator for different days
                currentDay = day;
            }

            System.out.printf("| %-8s | %-10s | %-12s | %-7s | %-15s | %-15s |\n",
                    day,
                    timetableItem.getLesson().getTimeSlot(),
                    timetableItem.getLesson().getGradeLevel(),
                    timetableItem.getLesson().getCoach().getName(),
                    timetableItem.getLesson().getBookedLearners() + "/" + "4",  // Assuming a capacity of 4
                    timetableItem.getLessonDate()
            );
        }

        System.out.println("+--------+----------+------------+--------+-----------------+-----------------+");

//        System.out.println("TimeTable");
//        for (TimetableItem timetableItem : timetableItems) {
//            System.out.println(timetableItem);
//        }
    }

    public void viewLesson() {
        System.out.println("Lessons");
        for (Lesson lesson : lessons) {
            System.out.println(lesson.toString());
        }
    }

//    public boolean bookLesson(Learner learner, int gradeLevel, DayOfWeek day, TimeSlot timeSlot) {
//        for (Lesson lesson : lessons) {
//            if (lesson.getDay() == day && lesson.getTimeSlot() == timeSlot && lesson.getGradeLevel() == gradeLevel) {
//                if (lesson.bookLesson(learner)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public boolean cancelBooking(Learner learner, DayOfWeek day, TimeSlot timeSlot) {
        for (Lesson lesson : lessons) {
            if (lesson.getDay() == day && lesson.getTimeSlot() == timeSlot) {
                return lesson.cancelBooking(learner);
            }
        }
        return false;
    }

    public void printLearnerReport(Learner learner) {
        int booked = 0;
        int cancelled = 0;
        int attended = 0;

        System.out.println("src.main.java.model.Learner Report - " + learner.getName());
        for (Lesson lesson : lessons) {
            if (lesson.getBookedLearners().contains(learner)) {
                booked++;
                if (true) {
                    attended++;
                } else {
                    cancelled++;
                }
            }
        }
        System.out.println("Booked Lessons: " + booked);
        System.out.println("Cancelled Lessons: " + cancelled);
        System.out.println("Attended Lessons: " + attended);
    }

    public void printCoachReport() {
        System.out.println("src.main.java.model.Coach Report");
        for (Coach coach : coaches) { // Iterate through learners list assuming it also stores coaches
            double totalRating = 0.0;
            int ratingCount = 0;
            for (Lesson lesson : lessons) {
                if (lesson.getCoach().equals(coach)) {
                    for (Learner learner : lesson.getBookedLearners()) {
                        // Logic to get rating from learner for this lesson's coach
                        totalRating += getRating(learner, lesson);
                        ratingCount++;
                    }
                }
            }
            if (ratingCount > 0) {
                double averageRating = totalRating / ratingCount;
                System.out.println("src.main.java.model.Coach: " + coach.getName() + ", Average Rating: " + averageRating);
            } else {
                System.out.println("src.main.java.model.Coach: " + coach.getName() + ", No Ratings Available.");
            }
        }
    }

    public void printMenu() {
        System.out.println("Enter Your Choice: ");
        System.out.println("1 - View Time Table");
        System.out.println("2 - View Lessons");
        System.out.println("3 - Book A session");
        System.out.println("4 - Cancel the session");
        System.out.println("5 - Add Student");
        System.out.println("6 - Exit");
    }

    private double getRating(Learner learner, Lesson lesson) {
        // Implement logic to retrieve rating provided by learner for the specific lesson's coach
        return 0.0;
    }

    public static void main(String[] args) {
        SwimmingLessonManagement management = new SwimmingLessonManagement();
        management.addCoach();
        management.createSampleLessons();
        management.createSampleTimetable();

        // Add sample learners (assuming you have functions to get user input)
        management.addLearner("Dhananjali", "F", 8, "1234567890", 2);
        management.addLearner("Chandupa", "M", 6, "9876543210", 0);

        // Booking and cancellation examples (assuming you have functions to get user input for options)
//        management.bookLesson(management.getLearners().get(0), 2, src.main.java.enums.DayOfWeek.WEDNESDAY, src.main.java.enums.TimeSlot.M5_6PM);
//        management.cancelBooking(management.getLearners().get(1), src.main.java.enums.DayOfWeek.MONDAY, src.main.java.enums.TimeSlot.M4_5PM);
//
//        // Generate reports after 4 weeks
//        for (src.main.java.model.Learner learner : management.getLearners()) {
//            management.printLearnerReport(learner);
//        }
//        management.printCoachReport();
        boolean notexit = true;
        while (notexit) {
            management.printMenu();
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object

            int choice = Integer.parseInt(myObj.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("View Time Table");
                    management.viewTimeTable();
                    break;
                case 2:
                    System.out.println("View Lessons");
                    management.viewLesson();
                    break;
                case 3:
                    System.out.println("Book A session");
                    break;
                case 4:
                    System.out.println("Cancel the session");
                    break;
                case 5:
                    System.out.println("Add Student");
                    break;
                case 6:
                    System.out.println("Review a Lesson");
                    break;
                case 7:
                    System.out.println("Print Report");
                    break;
                case 8:
                    notexit = false;
                    break;
            }
        }
    }


}

