package clubSystem;


import java.util.*;

class ClubSystem {
    private List<Student> students;
    private List<Club> clubs;
    private List<ClubAdmin> admins;
    private Datastorage storage;

    public ClubSystem() {
        this.storage = new FileDataStorage();
        this.admins = storage.loadClubAdmins();
        this.students = storage.loadStudents();
        this.clubs = storage.loadClubs(admins);
    }

    public Student studentLogin(String email, String password) {
        return students.stream()
            .filter(s -> s.authenticate(email, password))
            .findFirst().orElse(null);
    }

    public ClubAdmin adminLogin(String email, String password) {
        return admins.stream()
            .filter(a -> a.authenticate(email, password))
            .findFirst().orElse(null);
    }
    
    
    public void registerStudent(String email, String phoneNumber, String password, String name) {
        Student student = new Student(email, phoneNumber, password, name);
        students.add(student);
        storage.saveStudent(student);
    }
    public void registerAdmin(ClubAdmin admin) {
        admins.add(admin);
        storage.saveAdmin(admin);
    }

    public void registerClub(String name, String description, String contactEmail, ClubAdmin admin) {
        String clubId = admin.getClubId();
        Club club = new Club(clubId, name, description, contactEmail, admin);
        clubs.add(club);
        storage.saveClub(club);
    }

    
    public void addClub(Club club) {
        clubs.add(club);
    }


    public List<Club> getAllClubs() {
        return new ArrayList<>(clubs);
    }

    public void submitInterest(Student student, Club club) {
        student.expressInterest(club);
        storage.saveStudentInterest(student, club);
    }
}

