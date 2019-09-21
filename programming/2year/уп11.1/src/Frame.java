import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Frame extends JFrame {
    JTable table=new JTable();
    ArrayList<Human> list=new ArrayList<>();
    Frame(){
        Box panel=Box.createVerticalBox();
        JPanel pan=new JPanel();
        Box t=Box.createVerticalBox();
        pan.setLayout(new BorderLayout());
        JMenuBar menuBar= new JMenuBar();
        JMenu menu= new JMenu("File");
        JMenu info= new JMenu("Information");
        JMenuItem sax=new JMenuItem("Show SAX info");
        info.add(sax);
        JMenuItem save=new JMenuItem("SaveXML");
        JMenuItem openBin=new JMenuItem("Open Binary");
        JMenuItem saveBin=new JMenuItem("Save Binary");
        JMenuItem html = new JMenuItem("To HTML");
        JMenuItem txt = new JMenuItem("To TXT");
        JMenuItem item = new JMenuItem("Open");
        JButton add = new JButton("Add");
        menu.add(item);
        menu.add(save);
        menu.add(openBin);
        menu.add(saveBin);
        menu.add(html);
        menu.add(txt);
        menuBar.add(menu);
        menuBar.add(info);
        JButton del=new JButton("Delete");
        JLabel name=new JLabel("Name");
        JLabel county =new JLabel("Country");
        JLabel age=new JLabel("Age");
        JTextField namet=new JTextField();
        JTextField countryt=new JTextField();
        JTextField aget=new JTextField();
        panel.add(name);
        panel.add(namet);
        panel.add(county);
        panel.add(countryt);
        panel.add(age);
        panel.add(aget);
        panel.add(add);
        t.add(table);
        t.add(del);
        pan.add(t, BorderLayout.WEST);
        pan.add(panel, BorderLayout.CENTER);
        setJMenuBar(menuBar);
        add(pan);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = namet.getText();
                    String country = countryt.getText();
                    String age = aget.getText();
                    list.add(new Human(country, name, Integer.parseInt(age)));
                    Object data[][] = new Object[list.size()][3];
                    Object names[] = {"Country", "Name", "Age"};
                    for (int i = 0; i < list.size(); i++) {
                        data[i][0] = list.get(i).getCountry();
                        data[i][1] = list.get(i).getName();
                        data[i][2] = list.get(i).getAge();
                    }
                    t.remove(table);
                    table = new JTable(data, names);
                    t.add(table);
                    t.repaint();
                    getContentPane().validate();
                }catch(NumberFormatException ex){JOptionPane.showMessageDialog(null, "Enter number in age");}
            }
        });
        sax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser jFileChooser = new JFileChooser();
                    int r = jFileChooser.showDialog(null, "Open file");
                    if (r == JFileChooser.APPROVE_OPTION) {
                        File file = jFileChooser.getSelectedFile();
                        SAXParserFactory factory = SAXParserFactory.newInstance();
                        SAXParser saxParser = factory.newSAXParser();

                        SAX saxp = new SAX();
                        saxParser.parse(file, saxp);
                        JOptionPane.showMessageDialog(getContentPane(), saxp.getResult());
                    }
                } catch (Exception i) {
                    JOptionPane.showMessageDialog(null, i.getMessage());
                }
            }
        });
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(null,"Choose a row.");
                }
                else {
                    list.remove(row);
                    Object data[][]=new Object[list.size()][3];
                    Object names[]={"Country", "Name", "Age"};
                    for (int i=0; i<list.size(); i++) {
                        data[i][0]=list.get(i).getCountry();
                        data[i][1]=list.get(i).getName();
                        data[i][2]=list.get(i).getAge();
                    }
                    t.remove(table);
                    table=new JTable(data, names);
                    t.add(table);
                    t.repaint();
                    getContentPane().validate();
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int r = jFileChooser.showDialog(null, "Open file");
                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    try {
                        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                        Schema schema = factory.newSchema(this.getClass().getResource("schema.xsd"));
                        Validator validator = schema.newValidator();
                        validator.validate(new StreamSource(file));
                    } catch (IOException | SAXException ex) {
                        JOptionPane.showMessageDialog(null,"Incorrect schema");
                    }
                    list = readXML(file);
                    Object data[][]=new Object[list.size()][3];
                    Object names[]={"Country", "Name", "Age"};
                    for (int i=0; i<list.size(); i++) {
                        data[i][0]=list.get(i).getCountry();
                        data[i][1]=list.get(i).getName();
                        data[i][2]=list.get(i).getAge();
                    }
                    t.remove(table);
                    table=new JTable(data, names);
                    t.add(table);
                    t.repaint();
                    getContentPane().validate();
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.newDocument();

                    Element rootElement = document.createElement("list");
                    document.appendChild(rootElement);

                    for (int i = 0; i < list.size(); i++) {
                        Element elementStudent = document.createElement("human");
                        rootElement.appendChild(elementStudent);

                        Attr attr1 = document.createAttribute("country");
                        attr1.setValue(list.get(i).getCountry());
                        elementStudent.setAttributeNode(attr1);

                        Element element1 = document.createElement("name");
                        element1.appendChild(document.createTextNode(list.get(i).getName()));
                        elementStudent.appendChild(element1);

                        Element element2 = document.createElement("age");
                        element2.appendChild(document.createTextNode(Integer.toString(list.get(i).getAge())));
                        elementStudent.appendChild(element2);
                    }

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    JFileChooser jFileChooser = new JFileChooser();
                    int r = jFileChooser.showDialog(null, "Open file");
                    if (r == JFileChooser.APPROVE_OPTION) {
                        StreamResult streamResult = new StreamResult(jFileChooser.getSelectedFile());
                        transformer.transform(source, streamResult);
                    }

                } catch (ParserConfigurationException | TransformerException ex) {}
            }
        });
        openBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                JFileChooser jFileChooser = new JFileChooser();
                int r = jFileChooser.showDialog(null, "Open file");
                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    FileInputStream fileInputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    list = new ArrayList<>();
                    Object obj = null;
                    try{
                        while(true){
                            obj = objectInputStream.readObject();
                            list.add((Human) obj);
                        }
                    } catch(ClassNotFoundException ex){ }
                    catch (IOException ex){System.out.println(ex.getClass());}
                    objectInputStream.close();
                    Object data[][]=new Object[list.size()][3];
                    Object names[]={"Country", "Name", "Age"};
                    for (int i=0; i<list.size(); i++) {
                        data[i][0]=list.get(i).getCountry();
                        data[i][1]=list.get(i).getName();
                        data[i][2]=list.get(i).getAge();
                    }
                    t.remove(table);
                    table=new JTable(data, names);
                    t.add(table);
                    t.repaint();
                    getContentPane().validate();
                }
            } catch (IOException ex) { }
        }});
        saveBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File("out.bin"));
                        ObjectOutputStream objectOutputStream =new ObjectOutputStream(fileOutputStream);
                        for(int i = 0; i < list.size(); i++){
                            objectOutputStream.writeObject(list.get(i));
                            System.out.println(list.get(i));
                        }
                        objectOutputStream.flush();
                        objectOutputStream.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        html.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileopen = new JFileChooser(".");
                    if (fileopen.showDialog(null, "Choose file") == JFileChooser.APPROVE_OPTION) {
                        File file = fileopen.getSelectedFile();
                        TransformerFactory factory = TransformerFactory.newInstance();
                        StreamSource xslStream = new StreamSource(new File("html.xslt"));
                        Transformer transformer = factory.newTransformer(xslStream);
                        StreamSource in = new StreamSource(file);
                        StreamResult out = new StreamResult(new File("table"+ ".html"));
                        transformer.transform(in, out);
                    }
                }catch (TransformerException ex) { }
            }
        });
        txt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    JFileChooser fileopen = new JFileChooser(".");
                    if (fileopen.showDialog(null, "Choose file") == JFileChooser.APPROVE_OPTION) {
                        File file = fileopen.getSelectedFile();
                        TransformerFactory factory = TransformerFactory.newInstance();
                        StreamSource xslStream = new StreamSource(new File("txt.xslt"));
                        Transformer transformer = factory.newTransformer(xslStream);
                        StreamSource in = new StreamSource(file);
                        StreamResult out = new StreamResult(new File(file.getPath() + ".txt"));
                        transformer.transform(in, out);
                    }
                } catch (TransformerException ex) {}
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 300);
    }
    public static ArrayList<Human> readXML(File file){
        ArrayList<Human> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = (Document) builder.parse(file);
            NodeList nodeList = document.getElementsByTagName("human");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                int age= Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                String country = element.getAttribute("country");
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                list.add(new Human(country, name, age));
            }
        }catch (Exception ex){}
        return list;
    }

    public static void main(String[] args) {
        new Frame();
    }
}
