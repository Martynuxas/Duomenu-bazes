package edu.ktu.ds.lab3.Kemezys;

import edu.ktu.ds.lab3.utils.*;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import java.util.Locale;

public class ManualTest {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        executeTestBike();
    }
    public static void executeTestBike() {
        ParsableHashMapOa<String, Bike> bikesMap = new ParsableHashMapOa<>(String::new, Bike::new, HashType.DIVISION);
        HashMapOa<String, Bike> bikes = new HashMapOa<String, Bike>();
        String bikas = "MBR Female 2015 16 200";
        Bike b1 = new Bike("Dino Female 2015 16 350");
        Bike b3 = new Bike("Dino Male 2017 17 600");
        Bike b2 = new Bike("Dhs Male 2018 16 400");
        Bike b5 = new Bike("BBS Male 2014 17 500");
        Bike b6 = new Bike("BBS Female 2016 15 300");
        Bike b7 = new Bike("Vit Male 2015 16 1000");
        Bike b4 = new Bike(bikas);
        bikes.put("abc", b1);
        bikes.put("abcde", b2);
        bikes.put("iquooifj", b3);
        bikes.put("iquooifjgf", b3);
        bikes.findPosition("abc");
        System.out.println(bikes.findPosition("abc"));
        System.out.println(bikes.findPosition("abcde"));
        System.out.println(bikes.findPosition("iquooifj"));
        System.out.println(bikes.contains("iquooifj"));
        System.out.println(bikes.contains("iquooifjzz"));
        System.out.println(bikes.get("iquooifj"));

        //----------------------------------PRIDEDAME-----------------------------------
        bikesMap.put("abc", b1);
        bikesMap.put("abcd", b2);
        bikesMap.put("abcde",b3);
        bikesMap.put("abcdef", b4);
        bikesMap.put("abcdefg", b5);
        bikesMap.put("abcdefgh", b6);
        bikesMap.put("abcdefghj", b7);
        //----------------------------------PRIDEDAME PABAIGA-----------------------------------
        bikesMap.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(bikesMap.contains("abc"));//------------------AR YRA TOKS?------
        Ks.oun(bikesMap.contains("abce"));//-----------------AR YRA TOKS?------
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(bikesMap.remove("abc"));//------------------ŠALINAME------
        Ks.oun(bikesMap.remove("abcdef"));//----------------ŠALINAME------
        bikesMap.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(bikesMap.get("abcdef"));//------------------REIKŠMĖ------
        Ks.oun(bikesMap.get("abc"));//---------------------REIKŠMĖ------
        Ks.oun(bikesMap.get("abcdefg"));//-------------------REIKŠMĖ------
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(bikesMap);
    }
}
