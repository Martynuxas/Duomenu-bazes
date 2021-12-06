package dLL;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bike implements Parsable<Bike> {

    final static private int minYear = 2010;

    final static private double minPrice = 100.0;
    final static private double maxPrice = 1000.0;

    private String make;
    private String family;
    private int year;
    private double price;

    public Bike() {
    }

    public Bike(String make, String family,
            int year, double price) {
        this.make = make;
        this.family = family;
        this.year = year;
        this.price = price;
    }

    public Bike(String data) {
        parse(data);
    }

    @Override
    public final void parse(String data) {
        try {
            Scanner ed = new Scanner(data);
            make = ed.next();
            family = ed.next();
            year = ed.nextInt();
            setPrice(ed.nextDouble());
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie auto -> " + data);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie auto -> " + data);
        }
    }

    public String validate() {
        String error = "";
        int currentYear = LocalDate.now().getYear();
        if (year < minYear || year > currentYear) {
            error = "Netinkami gamybos metai, turi būti ["
                    + minYear + ":" + currentYear + "]";
        }
        if (price < minPrice || price > maxPrice) {
            error += " Kaina už leistinų ribų [" + minPrice
                    + ":" + maxPrice + "]";
        }
        return error;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-8s %4d %8.1f %s",
                make, family, year, price, validate());
    }

    public String getMake() {
        return make;
    }

    public String getFamily() {
        return family;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Bike otherCar) {
        double otherPrice = otherCar.getPrice();
        if (price < otherPrice) {
            return -1;
        }
        if (price > otherPrice) {
            return +1;
        }
        return 0;
    }

    public final static Comparator<Bike> byMakeAndFamily
            = new Comparator<Bike>() {
        @Override
        public int compare(Bike car1, Bike car2) {
            int cmp = car1.getMake().compareTo(car2.getMake());
            if (cmp != 0) {
                return cmp;
            }
            return car1.getFamily().compareTo(car2.getFamily());
        }
    };
    public final static Comparator byPrice = new Comparator() {

        @Override
        public int compare(Object obj1, Object obj2) {
            double price1 = ((Bike) obj1).getPrice();
            double price2 = ((Bike) obj2).getPrice();

            if (price1 < price2) {
                return -1;
            }
            if (price1 > price2) {
                return 1;
            }
            return 0;
        }
    };
    public final static Comparator byYearAndPrice = new Comparator() {

        @Override
        public int compare(Object obj1, Object obj2) {
            Bike c1 = (Bike) obj1;
            Bike c2 = (Bike) obj2;

            if (c1.getYear() < c2.getYear()) {
                return 1;
            }
            if (c1.getYear() > c2.getYear()) {
                return -1;
            }
            if (c1.getPrice() < c2.getPrice()) {
                return 1;
            }
            if (c1.getPrice() > c2.getPrice()) {
                return -1;
            }
            return 0;
        }
    };


    public static void main(String... args) {
        Locale.setDefault(new Locale("LT"));
        Bike a1 = new Bike("BMX", "Female", 2016, 599);
        Bike a2 = new Bike("VED", "Male", 2018, 500);
        Bike a3 = new Bike();
        Bike a4 = new Bike();
        a3.parse("TROL Female 2005 800,8");
        a4.parse("ANO Male 2014  500,8");
        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun(a4);
    }
}
