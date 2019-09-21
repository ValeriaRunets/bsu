import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MyFrame extends JFrame {
    ArrayList<Product> products=new ArrayList<>();
    JLabel lab=new JLabel("Alphabet order:");
    JLabel label =new JLabel("Products:");
    JButton but=new JButton("Show");
    List list1=new List();
    List list2=new List();
    JMenuBar menuBar= new JMenuBar();
    JMenu menu=new JMenu("File");
    JMenuItem item=new JMenuItem("Open");
    JMenuItem item1=new JMenuItem("Edit");
    JMenuItem item2=new JMenuItem("Save");
    public MyFrame(){
        MyFrame frame= this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        menu.add(item);
        menu.add(item1);
        menu.add(item2);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        Box box=Box.createVerticalBox();
        box.add(lab);
        box.add(list2);
        box.add(but);
        Box b= Box.createVerticalBox();
        b.add(label);
        b.add(list1);
        setLayout(new BorderLayout());
        add(b, BorderLayout.WEST);
        add(box, BorderLayout.EAST);
        JPanel pan=new JPanel();
        add(pan, BorderLayout.SOUTH);
        setSize(300, 400);
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Product> buf=new ArrayList<>();
                HashMap<String, Integer> map=new HashMap<>();
                HashSet<String> set=new HashSet<>();
                for (Iterator it=products.iterator();it.hasNext();){
                    set.add(((Product)it.next()).name);
                }
                for (Iterator i=set.iterator();i.hasNext();) {
                    int sum=0;
                    Object ob=i.next();
                    for (Iterator it=products.iterator();it.hasNext();){
                        Object obj=it.next();
                        if (((String)ob).compareTo(((Product)obj).name)==0){
                            sum+=((Product) obj).amount;
                        }
                    }
                    map.put((String)ob, sum);
                }
                for (Iterator it=set.iterator();it.hasNext();) {
                    String str=(String)it.next();
                    buf.add(new Product(str, "", map.get(str)));
                }
                buf.sort(new MyComparator());
                list2.removeAll();
                for (Iterator it=buf.iterator();it.hasNext();) {
                    list2.add(((Product)(it.next())).name);
                }
            }
        });
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditJDialog(products,frame);
                write();
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("output.xml");
                    writer.write("<LIST>\n");
                    for (Product i : products) {
                        i.printXML(writer);
                    }
                    writer.append("</LIST>");
                    writer.flush();
                    writer.close();
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);}
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    products=new ArrayList<>();
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SchemaFactory sfactory=SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                    try {
                        Schema schema=sfactory.newSchema(new File("input.xsd"));
                        factory.setSchema(schema);
                        factory.setNamespaceAware(true);
                        factory.setValidating(true);
                        SAXParser parser = factory.newSAXParser();
                        DefaultHandler handler = new DefaultHandler() {
                            boolean product = false, name = false, country = false, amount = false;
                            String n, c, a;

                            @Override
                            public void endElement(String uri, String localName, String qName) throws SAXException {
                                if (qName.equalsIgnoreCase("PRODUCT")) {
                                    products.add(new Product(n, c, Integer.parseInt(a)));
                                    product = false;
                                }
                            }

                            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                                if (qName.equalsIgnoreCase("PRODUCT")) {
                                    product = true;
                                }
                                if (qName.equalsIgnoreCase("NAME")) {
                                    name = true;
                                }
                                if (qName.equalsIgnoreCase("COUNTRY")) {
                                    country = true;
                                }
                                if (qName.equalsIgnoreCase("AMOUNT")) {
                                    amount = true;
                                }
                            }

                            public void characters(char ch[], int start, int length) throws SAXException {
                                if (product) {
                                    if (name) {
                                        n = new String(ch, start, length);
                                        name = false;
                                    }
                                    if (country) {
                                        c = new String(ch, start, length);
                                        country = false;
                                    }
                                    if (amount) {
                                        a = new String(ch, start, length);
                                        amount = false;
                                    }
                                }
                            }
                        };
                        parser.parse(file, handler);
                    } catch (SAXException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please, check your file", "Warning", JOptionPane.WARNING_MESSAGE);}
                     catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);}
                    catch (NoSuchElementException ex) {
                        JOptionPane.showMessageDialog(null, "Please, check your file", "Warning", JOptionPane.WARNING_MESSAGE);
                    } catch (ParserConfigurationException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
                write();
            }
        });
    }
    public void write(){
        list1.removeAll();
        for (Iterator it=products.iterator();it.hasNext();) {
            list1.add(it.next().toString());
        }
    }
    public static void main(String[]args){MyFrame f=new MyFrame();}
}

class MyComparator implements Comparator<Product> {
    public int compare(Product o1, Product o2) {
        if (o1.amount==o2.amount){
            return o1.name.compareTo(o2.name);
        }
        else{
            return o2.amount-o1.amount;
        }
    }
}

class MyEx extends Exception{
    MyEx(){}

    @Override
    public String getMessage() {
        return "Check intered information";
    }
}

class EditJDialog extends JDialog{
    JLabel empty=new JLabel();
    JLabel name =new JLabel("Name");
    JLabel country =new JLabel("Country");
    JLabel amount =new JLabel("Amount");
    JTextField nameText=new JTextField();
    JTextField countryText=new JTextField();
    JTextField amountText=new JTextField();
    JButton button=new JButton("Add");
    EditJDialog(ArrayList<Product> products, MyFrame frame){
        setLayout(new GridLayout(2, 4));
        add(name);
        add(country);
        add(amount);
        add(empty);
        add(nameText);
        add(countryText);
        add(amountText);
        add(button);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name1=nameText.getText();
                String country1=countryText.getText();
                String amount1=amountText.getText();
                try {
                    if (name1.length()==0|country1.length()==0){
                        throw new MyEx();
                    }
                    products.add(new Product(name1, country1, Integer.parseInt(amount1)));
                    frame.write();
                }
                catch (MyEx ex){JOptionPane.showMessageDialog(null, ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);}
                catch (NumberFormatException ex){JOptionPane.showMessageDialog(null, "Check entered information", "Warning", JOptionPane.WARNING_MESSAGE);}
            }
        });
    }
}
