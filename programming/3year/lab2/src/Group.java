import java.util.*;

public class Group {
    int number;
    ArrayList<Student> students;

    public Group(ArrayList<Student> students, int number) {
        this.students = students;
        this.number = number;
    }
    public double getAvg(){
        double sum=0;
        for (Student s:students){
           sum+=s.avgMark();
        }
        return sum/students.size();
    }

    @Override
    public String toString() {
        String str= "Group" + number +" "+getAvg()+":\n";
        for (Student s:students){
            str+=s.toString()+'\n';
        }
        return str;
    }
}
