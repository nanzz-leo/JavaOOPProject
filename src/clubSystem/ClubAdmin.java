package clubSystem;

public class ClubAdmin extends User {
    private String clubId;
    private Club club;

    public ClubAdmin(String email, String phoneNumber, String password, String clubId) {
        super(email, phoneNumber, password);
        this.clubId = clubId;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return getEmail().equals(email) && getPassword().equals(password);
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    
    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
