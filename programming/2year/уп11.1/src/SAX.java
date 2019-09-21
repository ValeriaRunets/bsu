import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAX extends DefaultHandler {

    private boolean startAge;
    private int max = 0;
    private int count = 0;

    public void startElement (String uri, String localName, String qName, Attributes attributes ) {
        if (qName.equals("human")) {
            ++count;
        } else if (qName.equals("age")) {
            startAge = true;
        }
    }

    public void characters(char ch[], int start, int length)  {
        if (startAge) {
            String string = new String (ch,start,length);
            int age = Integer.parseInt(string);
            if (age> max){
                max= age;
            }
            startAge = false;
        }
    }

    public String getResult() {
        String result = "Total number of people: " + count + "\n";
        if (count > 0) {
            result += "Maximum age: " + Integer.toString((getMaxAge())) + "\n";
        }
        return result;
    }

    private int getMaxAge() {
        return max;
    }
}
