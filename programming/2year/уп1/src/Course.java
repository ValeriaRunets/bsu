import java.util.*;

public class Course {
    protected Set<Student> students;
    protected String name;

    public Set<Postgraduate> getPostgraduates(String nameOfSupervisor){
        Set<Postgraduate> buf=new TreeSet<>();
        for (Student i:students){
            if (i instanceof Postgraduate){
                if (((Postgraduate)i).getSupervisor().name.equals(nameOfSupervisor)){
                    buf.add((Postgraduate) i);
                }
            }
        }
        return buf;
    }

    public Course(Set<Student> students, String name) {
        this.students = students;
        this.name = name;
    }
}
