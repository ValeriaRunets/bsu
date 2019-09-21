import java.io.Serializable;
import java.util.Objects;

public class Human implements Serializable {
    private String country;
    private String name;
    private int age;

    public Human(String country, String name, int age) {
        this.country = country;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return getAge() == human.getAge() &&
                Objects.equals(getCountry(), human.getCountry()) &&
                Objects.equals(getName(), human.getName());
    }
}
