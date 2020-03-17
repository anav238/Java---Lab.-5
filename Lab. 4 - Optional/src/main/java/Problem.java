import java.util.List;
import java.util.Set;

public class Problem {
    private Set<Element> firstSet;
    private Set<Element> secondSet;

    public Problem(Set<Element> firstSet, Set<Element> secondSet) {
        this.firstSet = firstSet;
        this.secondSet = secondSet;
    }

    public Set<Element> getFirstSet() {
        return firstSet;
    }

    public Set<Element> getSecondSet() {
        return secondSet;
    }

    public void printSetsPreferences() {
        System.out.println("First set preferences: ");
        for (Element element:firstSet)
            System.out.println(element + ": " + element.getPreferences());

        System.out.println("Second set preferences: ");
        for (Element element:secondSet)
            System.out.println(element + ": " + element.getPreferences());
    }
}
