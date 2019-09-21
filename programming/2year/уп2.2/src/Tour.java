public class Tour {
    private String country;
    private String description;
    private int price;

    public Tour(String country, String description, int price) {
        this.country = country;
        this.description = description;
        this.price = price;
    }

    public String getCountry() {
        return country;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }
}
