package dLL;

import java.util.Locale;

public class ManualTest {

    BikeList bikes = new BikeList();

    void execute() {
        createBikeList();
    }
    
    void createBikeList() {
        Bike c1 = new Bike("Wide", "Male", 2012, 520);
        Bike c2 = new Bike("Retro", "Male", 2011, 350);
        Bike c3 = new Bike("Whell", "Female", 2015, 850.6);
        bikes.add(c1);
        bikes.add(c2);
        bikes.add(c3);
        bikes.println("Pirmi 3 dviračiai");
        bikes.add("BMX Male 2015 750");
        bikes.add("Fed Female 2017 950");
        bikes.add("Shimano   Female  2015 850,3");
        //bikes.set(2, c1);
        bikes.add(0,c2);
        bikes.println("Visi 6 dviračiai");
        bikes.removeLastOccurrence(c3);
        bikes.println("Po metodo");
        bikes.remove(0);
        bikes.println("Po 2 metodo");
        bikes.println("Po 3 metodo");
        Ks.oun(bikes.findPreviuos(c2));
        Ks.oun(bikes.findBefore(c2));
        //bikes.forEach(System.out::println);
    }

    public static void main(String... args) {
        Locale.setDefault(new Locale("LT"));
        new ManualTest().execute();
    }
}
