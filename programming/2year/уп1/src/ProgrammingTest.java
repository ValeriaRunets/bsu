import java.util.*;

public class ProgrammingTest {
    public static void main(String args[]){
        Set<Student> set=new TreeSet();
        set.add(new Undergraduate("Ivan", "Van", "Van@gmail.com"));
        set.add(new Undergraduate("Alexander", "Alex", "Alex@gmail.com"));
        set.add(new Postgraduate("Nickolay", "Nick", "Nick@gmail.com", new Academic("Vasilevskiy")));
        set.add(new Postgraduate("Vladislav", "Vlad", "Vlad@gmail.com", new Academic("Vasilevskiy")));
        set.add(new Postgraduate("Leonid", "Leo", "Leo@gmail.com", new Academic("Sidorov")));
        Course course=new Course(set, "Programming");
        System.out.println("Postgraduates of Vasilevskiy:");
        Set<Postgraduate> set1=course.getPostgraduates("Vasilevskiy");
        for (Postgraduate i:set1){
            System.out.println(i.name);
        }
        Notifier<Postgraduate> notifier=new Notifier<>(set1);
        notifier.doNotifyAll("The meeting will start at 6 p.m.");
    }
}
