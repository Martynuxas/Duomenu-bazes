package edu.ktu.ds.lab2.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.TreeSet;

/**
 * Benchmark class for testing treeset and hashset differences in speed
 */
class Benchmarks extends Application {
    private static ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab2.demo.messages");
    private static Stage stage = new Stage();

    static void showGraph(String method, double[][] times, int[] iterations) {
        new Benchmarks().start(stage);
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(MESSAGES.getString("iterations"));
        yAxis.setLabel(MESSAGES.getString("seconds"));
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        XYChart.Series treeSetSeries = new XYChart.Series();
        XYChart.Series hashSetSeries = new XYChart.Series();
        hashSetSeries.setName(MESSAGES.getString("hashset" + method));
        treeSetSeries.setName(MESSAGES.getString("treeset" + method));
        treeSetSeries.getData().add(new XYChart.Data<>(iterations[0], times[0][0]));
        hashSetSeries.getData().add(new XYChart.Data<>(iterations[0], times[0][1]));
        treeSetSeries.getData().add(new XYChart.Data<>(iterations[1], times[times.length - 1][0]));
        hashSetSeries.getData().add(new XYChart.Data<>(iterations[1], times[times.length - 1][1]));

        stage.setScene(new Scene(chart));
        chart.getData().add(treeSetSeries);
        chart.getData().add(hashSetSeries);
        demonstrateAdd();
        demonstrateContains();
    }

    static void demonstrateAdd() {
        double[][] times = new double[3][];
        times[0] = addBenchmark(1);
        times[1] = addBenchmark(10000);
        times[2] = addBenchmark(1000000);
        showTimes("Add", new int[]{1, 10000, 1000000}, times);
        showGraph("Add", times, new int[]{1, 1000000});
    }

    static void demonstrateContains() {
        double[][] times = new double[3][];
        times[0] = containsBencmark(1);
        times[1] = containsBencmark(10000);
        times[2] = containsBencmark(1000000);
        showTimes("Contains", new int[]{1, 10000, 1000000}, times);
        showGraph("Contains", times, new int[]{1, 1000000});
    }

    private static void showTimes(String method, int[] iterations, double[][] times) {
        for (int i = 0; i < times.length; i++) {
            System.out.println(String.format("%s %s TreeSet %s(): %fs", iterations[i], MESSAGES.getString("iterations"), method, times[i][0]));
            System.out.println(String.format("%s %s HashSet %s(): %fs", iterations[i], MESSAGES.getString("iterations"), method, times[i][1]));
            System.out.println("");
        }
    }

    /**
     * Generates integer treeset and hashset up to count and removes all elements one by one
     *
     * @param count set size
     *              return array of experiment times where [][0] is treeset time and [][1] hashset time
     */
    private static double[] addBenchmark(int count) {
        double[] times = new double[2];
        TreeSet<Integer> treeSet = new TreeSet<>();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            treeSet.add(i);
            hashSet.add(i);
        }
        long time = System.nanoTime();
        for (int i = 0; i < count; i++)
            treeSet.add(i);

        times[0] = (System.nanoTime() - time) / 1e9;
        time = System.nanoTime();
        for (int i = 0; i < count; i++)
            hashSet.add(i);

        times[1] = (System.nanoTime() - time) / 1e9;
        return times;
    }

    /**
     * Generates hashset and treeset up to count and then checks if all subsets is contained up to count
     *
     * @param count set size
     * @return array of experiment times where [][0] is treeset time and [][1] hashset time
     */
    private static double[] containsBencmark(int count) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        HashSet<Integer> hashSet = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        double[] times = new double[2];
        for (int i = 0; i < count; i++) {
            treeSet.add(i);
            hashSet.add(i);
        }

        long time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
            treeSet.contains(list);
        }
        times[0] = (System.nanoTime() - time) / 1e9;

        list = new ArrayList<>();
        time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
            hashSet.contains(list);
        }
        times[1] = (System.nanoTime() - time) / 1e9;
        return times;
    }

    @Override
    public void start(Stage stage) {
        stage.show();
    }
}