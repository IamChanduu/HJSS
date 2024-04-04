package src.main.java.model;

public class Coach {
    private String name;

    public Coach(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "src.main.java.model.Coach{" +
                "name='" + name + '\'' + "}";
    }
}
