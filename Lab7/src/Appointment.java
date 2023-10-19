public class Appointment {
    public String getPatientName() {
        return patientName;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public String getNotes() {
        return notes;
    }

    private final String patientName;

    private final String appointmentTime;
    private final String visitReason;
    private final String notes;

    public Appointment(String patientName, String appointmentTime, String visitReason, String notes) {
        this.patientName = patientName;
        this.appointmentTime = appointmentTime;
        this.visitReason = visitReason;
        this.notes = notes;
    }

    public Appointment(String patientName, String appointmentTime, String visitReason) {
        this(patientName, appointmentTime, visitReason, null);
    }

    public String toString() {
        return String.format("Patient %s is visiting for %s at %s%s", patientName, visitReason, appointmentTime,
                notes != null ? String.format(", with notes %s", notes) : "");
    }
}
