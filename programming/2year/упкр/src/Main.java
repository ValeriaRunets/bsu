import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Main {
    public ArrayList<Cinema> list = new ArrayList<>();

    public void readXML() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                boolean cinema = false, name = false, films = false;
                String name1, places1;
                ArrayList<String> films1=new ArrayList<>();

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("Cinema")) {
                        list.add(new Cinema(Integer.parseInt(places1), name1, films1) {
                        });
                        cinema = false;
                        films1=new ArrayList<>();
                    }
                }

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("Cinema")) {
                        cinema = true;
                    }
                    if (qName.equalsIgnoreCase("Name")) {
                        name = true;
                    }
                    if (qName.equalsIgnoreCase("Places")) {
                        places1=attributes.getValue("val");
                    }
                    if (qName.equalsIgnoreCase("Films")) {
                        films = true;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (cinema) {
                        if (name) {
                            name1 = new String(ch, start, length);
                            name = false;
                        }
                        if (films) {
                            films1.add(new String(ch, start, length));
                            films = false;
                        }
                    }
                }
            };
            parser.parse(new File("list.xml"), handler);
        }
        catch(SAXException ex){System.out.println(ex.getMessage());}
        catch (ParserConfigurationException ex){System.out.println(ex.getMessage());}
        catch (IOException ex){System.out.println(ex.getMessage());}
    }
    void printList(ArrayList<Cinema> l){
        for(Cinema i:l) {
            System.out.println(i.toString());
        }
    }
    public static void main(String[] args){
        Main m=new Main();
        m.readXML();
        ArrayList<Cinema> sortedList=(ArrayList<Cinema>) m.list.clone();
        sortedList.sort(new MyComparator());
        m.printList(sortedList);
        TreeSet<String> set=new TreeSet<>();
        for(Cinema i:m.list) {
            for (String str:i.films){
                set.add(str);
            }
        }
        for (String i:set){
            System.out.println(i+" ");
        }

        System.out.println(Collections.max(m.list));

        Scanner scanner=new Scanner(System.in);
        int val=scanner.nextInt();
        Collections.sort(m.list, new Comp());
        ArrayList<String> str=new ArrayList<>();
        int ind=Collections.binarySearch(m.list, new Cinema(val, "", str));
        if (ind<0){
            System.out.println("Нет такого кинотеатра");
        }
        else {
            System.out.println(m.list.get(ind));
        }
    }
}
class MyComparator implements Comparator<Cinema> {
    public int compare(Cinema o1, Cinema o2) {
        return o1.name.compareTo(o2.name);
    }
}
class Comp implements Comparator<Cinema> {
    public int compare(Cinema o1, Cinema o2) {
        return o1.places-o2.places;
    }
}
