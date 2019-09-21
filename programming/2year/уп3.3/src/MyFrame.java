import javax.swing.*;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MyFrame extends JFrame {
    MyFrame() {
        Gson gson = new Gson();
        HashMap<String, Double> map=new HashMap<>();
        try {
            FileReader reader = new FileReader("input.txt");
            JsonReader jreader = new JsonReader(reader);
            Country[] country = gson.fromJson(jreader, Country[].class);
            for (Country obj:country) {
                if (obj.getPopulation()<0){
                    throw new MyEx();
                }
                else {
                    map.put(obj.getName(), obj.getPopulation());
                }
            }
        }catch (com.google.gson.JsonSyntaxException ex){JOptionPane.showMessageDialog(null, "Please check entered numbers", "Warning", JOptionPane.WARNING_MESSAGE); return;}
        catch (NumberFormatException ex){JOptionPane.showMessageDialog(null, "Please check entered numbers", "Warning", JOptionPane.WARNING_MESSAGE); return;}
        catch (MyEx ex){JOptionPane.showMessageDialog(null, "Please check entered numbers", "Warning", JOptionPane.WARNING_MESSAGE);}
        catch (IOException ex){}
        DefaultPieDataset data = new DefaultPieDataset();
        for (Map.Entry entry : map.entrySet())
        {
            data.setValue(entry.getKey().toString(), (Double) entry.getValue());
        }
        JFreeChart chart = ChartFactory.createPieChart("Population", data, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        //plot.setStartAngle(90);
        //plot.setDirection(Rotation.CLOCKWISE);
        //plot.setForegroundAlpha(0.5f);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args){new MyFrame();}
}

class MyEx extends Exception{
    MyEx(){
        super();
    }
}
