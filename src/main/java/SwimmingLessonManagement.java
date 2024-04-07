package src.main.java;

import src.main.java.enums.DayOfWeek;
import src.main.java.enums.TimeSlot;
import src.main.java.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SwimmingLessonManagement {

    private List<Lesson> lessons;
    private List<Learner> learners;
    private List<Coach> coaches;
    private List<TimetableItem> timetableItems;
    private List<Booking> bookings;

    public List<Learner> getLearners() {
        return learners;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public SwimmingLessonManagement() {
        this.lessons = new ArrayList<>();
        this.learners = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.timetableItems = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void createSampleLessons() {
        // Weekday time slots
        addLesson(1, DayOfWeek.MONDAY, TimeSlot.M4_5PM, 0, coaches.get(0));
        addLesson(2, DayOfWeek.MONDAY, TimeSlot.M5_6PM, 1, coaches.get(1));
        addLesson(3, DayOfWeek.MONDAY, TimeSlot.M6_7PM, 2, coaches.get(2));
        addLesson(4, DayOfWeek.WEDNESDAY, TimeSlot.M4_5PM, 3, coaches.get(0));
        addLesson(5, DayOfWeek.WEDNESDAY, TimeSlot.M5_6PM, 2, coaches.get(1));
        addLesson(6, DayOfWeek.WEDNESDAY, TimeSlot.M6_7PM, 1, coaches.get(2));
        addLesson(7, DayOfWeek.FRIDAY, TimeSlot.M4_5PM, 0, coaches.get(0));
        addLesson(8, DayOfWeek.FRIDAY, TimeSlot.M5_6PM, 3, coaches.get(1));
        addLesson(9, DayOfWeek.FRIDAY, TimeSlot.M6_7PM, 2, coaches.get(2));
        // Saturday time slots
        addLesson(10, DayOfWeek.SATURDAY, TimeSlot.S2_3PM, 1, coaches.get(0));
        addLesson(11, DayOfWeek.SATURDAY, TimeSlot.S3_4PM, 3, coaches.get(1));
    }

    private void createSampleTimetable() {
        LocalDate date = LocalDate.now();

// Define weekdays and weekend separately
        int[] weekdays = {0, 1, 2};
        int[] weekend = {3, 4};
        int ID = 0;
        for (int week = 1; week <= 4; week++) {
            // Loop through weekdays
            for (int day : weekdays) {
                addTimeTableItem(ID, day, date);
                ID++;
            }

            // Loop through weekend days (add 2 days for each)
            for (int day : weekend) {
                addTimeTableItem(ID, day, date.plusDays(2));
                ID++;
            }

            // Update date to next week's beginning (add 7 days)
            date = date.plusDays(7);
        }
    }

    public void addLesson(int id, DayOfWeek day, TimeSlot timeSlot, int gradeLevel, Coach coach) {
        lessons.add(new Lesson(id, day, timeSlot, gradeLevel, coach));
    }

    public void addTimeTableItem(int id, int lessonId, LocalDate date) {
        timetableItems.add(new TimetableItem(id, lessons.get(lessonId), date));
    }

    public void sortByTimetableID(List<TimetableItem> list) {
        Collections.sort(list, (o1, o2) -> Integer.compare(o1.getTimetabelItemId(), o2.getTimetabelItemId()));
    }

    public void addCoach() {
        coaches.add(new Coach("Alice"));
        coaches.add(new Coach("Bob"));
        coaches.add(new Coach("Charlie"));
    }

    public void addLearner(String name, String gender, String email, int age, String emergencyContact, int gradeLevel) {
        if (isValidLearner(age, gradeLevel)) {
            learners.add(new Learner(name, gender, email, age, emergencyContact, gradeLevel, 0, 0, 0));
        } else {
            System.out.println("Invalid learner details. Age must be between 4 and 11, grade level between 0 and 5.");
        }
    }

    private boolean isValidLearner(int age, int gradeLevel) {
        return age >= 4 && age <= 11 && gradeLevel >= 0 && gradeLevel <= 5;
    }
    public void viewTimeTable() {

        System.out.println("+--------+--------+----------+------------+--------+-----------------+-----------------+");
        System.out.println("| ID     | Day     | Time Slot| Grade Level | Coach  | Booked Learners | Date");
        System.out.println("+--------+--------+----------+------------+--------+-----------------+-----------------+");
        sortByTimetableID(timetableItems);
        String currentDay = "";
        for (TimetableItem timetableItem : timetableItems) {
            String day = timetableItem.getLesson().getDay().toString();
            if (!day.equals(currentDay)) {
                System.out.println("+--------+--------+----------+------------+--------+-----------------+-----------------+");  // Print a separator for different days
                currentDay = day;
            }

            System.out.printf("| %-3s | %-8s | %-10s | %-12s | %-7s | %-15s | %-15s |\n",
                    timetableItem.getTimetabelItemId(),
                    day,
                    timetableItem.getLesson().getTimeSlot(),
                    timetableItem.getLesson().getGradeLevel(),
                    timetableItem.getLesson().getCoach().getName(),
                    timetableItem.getLesson().getBookedLearners().size() + "/" + "4",  // Assuming a capacity of 4
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
        System.out.println("+-----+----------------------+-------------------+---------+-----------+----------+");
        System.out.println("| ID  | Day                 | Time Slot         | Grade  | Coach     | Capacity |");
        for (Lesson lesson : lessons) {
            System.out.println("+----------------------+-------------------+---------+-----------+----------+");
            System.out.printf("| %-5s | %-20s | %-18s | %-6d | %-9s | %-8d |\n",
                    lesson.getLessonId(), lesson.getDay().toString(), lesson.getTimeSlot(), lesson.getGradeLevel(), lesson.getCoach().getName(), lesson.getCapacity());
            System.out.println("+----------------------+-------------------+---------+-----------+----------+");
        }
    }

    public void bookLesson(String email, String timetableid) {
        try {
            Optional<Learner> precentLearner = learners.stream().filter(learner -> learner.getEmaill().equals(email)).findAny();
            Optional<TimetableItem> optionalTimetableItem = timetableItems.stream().filter(timetableItem -> timetableItem.getTimetabelItemId() == Integer.parseInt(timetableid)).findAny();
            if (precentLearner.isPresent() && optionalTimetableItem.isPresent()) {
                List<Learner> bookedLearners = optionalTimetableItem.get().getLesson().getBookedLearners();
                bookedLearners.add(precentLearner.get());
                optionalTimetableItem.get().getLesson().setBookedLearners(bookedLearners);
                if (bookings.isEmpty()) {
                    bookings.add(new Booking(0, precentLearner.get(), optionalTimetableItem.get().getLesson(), optionalTimetableItem.get().getLessonDate()));
                } else {
                    bookings.add(new Booking(bookings.get(bookings.size() - 1).getBookingId() + 1, precentLearner.get(), optionalTimetableItem.get().getLesson(), optionalTimetableItem.get().getLessonDate()));
                }
//      ---------------------------------------TimeTable update----------------------------------------------
                timetableItems.remove(optionalTimetableItem.get());
                timetableItems.add(optionalTimetableItem.get());
                System.out.println(timetableItems);
//      ---------------------------------------/TimeTable update----------------------------------------------
//      ---------------------------------------Learner update----------------------------------------------
                learners.remove(precentLearner);
                precentLearner.get().setBookedLessons(precentLearner.get().getBookedLessons() + 1);
                learners.add(precentLearner.get());
                System.out.println(learners.toString());
//      ---------------------------------------/Learner update----------------------------------------------
                System.out.println("Booking SuccessFull");
            } else {
                System.out.println("Learner Or Timetable is not available");
            }
        } catch (Exception e) {
            System.out.println("SomeThing Went Wrong" + e.getMessage().toString());
        }
    }

    public void cancelBooking(int bookingId) {
        this.bookings = bookings.stream().filter(booking -> booking.getBookingId() != bookingId).collect(Collectors.toList());
        System.out.println(this.bookings);
    }

    public void printMenu() {
        System.out.println("Enter Your Choice: ");
        System.out.println("1 - View Time Table");
        System.out.println("2 - View Lessons");
        System.out.println("3 - Book A session");
        System.out.println("4 - Cancel the session");
        System.out.println("5 - Add Student");
        System.out.println("6 - Review a Lesson");
        System.out.println("7 - Print Report");
        System.out.println("8 - Exit");
    }

    public static void main(String[] args) {
        SwimmingLessonManagement management = new SwimmingLessonManagement();
        management.addCoach();
        management.createSampleLessons();
        management.createSampleTimetable();

        // Add sample learners (assuming you have functions to get user input)
        management.addLearner("Dhananjali", "F", "Dhana@gmail.com", 8, "1234567890", 2);
        management.addLearner("Chandupa", "M", "chandu@gmail.com", 6, "9876543210", 0);

        boolean notexit = true;
        String email;
        int id;
        String description;
        int rating;
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
                    System.out.println("Enter your Email");
                    email = myObj.nextLine();
                    if (management.verifyLearner(email)) {
                        management.viewTimeTable();
                        System.out.println("");
                        System.out.println("Enter the Id of the timetable record you want to book: ");
                        String timetableid = myObj.nextLine();
                        management.bookLesson(email, timetableid);

                    }
                    break;
                case 4:
                    System.out.println("Cancel the session");
                    System.out.println("Enter your Email");
                    email = myObj.nextLine();
                    System.out.println(management.viewOwnBooking(email));
                    System.out.println("Enter booking ID: ");
                    id = Integer.parseInt(myObj.nextLine());
                    management.cancelBooking(id);
                    break;
                case 5:
                    System.out.println("Add Student");
                    System.out.println("Add Student Name");
                    String name = myObj.nextLine();
                    System.out.println("Add Student Gender");
                    String gender = myObj.nextLine();
                    System.out.println("Add Student Email");
                    String emailll = myObj.nextLine();
                    System.out.println("Add Student Age");
                    int age = Integer.parseInt(myObj.nextLine());
                    System.out.println("Add Student Emergency Contact");
                    String emergencyContact = myObj.nextLine();
                    System.out.println("Add Grade Level");
                    int gradeLevel = Integer.parseInt(myObj.nextLine());
                    management.addLearner(name, gender, emailll, age, emergencyContact, gradeLevel);
                    break;
                case 6:
                    System.out.println("Review a Lesson");
                    management.viewLesson();
                    System.out.println("Enter the Id of the Lesson you want to Review: ");
                    id = Integer.parseInt(myObj.nextLine());
                    System.out.println("Enter the Review Discription: ");
                    description = myObj.nextLine();
                    do {
                        System.out.println("Rating  (1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied) : ");
                        rating = Integer.parseInt(myObj.nextLine());
                    } while (rating < 0 || rating > 5);
                    management.reviewLesson(id, description, rating);
                    break;
                case 7:
                    System.out.println("Print Report");
                    management.printRepoert();
                    break;
                case 8:
                    notexit = false;
                    break;
            }
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               management.printRepoert();
                           }
                       }, 0, /* Initial delay */
                4 * 7 * 24 * 60 * 60 * 1000 /* 4 weeks in milliseconds */);
    }

    private void printRepoert() {
        System.out.println("| +-------- Learners Report ----------+");
        for (Learner learner : learners) {
            System.out.printf("Learner: %s ---> Canceled: %d, Booked: %d, Attended: %d%n",
                    learner.getName(),
                    learner.getCanceledLessons(),
                    learner.getBookedLessons(),
                    learner.getAttendedLessons());
        }
        System.out.println("| +----------------------------------+");
        System.out.println();
        System.out.println("| +-------- Coaches Report ----------+");
        for (Coach coach : coaches) {
            System.out.println(coach.getName() + " - " + coach.getRating().stream().mapToDouble(a -> a).average().orElse(0.0));
        }
        System.out.println("| +----------------------------------+");
    }

    private void reviewLesson(int lessionId, String description, int rating) {
        Optional<Lesson> selectedLesson = lessons.stream().filter(lesson -> lesson.getLessonId() == lessionId).findAny();
        List<Review> list = selectedLesson.get().getReviews();
        list.add(new Review(description, rating));
        selectedLesson.get().setReviews(list);
        List<Integer> coachRating = selectedLesson.get().getCoach().getRating();
        coachRating.add(rating);
        selectedLesson.get().getCoach().setRating(coachRating);
        System.out.println("SucessFully Created a Review");
        System.out.println(selectedLesson);
        System.out.println(coaches.stream().filter(coach -> coach.getName().equals(selectedLesson.get().getCoach().getName())).findFirst());
    }

    private List<Booking> viewOwnBooking(String email) {
        return bookings.stream()
                .filter(booking -> booking.getLearner().getEmaill().equals(email))
                .collect(Collectors.toList());
    }

    private boolean verifyLearner(String email) {
        return learners.stream().anyMatch(learner -> learner.getEmaill().equals(email));
    }


}

