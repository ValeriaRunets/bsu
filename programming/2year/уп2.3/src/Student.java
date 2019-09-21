public class Student {
    private int course;
    private int group;
    private String name;

    public Student(int course, int group, String name) {
        this.course = course;
        this.group = group;
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return course+" "+group+" "+name;
    }

    @Override
    public boolean equals(Object obj) {
        return (course==((Student)obj).getCourse()&&group==((Student)obj).getGroup()&&name.compareTo(((Student)obj).getName())==0);
    }
}
