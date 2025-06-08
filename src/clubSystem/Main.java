package clubSystem;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ClubSystem system = new ClubSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.print("1. Student Login\n2. Admin Login\n3. Register Student\n4. Register Club Admin\n5. Edit Login details\n6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            case 1 -> handleStudentLogin(system, scanner);
            case 2 -> handleAdminLogin(system, scanner);
            case 3 -> handleStudentRegistration(system, scanner);
            case 4 -> handleAdminRegistration(system, scanner); 
            case 5 -> handleLoginEdits(system,scanner);
            case 6 -> {
            	System.out.println("Exit successful! ");
                scanner.close();
                return;
            }
            default->
            	System.out.println("Invalid choice. Please try again!");
            	
        }

        }
    }

    private static void handleStudentLogin(ClubSystem system, Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        Student student = system.studentLogin(email, password);
        if (student == null) {
            System.out.println("Invalid credentials.");
            return;
        }
        studentMenu(system, student, scanner);
    }

    private static void handleAdminLogin(ClubSystem system, Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        ClubAdmin admin = system.adminLogin(email, password);
        if (admin != null) adminMenu(system, admin, scanner);
        else System.out.println("Invalid credentials.");
    }

    private static void handleStudentRegistration(ClubSystem system, Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        system.registerStudent(email, phone, password, name);
        System.out.println("Student registered.");
        
        
    }
    
    private static void handleAdminRegistration(ClubSystem system, Scanner scanner) {
        System.out.print("Admin Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Club ID: ");
        String clubId = scanner.nextLine();
        
        ClubAdmin admin = new ClubAdmin(email, phone, password, clubId);
        system.registerAdmin(admin);
        
        // Ask for club details
        System.out.print("Club Name: ");
        String clubName = scanner.nextLine();
        System.out.print("Club Description: ");
        String description = scanner.nextLine();
        System.out.print("Contact Email: ");
        String contactEmail = scanner.nextLine();

        system.registerClub(clubName, description, contactEmail, admin);
        System.out.println("Admin and club registered.");
    }
    
    private static void handleLoginEdits(ClubSystem system, Scanner scanner) {
        System.out.print("Are you a:\n1. Student\n2. Club Admin\n");
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine();

        User loggedInUser = null;

        if (userTypeChoice == 1) {
            // Student verification
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String pass = scanner.nextLine();

            Student student = system.studentLogin(email, pass);
            if (student == null) {
                System.out.println("Invalid credentials.");
                return;
            }
            loggedInUser = student;
        } else if (userTypeChoice == 2) {
            // Admin verification
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String pass = scanner.nextLine();

            ClubAdmin admin = system.adminLogin(email, pass);
            if (admin == null) {
                System.out.println("Invalid credentials.");
                return;
            }
            loggedInUser = admin;
        } else {
            System.out.println("Invalid user type.");
            return;
        }

        
        System.out.print("1. Change Email\n2. Change Password\n3. Change Phone Number\nChoice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter your phone number: ");
                String phone = scanner.nextLine();
                System.out.print("Enter your password: ");
                String pass = scanner.nextLine();

                if (loggedInUser.verifyPhoneAndPassword(phone, pass)) {
                    System.out.print("Enter new email: ");
                    loggedInUser.setEmail(scanner.nextLine());
                    System.out.println("Email updated successfully.");
                } else {
                    System.out.println("Verification failed.");
                }
            }
            case 2 -> {
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();
                System.out.print("Enter your phone number: ");
                String phone = scanner.nextLine();

                if (loggedInUser.verifyEmailAndPhone(email, phone)) {
                    System.out.print("Enter new password: ");
                    loggedInUser.setPassword(scanner.nextLine());
                    System.out.println("Password updated successfully.");
                } else {
                    System.out.println("Verification failed.");
                }
            }
            case 3 -> {
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();
                System.out.print("Enter your password: ");
                String pass = scanner.nextLine();

                if (loggedInUser.verifyEmailAndPassword(email, pass)) {
                    System.out.print("Enter new phone number: ");
                    loggedInUser.setPhoneNumber(scanner.nextLine());
                    System.out.println("Phone number updated successfully.");
                } else {
                    System.out.println("Verification failed.");
                }
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    	
    




    private static void studentMenu(ClubSystem system, Student student, Scanner scanner) {
        while (true) {
            System.out.print("1. View Clubs \n2. Express Interest \n3. View Interested Clubs \n4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> system.getAllClubs().forEach(club ->
                        System.out.println(club.getClubId() + ": " + club.getName() + " - " + club.getDescription()));
                case 2 -> {
                    System.out.print("Enter club ID: ");
                    String clubId = scanner.nextLine();
                    Club club = system.getAllClubs().stream()
                            .filter(c -> c.getClubId().equals(clubId))
                            .findFirst().orElse(null);
                    if (club != null) {
                        system.submitInterest(student, club);
                        System.out.println("Interest submitted.");
                    } else {
                        System.out.println("Club not found.");
                    }
                }
                case 3 -> {
                	System.out.println("Your Interested Clubs:");
                	for (Club club : student.getInterestedClubs()) {
                	    System.out.println("- " + club.getName());
                	}
                }
                case 4 -> { return; }
                
                default->
                	System.out.println("Invalid choice. Please try again!");
                	
            }
        }
    }

    private static void adminMenu(ClubSystem system, ClubAdmin admin, Scanner scanner) {
        Club club = admin.getClub();

        if (club == null) {
            System.out.println("You don't have a club registered yet. Let's create one.");
            System.out.print("Club ID: ");
            String clubId = scanner.nextLine();
            System.out.print("Club Name: ");
            String name = scanner.nextLine();
            System.out.print("Description: ");
            String description = scanner.nextLine();
            System.out.print("Contact Email: ");
            String contactEmail = scanner.nextLine();

            club = new Club(clubId, name, description, contactEmail, admin);
            admin.setClub(club);
            system.addClub(club); // Add to system’s list
            System.out.println("Club registered successfully.");
        }

        while (true) {
            System.out.print("1. Update Club Info\n2. View Interested Students\n3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                	
                    club.updateDetails();
                    System.out.println("Updation complete.");
                }
                case 2 -> {
                	if (club.getInterestedStudents().isEmpty()) {
                	    System.out.println("No students have expressed interest yet.");
                	}
                	else {
	                    for (Student s : club.getInterestedStudents()) {
	                        System.out.println(s.getName() + ": " + s.getEmail());
	                    }
                	} 
                }
                case 3 -> { return; }
                
                default ->
                	System.out.println("Invalid choice. Please try again!");
                	
            }
        }
    }

}

