package csv;

import java.util.Comparator;

public class StoreComparator implements Comparator<String>{
    @Override
    public int compare(String store1, String store2) {
        return store1.compareTo(store2);
    }
}
