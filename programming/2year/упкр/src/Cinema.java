import java.util.ArrayList;

public class Cinema implements Comparable<Cinema> {
    int places;
    String name;
    ArrayList<String> films;

    public Cinema(int places, String name, ArrayList<String> films) {
        this.places = places;
        this.name = name;
        this.films = films;
    }

    @Override
    public String toString() {
        String str= name+" "+Integer.toString(places)+" ";
        for (String i:films){
            str=str+i+" ";
        }
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.places==((Cinema)obj).places){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int compareTo(Cinema o) {
        return places-o.places;
    }
}
