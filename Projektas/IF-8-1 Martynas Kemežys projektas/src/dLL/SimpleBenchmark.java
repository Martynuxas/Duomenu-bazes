package dLL;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;


public class SimpleBenchmark {

    Bike[] bikes;
    DoubleLinkedList<Bike> bikeSeries1 = new DoubleLinkedList<>();
    java.util.LinkedList<Bike> bikeSeries2 = new java.util.LinkedList<>();
    Random rg = new Random();
    int[] counts = {2_000, 4_000, 8_000, 16_000};

    void generateBikes(int count) {
        String[][] makesAndFamily = {
            {"BMX", "Fed", "ESL", "B23", "MIX"},
            {"Shimano", "Field", "Esc", "Foc", "Sie", "Mond"},
            {"Sava", "Milkon", "Vidison"}
        };
        bikes = new Bike[count];
        rg.setSeed(2017);
        for (int i = 0; i < count; i++) {
            int makeIndex = rg.nextInt(makesAndFamily.length);
            int familyIndex = rg.nextInt(makesAndFamily[makeIndex].length - 1) + 1;
            bikes[i] = new Bike(makesAndFamily[makeIndex][0], makesAndFamily[makeIndex][familyIndex],
                    1994 + rg.nextInt(20),
                    10 + rg.nextDouble() * 1_000);
        }
        Collections.shuffle(Arrays.asList(bikes));
        bikeSeries1.clear();
        for (Bike c : bikes) {
            bikeSeries1.add(c);
        }
    }
        void generateJavaBikes(int count) {
        String[][] makesAndFamily = {
            {"BMX", "Fed", "ESL", "B23", "MIX"},
            {"Shimano", "Field", "Esc", "Foc", "Sie", "Mond"},
            {"Sava", "Milkon", "Vidison"}
        };
        bikes = new Bike[count];
        rg.setSeed(2017);
        for (int i = 0; i < count; i++) {
            int makeIndex = rg.nextInt(makesAndFamily.length);
            int familyIndex = rg.nextInt(makesAndFamily[makeIndex].length - 1) + 1;
            bikes[i] = new Bike(makesAndFamily[makeIndex][0], makesAndFamily[makeIndex][familyIndex],
                    1994 + rg.nextInt(20),
                    10 + rg.nextDouble() * 1_000);
        }
        bikeSeries2.clear();
        for (Bike c : bikes) {
            bikeSeries2.add(c);
        }
       }

    void generateAndExecute(int elementCount) {
        
        long t0 = System.nanoTime();
        generateBikes(elementCount);//Sugeneruoja dviračius
        //vyksta ADD
        long t1 = System.nanoTime();
        generateJavaBikes(elementCount);//Sugeneruoja javos dviračius
        //vyksta JADD 
        long t2 = System.nanoTime();
        for (int i = 0; i < elementCount; i++) {
            bikeSeries1.remove(i);//Pašalina elementa
        }
        //vyksta REMOVE
        long t3 = System.nanoTime();
        for (int k = 0; k < 1000; k++) {
        bikeSeries2.remove(bikeSeries2.get(k));//Pašalina javos elementa
        }
        //vyksta JREMOVE
        long t4 = System.nanoTime();

        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f\n",elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9);
    }

    void execute() {

        Ks.ouf("  Kiekis   Add    JAdd   Remove  JRemove \n");
        for (int n : counts) {
            generateAndExecute(n);
        }
    }

    public static void main(String[] args) {

        Locale.setDefault(new Locale("LT"));
        new SimpleBenchmark().execute();
    }
}
