package src.main.java;

import src.main.java.enums.DayOfWeek;
import src.main.java.enums.TimeSlot;
import src.main.java.model.Coach;
import src.main.java.model.Learner;
import src.main.java.model.Lesson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwimmingLessonManagement {

    private List<Lesson> lessons;
    private List<Learner> learners;
    private List<Coach> coaches;

    public List<Learner> getLearners() {
        return learners;
    }

    public SwimmingLessonManagement() {
        this.lessons = new ArrayList<>();
        this.learners = new ArrayList<>();
        this.coaches = new ArrayList<>();
    }

    public void createSampleTimetable() {
        // Design 4 weeks of timetable (44 lessons) covering all grades and coaches
        for (int week = 1; week <= 4; week++) {
            // Loop through weekdays and Saturdays
            for (DayOfWeek day : DayOfWeek.values()) {
                if (day == DayOfWeek.SATURDAY) {
                    // Saturday time slots
                    addLesson(day, TimeSlot.S2_3PM, 1, coaches.get(0));
                    addLesson(day, TimeSlot.S3_4PM, 3, coaches.get(1));
                } else {
                    // Weekday time slots
                    addLesson(day, TimeSlot.M4_5PM, 2, coaches.get(0));
                    addLesson(day, TimeSlot.M5_6PM, 0, coaches.get(0));
                    addLesson(day, TimeSlot.M6_7PM, 4, coaches.get(0));
                }
            }
        }

    }

    private void addLesson(DayOfWeek day, TimeSlot timeSlot, int gradeLevel, Coach coach) {
        lessons.add(new Lesson(day, timeSlot, gradeLevel, coach));
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
        coaches.add(new Coach("John"));
        coaches.add(new Coach("Alice"));
    }

    public void addLearner(String name, String gender, int age, String emergencyContact, int gradeLevel) {
        if (isValidLearner(age, gradeLevel)) {
            learners.add(new Learner(name, gender, age, emergencyContact, gradeLevel));
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
        System.out.println("TimeTable");
        for (Lesson lesson : lessons) {
            System.out.println(lesson);
        }
    }

    public void viewLesson(){
        System.out.println("Lessons");
        for (Lesson lesson : lessons){
            System.out.println(lesson);
        }
    }

    public boolean bookLesson(Learner learner, int gradeLevel, DayOfWeek day, TimeSlot timeSlot) {
        for (Lesson lesson : lessons) {
            if (lesson.getDay() == day && lesson.getTimeSlot() == timeSlot && lesson.getGradeLevel() == gradeLevel) {
                if (lesson.bookLesson(learner)) {
                    return true;
                }
            }
        }
        return false;
    }

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
        management.createSampleTimetable();
        management.printMenu();

        // Add sample learners (assuming you have functions to get user input)
        management.addLearner("Alice", "F", 8, "1234567890", 2);
        management.addLearner("Bob", "M", 6, "9876543210", 0);

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
                    notexit = false;
                    break;
            }
        }
    }
}

