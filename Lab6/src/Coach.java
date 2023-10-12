public class Coach implements RailCar {
    private final String info;

    public Coach(String info) {
        this.info = info;
    }

    public String info() {
        return info;
    }

    public String type() {
        return "Coach";
    }

    public String toString() {
        return String.format("Coach car, %s", info);
    }
}
