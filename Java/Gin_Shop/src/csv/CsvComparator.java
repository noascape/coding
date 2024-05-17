package csv;

import java.util.Comparator;

public class CsvComparator implements Comparator<String> {

    @Override
    public int compare(String line1 , String line2) {
        return line1.getline().compareTo(line2.getline());
    }
}
