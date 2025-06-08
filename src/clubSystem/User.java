package clubSystem;

abstract class User {
    private String email;
    private String phoneNumber;
    private String password;

    public User(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    
    public void setEmail(String email) {this.email =email;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public void setPassword(String password) {this.password = password;}

    public abstract boolean authenticate(String email, String password);
    
    public boolean verifyEmailAndPhone(String email, String phone) {
        return this.email.equals(email) && this.phoneNumber.equals(phone);
    }

    public boolean verifyEmailAndPassword(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public boolean verifyPhoneAndPassword(String phone, String password) {
        return this.phoneNumber.equals(phone) && this.password.equals(password);
    }
}