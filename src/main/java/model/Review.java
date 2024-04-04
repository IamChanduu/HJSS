package src.main.java.model;

public class Review {
    private String discription;
    private int rating;

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Review(String discription, int rating) {
        this.discription = discription;
        this.rating = rating;
    }

    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "discription='" + discription + '\'' +
                ", rating=" + rating +
                '}';
    }

}
