import java.util.*;

class Sportsman {
    private String name;
    private int age;
    private String sportName;
    private int rank;

    public Sportsman(String name, int age, String sportName, int rank) {
        this.name = name;
        this.age = age;
        this.sportName = sportName;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Tamirchin: " + name + " | Sport: " + sportName + " | Rank: " + rank;
    }
}

// Rank-aar erembeleh Comparator
class RankComparator implements Comparator<Sportsman> {
    @Override
    public int compare(Sportsman s1, Sportsman s2) {
        return Integer.compare(s1.getRank(), s2.getRank());
    }
}

// Ner-eer erembeleh Comparator
class NameComparator implements Comparator<Sportsman> {
    @Override
    public int compare(Sportsman s1, Sportsman s2) {
        return s1.getName().compareTo(s2.getName());
    }
}

public class lab12_22 { // 2. 'public' bolgoj bolno, failiin ner Main.java baih yostoi
    public static void main(String[] args) {
        ArrayList<Sportsman> list = new ArrayList<>(); // java.util.ArrayList-iig import hiisen tul ajillana
        list.add(new Sportsman("Zorigt", 25, "Bukh", 3));
        list.add(new Sportsman("Amaraa", 22, "Sags", 1));
        list.add(new Sportsman("Boldoo", 24, "Tennis", 2));

        // Nereer erembeleh
        System.out.println("--- Nereer erembeleh ---");
        Collections.sort(list, new NameComparator()); // Collections klassiig ashiglakhad import kheregtei
        for (Sportsman s : list)
            System.out.println(s);

        // Rank-aar erembeleh
        System.out.println("\n--- Rank-aar erembeleh ---");
        Collections.sort(list, new RankComparator());
        for (Sportsman s : list)
            System.out.println(s);
    }
}