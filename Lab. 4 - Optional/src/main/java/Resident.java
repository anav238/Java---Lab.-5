import java.util.*;

public class Resident implements Element, Comparable<Resident> {
    private String name;
    private List<Element> preferences;

    public Resident(String name) {
        this.name = name;
        this.preferences = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public int getCapacity() {
        return 1;
    }

    @Override
    public List<Element> getPreferences() {
        return this.preferences;
    }

    @Override
    public void setPreferences(List<Element> preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return name.equals(resident.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Resident{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Resident o) {
        return this.getName().compareTo(o.getName());
    }
}
