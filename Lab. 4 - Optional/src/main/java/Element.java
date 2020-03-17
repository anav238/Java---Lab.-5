import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface Element {
    String getName();
    int getCapacity();
    List<Element> getPreferences();
    void setPreferences(List<Element> preferences);

    //Function to check which are the elements that an element prefers over another element
    default List<Element> getPrefersOver(Element element) {
        List<Element> preferences = this.getPreferences();
        List<Element> prefersOver = new ArrayList<>();
        for (Element preference:preferences) {
            if (preference.equals(element))
                break;
            prefersOver.add(preference);
        }
        return prefersOver;
    }

    //Function to check which are the elements that an element prefers over a list of elements
    //
    default List<Element> getPrefersOver(List<Element> elements) {
        List<Element> prefersOver = new ArrayList<>();
        for (Element element:elements) {
            List<Element> prefersOverCurrentElement = getPrefersOver(element);
            for (Element element2:prefersOverCurrentElement) {
                if (!prefersOver.contains(element2))
                    prefersOver.add(element2);
            }
        }
        return prefersOver;
    }
}
