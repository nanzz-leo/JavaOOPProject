package clubSystem;
import java.util.*;
class Club {
    private String clubId;
    private String name;
    private String description;
    private String contactEmail;
    private ClubAdmin admin;
    private List<Student> interestedStudents;
    
    Scanner scanner = new Scanner(System.in);

    public Club(String clubId, String name, String description, String contactEmail, ClubAdmin admin) {
        this.clubId = clubId;
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.admin = admin;
        this.interestedStudents = new ArrayList<>();
        admin.setClub(this); 
    }

    public void addInterestedStudent(Student student) {
        interestedStudents.add(student);
    }

    public void updateDetails(String name, String description, String contactEmail) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
    }
    
    public void updateName(String name) {
        this.name = name;
    }
    
    public void updateDescription(String description) {
        this.description = description;
    }
    
    public void updateEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void updateDetails() {	//method overloading
    	System.out.println("Select the information to be updated: ");
    	System.out.println("1. Club Name \n2. Club Description \n3. Contact Email \n4. All details");
    	
    	int choice  = scanner.nextInt();
    	scanner.nextLine();
    	
    	
    	switch(choice) {
        
        case 1 -> {
        	System.out.print("Enter new Name: ");
        	String name = scanner.nextLine();
        	updateName(name);
        	System.out.println("Name updated.");
        }
        
        case 2 -> {
        	System.out.print("Enter new description: ");
        	String description = scanner.nextLine();
        	updateDescription(description);
        	System.out.println("Description updated.");
        }
        
        case 3 -> {
        	System.out.print("Enter new Email: ");
        	String email = scanner.nextLine();
        	updateEmail(email);
        	System.out.println("Email updated.");
        }
        
        case 4 -> {
        	System.out.print("New Club Name: ");
            String name = scanner.nextLine();
            System.out.print("New Description: ");
            String desc = scanner.nextLine();
            System.out.print("New Contact Email: ");
            String email = scanner.nextLine();
            updateDetails(name, desc, email);
            System.out.println("Club updated.");
        }
        default ->
        	System.out.println("Invalid choice. Please try again!");
    	}
        	
        
        
    }
    public String getClubId() { return clubId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getContactEmail() { return contactEmail; }
    public ClubAdmin getAdmin() { return admin; }
    public List<Student> getInterestedStudents() { return interestedStudents; }
}
