package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.Kemezys.BikesGenerator;
import edu.ktu.ds.lab3.Kemezys.HashMapOa;
import edu.ktu.ds.lab3.utils.*;
import edu.ktu.ds.lab3.Kemezys.Bike;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.ParsableHashMap;
import edu.ktu.ds.lab3.utils.ParsableMap;
import javafx.collections.FXCollections;
import edu.ktu.ds.lab3.utils.Map;
import java.util.HashSet;
import java.util.Locale;
import java.util.stream.IntStream;

public class ManualTest {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        //executeTest();
        executeTestBike();
    }

    public static void executeTest() {
        Car c1 = new Car("Renault", "Laguna", 1997, 50000, 1700);
        Car c2 = new Car("Renault", "Megane", 2001, 20000, 3500);
        Car c3 = new Car("Toyota", "Corolla", 2001, 20000, 8500.8);
        Car c4 = new Car("Renault Laguna 2001 115900 7500");
        Car c5 = new Car.Builder().buildRandom();
        Car c6 = new Car("Honda   Civic  2007  36400 8500.3");
        Car c7 = new Car("Renault Laguna 2001 115900 7500");

        // Raktų masyvas
        String[] carsIds = {"TA156", "TA102", "TA178", "TA171", "TA105", "TA106", "TA107", "TA108"};
        int id = 0;
        ParsableMap<String, Car> carsMap
                = new ParsableHashMap<>(String::new, Car::new, HashType.DIVISION);

        // Reikšmių masyvas
        Car[] cars = {c1, c2, c3, c4, c5, c6, c7};
        for (Car c : cars) {
            carsMap.put(carsIds[id++], c);
        }
        carsMap.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(carsMap.contains(carsIds[6]));
        Ks.oun(carsMap.contains(carsIds[7]));
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(carsMap.remove(carsIds[1]));
        Ks.oun(carsMap.remove(carsIds[7]));
        carsMap.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(carsMap.get(carsIds[2]));
        Ks.oun(carsMap.get(carsIds[7]));
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(carsMap);
    }
    public static void executeTestBike() {
        edu.ktu.ds.lab3.utils.HashMap<String, Bike> bikes = new edu.ktu.ds.lab3.utils.HashMap<String, Bike>();

        System.out.println("Individualūs metodai:");
        System.out.println("Metodai getNumberOfCollisions() ir values():");
//Louke;Male;2012;17;200
        Bike b1 = new Bike("Dino Female 2015 16 350");
        Bike b3 = new Bike("Romet Male 2017 17 600");
        Bike b2 = new Bike("Dhs Male 2018 17 800");
        Bike b4 = new Bike();
        String bikas = "MBR Female 2015 16 200";
        bikes.put("abc", b1);
        bikes.put("abcde", b2);
        bikes.put("iquooifj", b3);

        System.out.println(bikes.containsValue(b4));//containsValue-------------------------FALSE
        System.out.println(bikes.putIfAbsent(bikas,b4));//putIfAbsent-----------------------NUll
        System.out.println(bikes.containsValue(b4));//containsValue-------------------------TRUE
        System.out.println(bikes.numberOfEmpties());//numberOfEmpties-------------------------TRUE
        System.out.println("Reikšmių aibė: " + bikes.values());//values---------------------3
        System.out.println("Kai indeksai nesikerta: " + bikes.getNumberOfCollisions() + " kolizijos");//getNumberOfCollisions-------3
        bikes.put("Abc", b1);
        bikes.put("aBc", b1);
        bikes.put("abC", b1);
        System.out.println("Dar 3 elementai su specialiai parinktais raktais: " + bikes.getNumberOfCollisions() + " kolizijos");
        for (int i = 0; i < 20; i++)
            bikes.put(String.valueOf(Math.random()).substring(0, 4), b2); //getNumberOfCollisions------------------

        System.out.println("Dar 20 atsitiktinių raktų: " + bikes.getNumberOfCollisions() + " kolizijos");

    }
}
