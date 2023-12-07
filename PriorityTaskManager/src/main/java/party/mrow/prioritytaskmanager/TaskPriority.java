package party.mrow.prioritytaskmanager;

public enum TaskPriority {
    // Adding or removing a value here will automatically update everywhere else.
    // Make sure the priorities are in order from lowest to highest.
    NONE, LOW, MEDIUM, HIGH, MAXIMUM;

    public String toString() {
        // Makes the first letter upper case and the rest lower case.
        return name().substring(0,1).toUpperCase() + name().substring(1).toLowerCase();
    }
}
