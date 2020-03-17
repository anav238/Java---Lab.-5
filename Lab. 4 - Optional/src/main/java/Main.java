import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        runRandomInstance();
    }

    public static void runRandomInstance() {
        //Folosind clasa cu metode statice RandomProblemGenerator, putem genera instante random ale problemei,
        //ce respecta restrictiile transmise prin parametri.
        Problem problem = RandomProblemGenerator.generateProblem(10, 5, 5, 3);
        problem.printSetsPreferences();

        //La crearea unui obiect de tip Matching, care ia ca parametru un obiect de tip Problem, se creeaza un cuplaj
        //intre cele 2 multimi din problema.
        Matching matching = new Matching(problem);
        matching.printFirstSetMatches();
        if (matching.isStableMatching())
            System.out.println("Match is stable.");
        else
            System.out.println("Match is not stable.");
    }

}
