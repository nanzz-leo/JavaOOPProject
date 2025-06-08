package clubSystem;
import java.util.*;
class Student extends User {
    private String name;
    private List<Club> interestedClubs;

    public Student(String email, String phoneNumber, String password, String name) {
        super(email, phoneNumber, password);
        this.name = name;
        this.interestedClubs = new ArrayList<>();
    }

    public void expressInterest(Club club) {
        interestedClubs.add(club);
        club.addInterestedStudent(this);
    }
    
    public List<Club> getInterestedClubs() {
        return interestedClubs;
    }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
   

    @Override
    public boolean authenticate(String email, String password) {
        return getEmail().equals(email) && getPassword().equals(password);
    }
}
