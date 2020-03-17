import java.util.*;

public class Hospital implements Element, Comparable<Hospital> {
    private String name;
    private int capacity;
    private List<Element> preferences;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.preferences = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public List<Element> getPreferences() {
        return this.preferences;
    }

    @Override
    public void setPreferences(List<Element> preferences) {
        this.preferences = preferences;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int compareTo(Hospital o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return capacity == hospital.capacity &&
                Objects.equals(name, hospital.name) &&
                Objects.equals(preferences, hospital.preferences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, preferences);
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
