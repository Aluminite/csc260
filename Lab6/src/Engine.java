public class Engine implements RailCar {
    private final String info;

    public Engine(String info) {
        this.info = info;
    }

    public String info() {
        return info;
    }

    public String type() {
        return "Engine";
    }

    public String toString() {
        return String.format("Engine car, %s", info);
    }
}
