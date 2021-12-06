package dLL;

public class BikeMarket {

    public BikeList allBikes = new BikeList();

    public BikeList getByMakeAndFamily(String makeAndFamily) {
        BikeList bikes = new BikeList();
        for (Bike c : allBikes) {
            String carMakeAndFamily = c.getMake() + " " + c.getFamily();
            if (carMakeAndFamily.startsWith(makeAndFamily)) {
                bikes.add(c);
            }
        }
        return bikes;
    }
}
