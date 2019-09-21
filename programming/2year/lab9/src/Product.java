public class Product {
    protected String name;
    protected String country;
    protected int amount;

    public Product(String name, String country, int amount) {
        this.name = name;
        this.country = country;
        this.amount = amount;
    }
    public String toString(){
        return name+" "+country+" "+amount;
    }
}
