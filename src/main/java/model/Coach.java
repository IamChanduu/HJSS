package src.main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private String name;

    private List<Integer> rating;

    public Coach(String name) {
        this.name = name;
        this.rating = new ArrayList<>();
    }

    public Coach() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getRating() {
        return rating;
    }

    public void setRating(List<Integer> rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
