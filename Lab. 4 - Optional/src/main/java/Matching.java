import java.util.*;

public class Matching {
    Problem problem;
    Map<Element, List<Element>> matches;

    public Matching(Problem problem) {
        this.problem = problem;
        matches = new HashMap<>();
        matchSets(problem.getFirstSet(), problem.getSecondSet());
    }

    private void matchSets(Set<Element> firstSet, Set<Element> secondSet) {
        for (Element element:firstSet) 
            matchElement(element);
    }

    private void matchElement(Element element) {
        List<Element> elementMatches = new ArrayList<>();
        List<Element> elementPreferences = element.getPreferences();

        int i = 0;
        //In functie de preferintele elementului, incercam sa ii gasim un match (primul element de pe lista lui de
        //preferinte care mai are inca locuri libere)
        while (i < elementPreferences.size() && elementMatches.size() < element.getCapacity()) {
            Element currElement = elementPreferences.get(i);
            if (!matches.containsKey(currElement) ||
                    (matches.containsKey(currElement) &&
                            matches.get(currElement).size() < currElement.getCapacity())) {
                elementMatches.add(currElement);
                //Inainte de a adauga un element in lista de preferinte a match-ului sau,
                // verificam daca lista a fost creata deja. Am ales stocarea preferintelor intr-un
                // ArrayList deoarece este importanta ordinea lor
                if (!matches.containsKey(currElement))
                    matches.put(currElement, new ArrayList<>());
                matches.get(currElement).add(element);
            }
            i++;
        }
        matches.put(element, elementMatches);
    }

    public List<Element> getElementMatches(Element element) {
        return matches.get(element);
    }

    public boolean isStableMatching() {
        Set<Element> firstSet = problem.getFirstSet();
        for (Element element:firstSet) {
            if (matches.get(element).size() > 0) {
                Element matchedElement = matches.get(element).get(0);
                //Gasim elementele pe care elementul curent le prefera in detrimentul match-ului sau.
                List<Element> prefersOverMatch = element.getPrefersOver(matchedElement);
                //In lista acestor elemente, cautam un element care prefera elementul curent in detrimentul
                //unora dintre match-urile sale.
                for (Element preferred : prefersOverMatch) {
                    List<Element> preferredElementMatches = matches.get(preferred);
                    if (preferred.getPrefersOver(preferredElementMatches).contains(element)) {
                        //System.out.println(element);
                        //System.out.println(preferred);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void printFirstSetMatches() {
        Set<Element> firstSet = problem.getFirstSet();
        for (Element element:firstSet)
            System.out.println(element.getName() + " " + matches.get(element));
    }

    public void printSecondSetMatches() {
        Set<Element> secondSet = problem.getSecondSet();
        for (Element element:secondSet)
            System.out.println(element.getName() + " " + matches.get(element));
    }

    @Override
    public String toString() {
        return "Matching{" +
                "matches=" + matches +
                '}';
    }
}
