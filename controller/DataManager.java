package controller;

import model.Dimension;
import model.HigherMetric;
import model.LowerMetric;
import model.Metric;
import model.Scenario;

import java.util.ArrayList;
import java.util.HashMap;

// Senaryo verilerini merkezi olarak yönetir.
public class DataManager {
    private static HashMap<String, Scenario> scenarios = new HashMap<>();

    // Senaryoları ilk kullanımda hazırlar.
    static {
        loadScenarios();
    }

    // Tüm hazır senaryolar burada oluşturulur.
    private static void loadScenarios() {
        addScenario(createEducationScenarioC());
        addScenario(createEducationScenarioD());
        addScenario(createHealthScenarioA());
        addScenario(createHealthScenarioB());
    }

    // HashMap'e senaryo ekler.
    private static void addScenario(Scenario scenario) {
        scenarios.put(scenario.getKey(), scenario);
    }

    // Seçilen mode'a göre senaryoları döndürür.
    public static ArrayList<Scenario> getScenariosByMode(String mode) {
        ArrayList<Scenario> result = new ArrayList<>();

        for (Scenario scenario : scenarios.values()) {
            if (scenario.getMode().equals(mode)) {
                result.add(scenario);
            }
        }

        return result;
    }

    // Mode ve senaryo adına göre senaryo bulur.
    public static Scenario getScenario(String mode, String scenarioName) {
        String key = mode + " - " + scenarioName;
        return scenarios.get(key);
    }

    // Tüm senaryoları döndürür.
    public static HashMap<String, Scenario> getAllScenarios() {
        return scenarios;
    }

    // Education - Scenario C veri seti.
    private static Scenario createEducationScenarioC() {
        ArrayList<Dimension> dimensions = new ArrayList<>();

        Dimension usability = new Dimension("Usability", 25);
        Metric susScore = new HigherMetric("SUS score", 50, 0, 100, "points");
        susScore.calculateScore(89);
        Metric onboardingTime = new LowerMetric("Onboarding time", 50, 0, 60, "min");
        onboardingTime.calculateScore(5);
        usability.addMetric(susScore);
        usability.addMetric(onboardingTime);

        Dimension performance = new Dimension("Performance Efficiency", 20);
        Metric videoStartTime = new LowerMetric("Video start time", 50, 0, 15, "sec");
        videoStartTime.calculateScore(2);
        Metric concurrentExams = new HigherMetric("Concurrent exams", 50, 0, 600, "users");
        concurrentExams.calculateScore(450);
        performance.addMetric(videoStartTime);
        performance.addMetric(concurrentExams);

        Dimension accessibility = new Dimension("Accessibility", 20);
        Metric wcagCompliance = new HigherMetric("WCAG compliance", 50, 0, 100, "%");
        wcagCompliance.calculateScore(98);
        Metric screenReaderScore = new HigherMetric("Screen reader score", 50, 0, 100, "%");
        screenReaderScore.calculateScore(85);
        accessibility.addMetric(wcagCompliance);
        accessibility.addMetric(screenReaderScore);

        Dimension reliability = new Dimension("Reliability", 20);
        Metric uptime = new HigherMetric("Uptime", 50, 95, 100, "%");
        uptime.calculateScore(99);
        Metric mttr = new LowerMetric("MTTR", 50, 0, 120, "min");
        mttr.calculateScore(45);
        reliability.addMetric(uptime);
        reliability.addMetric(mttr);

        Dimension functionalSuitability = new Dimension("Functional Suitability", 15);
        Metric featureCompletion = new HigherMetric("Feature completion", 50, 0, 100, "%");
        featureCompletion.calculateScore(90);
        Metric assignmentSubmitRate = new HigherMetric("Assignment submit rate", 50, 0, 100, "%");
        assignmentSubmitRate.calculateScore(92);
        functionalSuitability.addMetric(featureCompletion);
        functionalSuitability.addMetric(assignmentSubmitRate);

        dimensions.add(usability);
        dimensions.add(performance);
        dimensions.add(accessibility);
        dimensions.add(reliability);
        dimensions.add(functionalSuitability);

        return new Scenario("Scenario C - Team Alpha", "Education", "Product Quality", dimensions);
    }

    // Education - Scenario D veri seti.
    private static Scenario createEducationScenarioD() {
        ArrayList<Dimension> dimensions = new ArrayList<>();

        Dimension usability = new Dimension("Usability", 25);
        Metric susScore = new HigherMetric("SUS score", 50, 0, 100, "points");
        susScore.calculateScore(76);
        Metric onboardingTime = new LowerMetric("Onboarding time", 50, 0, 60, "min");
        onboardingTime.calculateScore(18);
        usability.addMetric(susScore);
        usability.addMetric(onboardingTime);

        Dimension performance = new Dimension("Performance Efficiency", 20);
        Metric pageLoadTime = new LowerMetric("Page load time", 50, 0, 10, "sec");
        pageLoadTime.calculateScore(4);
        Metric activeUsers = new HigherMetric("Active users", 50, 0, 1000, "users");
        activeUsers.calculateScore(700);
        performance.addMetric(pageLoadTime);
        performance.addMetric(activeUsers);

        Dimension accessibility = new Dimension("Accessibility", 20);
        Metric keyboardNavigation = new HigherMetric("Keyboard navigation score", 50, 0, 100, "%");
        keyboardNavigation.calculateScore(80);
        Metric contrastCompliance = new HigherMetric("Contrast compliance", 50, 0, 100, "%");
        contrastCompliance.calculateScore(74);
        accessibility.addMetric(keyboardNavigation);
        accessibility.addMetric(contrastCompliance);

        Dimension reliability = new Dimension("Reliability", 20);
        Metric uptime = new HigherMetric("Uptime", 50, 95, 100, "%");
        uptime.calculateScore(97);
        Metric errorRate = new LowerMetric("Error rate", 50, 0, 20, "%");
        errorRate.calculateScore(6);
        reliability.addMetric(uptime);
        reliability.addMetric(errorRate);

        Dimension functionalSuitability = new Dimension("Functional Suitability", 15);
        Metric quizCompletion = new HigherMetric("Quiz completion rate", 50, 0, 100, "%");
        quizCompletion.calculateScore(82);
        Metric materialAccess = new HigherMetric("Material access rate", 50, 0, 100, "%");
        materialAccess.calculateScore(88);
        functionalSuitability.addMetric(quizCompletion);
        functionalSuitability.addMetric(materialAccess);

        dimensions.add(usability);
        dimensions.add(performance);
        dimensions.add(accessibility);
        dimensions.add(reliability);
        dimensions.add(functionalSuitability);

        return new Scenario("Scenario D - Team Beta", "Education", "Product Quality", dimensions);
    }

    // Health - Scenario A veri seti.
    private static Scenario createHealthScenarioA() {
        ArrayList<Dimension> dimensions = new ArrayList<>();

        Dimension usability = new Dimension("Usability", 25);
        Metric patientSatisfaction = new HigherMetric("Patient satisfaction", 50, 0, 100, "points");
        patientSatisfaction.calculateScore(84);
        Metric appointmentTime = new LowerMetric("Appointment booking time", 50, 0, 30, "min");
        appointmentTime.calculateScore(8);
        usability.addMetric(patientSatisfaction);
        usability.addMetric(appointmentTime);

        Dimension performance = new Dimension("Performance Efficiency", 20);
        Metric recordLoadTime = new LowerMetric("Patient record load time", 50, 0, 12, "sec");
        recordLoadTime.calculateScore(3);
        Metric concurrentDoctors = new HigherMetric("Concurrent doctors", 50, 0, 300, "users");
        concurrentDoctors.calculateScore(210);
        performance.addMetric(recordLoadTime);
        performance.addMetric(concurrentDoctors);

        Dimension security = new Dimension("Security", 20);
        Metric accessControl = new HigherMetric("Access control score", 50, 0, 100, "%");
        accessControl.calculateScore(93);
        Metric failedLoginRate = new LowerMetric("Failed login rate", 50, 0, 20, "%");
        failedLoginRate.calculateScore(4);
        security.addMetric(accessControl);
        security.addMetric(failedLoginRate);

        Dimension reliability = new Dimension("Reliability", 20);
        Metric uptime = new HigherMetric("Uptime", 50, 95, 100, "%");
        uptime.calculateScore(98);
        Metric incidentRecovery = new LowerMetric("Incident recovery time", 50, 0, 180, "min");
        incidentRecovery.calculateScore(60);
        reliability.addMetric(uptime);
        reliability.addMetric(incidentRecovery);

        Dimension functionalSuitability = new Dimension("Functional Suitability", 15);
        Metric prescriptionAccuracy = new HigherMetric("Prescription accuracy", 50, 0, 100, "%");
        prescriptionAccuracy.calculateScore(94);
        Metric reportCompletion = new HigherMetric("Report completion rate", 50, 0, 100, "%");
        reportCompletion.calculateScore(87);
        functionalSuitability.addMetric(prescriptionAccuracy);
        functionalSuitability.addMetric(reportCompletion);

        dimensions.add(usability);
        dimensions.add(performance);
        dimensions.add(security);
        dimensions.add(reliability);
        dimensions.add(functionalSuitability);

        return new Scenario("Scenario A - Hospital System", "Health", "Product Quality", dimensions);
    }

    // Health - Scenario B veri seti.
    private static Scenario createHealthScenarioB() {
        ArrayList<Dimension> dimensions = new ArrayList<>();

        Dimension sprintEfficiency = new Dimension("Sprint Efficiency", 25);
        Metric completedTasks = new HigherMetric("Completed tasks", 50, 0, 100, "%");
        completedTasks.calculateScore(78);
        Metric delayedTasks = new LowerMetric("Delayed tasks", 50, 0, 40, "%");
        delayedTasks.calculateScore(12);
        sprintEfficiency.addMetric(completedTasks);
        sprintEfficiency.addMetric(delayedTasks);

        Dimension codeQuality = new Dimension("Code Quality", 25);
        Metric codeReviewScore = new HigherMetric("Code review score", 50, 0, 100, "points");
        codeReviewScore.calculateScore(86);
        Metric defectDensity = new LowerMetric("Defect density", 50, 0, 30, "defects");
        defectDensity.calculateScore(7);
        codeQuality.addMetric(codeReviewScore);
        codeQuality.addMetric(defectDensity);

        Dimension collaboration = new Dimension("Team Collaboration", 20);
        Metric meetingParticipation = new HigherMetric("Meeting participation", 50, 0, 100, "%");
        meetingParticipation.calculateScore(82);
        Metric unresolvedComments = new LowerMetric("Unresolved comments", 50, 0, 50, "comments");
        unresolvedComments.calculateScore(14);
        collaboration.addMetric(meetingParticipation);
        collaboration.addMetric(unresolvedComments);

        Dimension reliability = new Dimension("Process Reliability", 15);
        Metric releaseSuccess = new HigherMetric("Release success rate", 50, 0, 100, "%");
        releaseSuccess.calculateScore(91);
        Metric rollbackRate = new LowerMetric("Rollback rate", 50, 0, 20, "%");
        rollbackRate.calculateScore(3);
        reliability.addMetric(releaseSuccess);
        reliability.addMetric(rollbackRate);

        Dimension documentation = new Dimension("Documentation", 15);
        Metric documentationCoverage = new HigherMetric("Documentation coverage", 50, 0, 100, "%");
        documentationCoverage.calculateScore(73);
        Metric missingDocuments = new LowerMetric("Missing documents", 50, 0, 30, "docs");
        missingDocuments.calculateScore(9);
        documentation.addMetric(documentationCoverage);
        documentation.addMetric(missingDocuments);

        dimensions.add(sprintEfficiency);
        dimensions.add(codeQuality);
        dimensions.add(collaboration);
        dimensions.add(reliability);
        dimensions.add(documentation);

        return new Scenario("Scenario B - Clinic Process", "Health", "Process Quality", dimensions);
    }
}