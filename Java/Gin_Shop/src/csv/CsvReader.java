package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import bottle.*;
import track.*;

public class CsvReader {

    String file = "../Gin_Shop/src/orders.csv";
    BufferedReader br = null;
    String line = "";
    TreeMap<String, String> treeMap = new TreeMap<>();

    public CsvReader() {
        treeMap = new TreeMap<>(new StoreComparator());
    }


    public void read(Track bottleTrack) {

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                String[] row = line.split(";");

                if (row.length >= 2) {
                    String store = row[0];
                    String product = row[1];
                    treeMap.put(store, product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {

            Bottle bottle = new Bottle();

            FrontLabel frontlabel = new FrontLabel();
            frontlabel.setHeader(entry.getValue());
            bottle.addFrontLabel(frontlabel);

            BackLabel backlabel = new BackLabel(System.nanoTime(), bottle.getserialNumber());
            bottle.addBackLabel(backlabel);

            //.getKey()
            bottleTrack.add(bottle);

            //bottleTrack.add(new Bottle.Bottle(identity));
           System.out.print("| Key: " + entry.getKey() + " Values: " + entry.getValue());
        }
    }
}



