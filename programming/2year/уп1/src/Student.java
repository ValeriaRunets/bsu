public class Student extends Person implements Notifiable, Comparable {
    protected String login;
    protected String email;

    public Student(String name, String login, String email) {
        super(name);
        this.login = login;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void notify(String message) {
        System.out.println(name+" should know "+message);
    }

    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Student)o).name);
    }
}
