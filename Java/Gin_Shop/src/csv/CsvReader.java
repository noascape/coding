package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class CsvReader {
    String file = "../Gin_Shop/src/orders.csv";
    BufferedReader br = null;
    String line = "";
    TreeMap<String, String> treeMap = new TreeMap<>();


    public void read() {

        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                String[] row = line.split(";");

                for(String index : row) {
                    System.out.printf("%-10s", index);
                }

                System.out.println();

                if(row.length >=2) {
                    String store = row[0];
                    String ginType = row[1];
                    treeMap.put(store, ginType);
                } else {
                    System.err.println("Invalide row: " + line);
                }
            }
        } catch (IOException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        for(Map.Entry<String, String> entry: treeMap.entrySet()) {
            System.out.print("| Key: " + entry.getKey() + " Values: " + entry.getValue());
            }
        }


    public String getline() {
        return line;
    }
}



