package Model;

public class Patient {

    private long CPR;
    private String emailAddress;
    private boolean isApproved;

    public Patient() {}

    public Patient(long CPR, String emailAddress, boolean isApproved) {
        this.CPR = CPR;
        this.emailAddress = emailAddress;
        this.isApproved = isApproved;
    }

    public long getCPR() {
        return CPR;
    }

    public void setCPR(long CPR) {
        this.CPR = CPR;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

}

