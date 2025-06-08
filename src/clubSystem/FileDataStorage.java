package clubSystem;

import java.util.*;
import java.io.*;

class FileDataStorage implements Datastorage {
    private static final String STUDENTS_FILE = "students.txt";
    private static final String CLUBS_FILE = "clubs.txt";
    private static final String ADMINS_FILE = "admins.txt";
    private static final String INTERESTS_FILE = "interests.txt";
    
    
    public FileDataStorage() {
        try {
            createFileIfNotExists(STUDENTS_FILE);
            createFileIfNotExists(ADMINS_FILE);
            createFileIfNotExists(CLUBS_FILE);
            createFileIfNotExists(INTERESTS_FILE);
        } catch (IOException e) {
            System.out.println("Error creating data files: " + e.getMessage());
        }
    }

    private void createFileIfNotExists(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile(); // Only creates if it doesn't exist
        }
    }

    @Override
    public void saveStudent(Student student) {
        try (PrintWriter out = new PrintWriter(new FileWriter(STUDENTS_FILE, true))) {
            out.println(student.getEmail() + "," + student.getPhoneNumber() + "," +
                       student.getPassword() + "," + student.getName());
        } catch (IOException e) {
            System.err.println("Error saving student: " + e.getMessage());
        }
    }

    @Override
    public void saveClub(Club club) {
        try (PrintWriter out = new PrintWriter(new FileWriter(CLUBS_FILE, true))) {
            out.println(club.getClubId() + "," + club.getName() + "," +
                       club.getDescription() + "," + club.getContactEmail() + "," +
                       club.getAdmin().getEmail());
        } catch (IOException e) {
            System.err.println("Error saving club: " + e.getMessage());
        }
    }

    @Override
    public void saveAdmin(ClubAdmin admin) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMINS_FILE, true))) {
            writer.write(admin.getEmail() + "," + admin.getPhoneNumber() + "," + admin.getPassword() + "," + admin.getClubId());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving admin: " + e.getMessage());
        }
}

    @Override
    public List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    students.add(new Student(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    @Override
    public List<ClubAdmin> loadClubAdmins() {
        List<ClubAdmin> admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMINS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    admins.add(new ClubAdmin(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading admins: " + e.getMessage());
        }
        return admins;
    }

    @Override
    public List<Club> loadClubs(List<ClubAdmin> admins) {
        List<Club> clubs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CLUBS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    ClubAdmin admin = admins.stream()
                            .filter(a -> a.getEmail().equals(parts[4]))
                            .findFirst().orElse(null);
                    if (admin != null) {
                        clubs.add(new Club(parts[0], parts[1], parts[2], parts[3], admin));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading clubs: " + e.getMessage());
        }
        return clubs;
    }

    @Override
    public void saveStudentInterest(Student student, Club club) {
        try (PrintWriter out = new PrintWriter(new FileWriter(INTERESTS_FILE, true))) {
            out.println(student.getEmail() + "," + club.getClubId());
        } catch (IOException e) {
            System.err.println("Error saving interest: " + e.getMessage());
        }
    }
    
  
    
}
