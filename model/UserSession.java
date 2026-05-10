package model;

// Kullanıcı bilgilerini ve seçilen senaryoyu tutar.
public class UserSession {
    private String username;
    private String school;
    private String sessionName;

    private String qualityType;
    private String mode;
    private Scenario selectedScenario;

    public UserSession() {
        this.username = "";
        this.school = "";
        this.sessionName = "";
        this.qualityType = "";
        this.mode = "";
        this.selectedScenario = null;
    }

    // Profile ekranındaki bilgileri kaydeder.
    public void setProfileInformation(String username, String school, String sessionName) {
        this.username = username;
        this.school = school;
        this.sessionName = sessionName;
    }

    // Define ekranındaki seçimleri kaydeder.
    public void setDefinitionInformation(String qualityType, String mode, Scenario selectedScenario) {
        this.qualityType = qualityType;
        this.mode = mode;
        this.selectedScenario = selectedScenario;
    }

    public String getUsername() {
        return username;
    }

    public String getSchool() {
        return school;
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getQualityType() {
        return qualityType;
    }

    public String getMode() {
        return mode;
    }

    public Scenario getSelectedScenario() {
        return selectedScenario;
    }
}