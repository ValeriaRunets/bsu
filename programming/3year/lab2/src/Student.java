import java.io.Serializable;

public class Student implements Serializable {
    private String surname;
    private int group;
    private double math;
    private double language;
    private double biology;

    public Student() {
    }

    public Student(String surname, int group, double math, double language, double biology) {
        this.surname = surname;
        this.group = group;
        this.math = math;
        this.language = language;
        this.biology = biology;
    }
    public double avgMark(){
        return (math+language+biology)/3;
    }

    @Override
    public String toString() {
        return surname +" "+avgMark();
    }

    public int getGroup() {
        return group;
    }
}
