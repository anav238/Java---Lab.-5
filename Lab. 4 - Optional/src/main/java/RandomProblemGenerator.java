import java.util.*;

import com.github.javafaker.Faker;

public class RandomProblemGenerator {
    public static Problem generateProblem(int firstSetSize, int secondSetSize, int maxPreferences, int maxCapacity) {
        Set<Element> firstSet = generateFirstSet(firstSetSize);
        Set<Element> secondSet = generateSecondSet(secondSetSize, maxCapacity);

        /*Refolosim aceeasi functie pt a genera preferintele elementelor din ambele multimi.
          Prima multime va fi cea pentru care generam preferintele, a doua va fi cea din care se aleg
          preferintele.*/
        generateSetPreferences(firstSet, secondSet, maxPreferences);
        generateSetPreferences(secondSet, firstSet, maxPreferences);

        Problem problem = new Problem(firstSet, secondSet);
        return problem;
    }

    private static Set<Element> generateFirstSet(int firstSetSize) {
        Faker faker = new Faker();
        //Am ales structura de date TreeSet pentru stocarea elementelor din ambele multimi, deoarece acestea
        //trebuie sa fie unice si este util sa le mentinem sortate (sortarea se face alfabetic, dupa nume).
        Set<Element> firstSet = new TreeSet<>();
        for (int i = 0; i < firstSetSize; i++) {
            String randomName = faker.funnyName().name();
            Element randomResident = new Resident(randomName);
            firstSet.add(randomResident);
        }
        return firstSet;
    }

    private static Set<Element> generateSecondSet(int secondSetSize, int maxCapacity) {
        Faker faker = new Faker();
        Random random = new Random();
        Set<Element> secondSet = new TreeSet<>();
        for (int i = 0; i < secondSetSize; i++) {
            String randomName = faker.medical().hospitalName();
            int randomCapacity = random.nextInt(maxCapacity) + 1;
            Element randomHospital = new Hospital(randomName, randomCapacity);
            secondSet.add(randomHospital);
        }
        return secondSet;
    }

    private static void generateSetPreferences(Set<Element> firstSet, Set<Element> secondSet, int maxPreferences) {
        maxPreferences = Math.min(secondSet.size(), maxPreferences);
        Random random = new Random();
        for (Element element:firstSet) {
            //Formam random lista de preferinte pentru fiecare element din multime, generand prima data un numar random
            //de preferinte, apoi generand un index random de pe care vom selecta un element.
            List<Element> preferences = new ArrayList<>();
            int numberOfPreferences = random.nextInt(maxPreferences) + 1;

            while (preferences.size() < numberOfPreferences) {
                int randomIndex = random.nextInt(secondSet.size());
                //Gasim elementul din multimea a doua aflat pe pozitia randomIndex.
                Optional<Element> randomElement = secondSet.stream()
                        .skip(randomIndex)
                        .findFirst();
                //Daca am gasit acel element si nu este deja adaugat in lista de preferinte, il adaugam.
                if (randomElement.isPresent() && !preferences.contains(randomElement.get()))
                    preferences.add(randomElement.get());
            }
            element.setPreferences(preferences);
        }
    }
}
