package dLL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Function;

public class ParsableList<E extends Parsable<E>> extends DoubleLinkedList<E> {

    private Function<String, E> createFunction;

    public ParsableList() {
    }

    public ParsableList(Function<String, E> createFunction) {
        this.createFunction = createFunction;
    }
    
    public void add(String dataString) {
        add(createElement(dataString));
    }

    public void load(String fName) {
        clear();
        if (fName.length() == 0) {
            return;
        }
        try {
            (new File(Ks.getDataFolder())).mkdir();
            String fN = Ks.getDataFolder() + File.separatorChar + fName;
            BufferedReader fReader
                    = new BufferedReader(new FileReader(new File(fN)));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                add(dLine);
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            Ks.ern("Duomenų failas " + fName + " nerastas");
        } catch (IOException e) {
            Ks.ern("Failo " + fName + " skaitymo klaida");
            System.exit(0);
        }
    }

    public void save(String fName) {
        PrintWriter fWriter = null;
        try {
            if (fName.equals("")) {
                return;
            }

            String fN = Ks.getDataFolder() + File.separatorChar + fName;
            fWriter = new PrintWriter(new FileWriter(new File(fN)));
            for (Parsable d1 = super.get(0); d1 != null; d1 = super.getNext()) {
                fWriter.println(d1.toString());
            }
            fWriter.close();
        } catch (IOException e) {
            Ks.ern("!!! Klaida formuojant " + fName + " failą.");
            System.exit(0);
        }
    }

    public void println() {
        int eilNr = 0;
        if (super.isEmpty()) {
            Ks.oun("Sąrašas yra tuščias");
        } else {
            for (Parsable d1 = super.get(0); d1 != null; d1 = super.getNext()) {
                String printData = String.format("%3d: %s ", eilNr++, d1.toString());
                Ks.oun(printData);
            }
        }
        Ks.oun("****** Bendras elementų kiekis yra " + super.size());
    }

    public void println(String title) {
        Ks.oun("========" + title + "=======");
        println();
        Ks.oun("======== Sąrašo pabaiga =======");
    }

    protected E createElement(String data) {
        if (createFunction == null) {
            throw new IllegalStateException("Nenustatyta sąrašo elementų kūrimo funkcija");
        }
        return createFunction.apply(data);
    }
}
