package dLL;

import java.util.Random;

public class BikeList extends ParsableList<Bike> {

    public BikeList() {
    }
    
    public BikeList(int count) {
        super();
        String[][] makesAndFamily = {
            {"BMX", "Fed", "ESL", "B23", "MIX"},
            {"Shimano", "Field", "Esc", "Foc", "Sie", "Mond"},
            {"Sava", "Milkon", "Vidison"}
        };
        Random rnd = new Random();
        rnd.setSeed(2017);
        for (int i = 0; i < count; i++) {
            int makeIndex = rnd.nextInt(makesAndFamily.length);
            int familyIndex = rnd.nextInt(makesAndFamily[makeIndex].length - 1) + 1;
            add(new Bike(makesAndFamily[makeIndex][0], makesAndFamily[makeIndex][familyIndex],
                    1994 + rnd.nextInt(20),
                    10 + rnd.nextDouble() * 100));
        }
    }
    
    @Override
    protected Bike createElement(String data) {
        return new Bike(data);
    }
}
