package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.gui.ValidationException;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.ParsableHashMap;
import edu.ktu.ds.lab3.utils.ParsableMap;
import edu.ktu.ds.lab3.Kemezys.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * @author eimutis
 */
public class SimpleBenchmark {

    public static final String FINISH_COMMAND = "                               ";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab3.gui.messages");

    private final Timekeeper timekeeper;

    private final String[] BENCHMARK_NAMES = {"OaRemove", "JavaRemove", "HashContains", "JavaContains"};
    private final int[] COUNTS = {10000, 20000, 40000, 80000};
    private String[] phrases;
    private edu.ktu.ds.lab3.utils.HashMap<String, String> testukas1 =
            new edu.ktu.ds.lab3.utils.HashMap<>(10, 0.25f, HashType.DIVISION);

    private final edu.ktu.ds.lab3.Kemezys.HashMapOa<String, String> phrasesMap
            = new edu.ktu.ds.lab3.Kemezys.HashMapOa<>(10, 0.25f, HashType.DIVISION);
    private final java.util.HashMap<String, String> phrasesMap2
            = new java.util.HashMap<>();
    private final edu.ktu.ds.lab3.utils.HashMap<String, String> phrasesMap3
            = new edu.ktu.ds.lab3.utils.HashMap<>(10, 0.25f, HashType.DIVISION);
    /**
     * For console benchmark
     */
    public SimpleBenchmark() {
        timekeeper = new Timekeeper(COUNTS);
    }

    /**
     * For Gui benchmark
     *
     * @param resultsLogger
     * @param semaphore
     */
    public SimpleBenchmark(BlockingQueue<String> resultsLogger, Semaphore semaphore) {
        semaphore.release();
        timekeeper = new Timekeeper(COUNTS, resultsLogger, semaphore);
    }

    public static void main(String[] args) {
        executeTest();
    }
    public static void executeTest() {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        Ks.out("Greitaveikos tyrimas:\n");
        new SimpleBenchmark().startBenchmark();
    }

    public void startBenchmark() {
        try {
            phrases = readFile();
            benchmark();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    private String[] readFile() throws FileNotFoundException{
        List<String> phrasesList = new ArrayList<>();
        Scanner s = new Scanner(new BufferedReader(new FileReader("data/zodynas.txt")));
        while(s.hasNextLine())
        {
            phrasesList.add(s.nextLine());
        }
        return (String[])phrasesList.toArray(new String[0]);
    }
    public void benchmark() throws InterruptedException {
        try {
            for (int k : COUNTS) {
                phrasesMap.clear();
                phrasesMap2.clear();
                phrasesMap3.clear();

                for (int i = 0; i < k; i++) {
                    phrasesMap.put(phrases[i], phrases[i]);
                    phrasesMap2.put(phrases[i], phrases[i]);
                }
                timekeeper.startAfterPause();
                timekeeper.start();
                //------------------REMOVE HASHMAPOA---------------------
                for (int i = 0; i < k; i++) {
                    phrasesMap.remove(phrases[i]);
                }
                //-----------------REMOVE JAVA HASHMAP-------------------
                timekeeper.finish(BENCHMARK_NAMES[0]);
                for (int i = 0; i < k; i++) {
                    phrasesMap2.remove(phrases[i]);
                }
                //----------------CONTAINS HASHMAP-----------------------
                timekeeper.finish(BENCHMARK_NAMES[1]);
                for (int i = 0; i < k; i++) {
                    phrasesMap3.contains(phrases[i]);
                }
                //----------------CONTAINS JAVA HASHMAP-------------------
                timekeeper.finish(BENCHMARK_NAMES[2]);
                for (int i = 0; i < k; i++) {
                    phrasesMap2.containsKey(phrases[i]);
                }
                timekeeper.finish(BENCHMARK_NAMES[3]);
                timekeeper.seriesFinish();
            }

            StringBuilder sb = new StringBuilder();
            timekeeper.logResult(sb.toString());
            timekeeper.logResult(FINISH_COMMAND);
        } catch (ValidationException e) {
            timekeeper.logResult(e.getMessage());
        }
    }
}
