import java.io.FileWriter;
import java.io.IOException;

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
    public void printXML(FileWriter f)throws IOException {
        f.append(" <PRODUCT>\n  <NAME>"+name+"</NAME>\n  <COUNTRY>"+country+"</COUNTRY>\n  <AMOUNT>"+amount+"</AMOUNT> \n </PRODUCT> \n");
    }
}
