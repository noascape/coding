package Database;

import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;

@Slf4j
public class Database {
    private final HashMap<String, Item> itemDatabase = new HashMap<>();

    public Database() {
        initDatabase();
    }

    private void initDatabase() {
        itemDatabase.put("r8yz7clkz4", new Item("ABC Can | 0.33L", "r8yz7clkz4", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25));
        itemDatabase.put("tmvkrw69le", new Item("ABC Can | 0.5L", "tmvkrw69le", RecyclingType.SINGLE_USE, MaterialType.METAL, 0.25));
        itemDatabase.put("bzfi339nsy", new Item("DE Bottle | 0.33L", "bzfi339nsy", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.25));
        itemDatabase.put("3jdwml7w52", new Item("DE Bottle | 0.5L", "3jdwml7w52", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.30));
        itemDatabase.put("4xpokcvb7c", new Item("DE Bottle | 0.75L", "4xpokcvb7c", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.50));
        itemDatabase.put("xvjix0xaue", new Item("DE Bottle | 1L", "xvjix0xaue", RecyclingType.MULTI_USE, MaterialType.GLASS, 0.75));
        itemDatabase.put("js92hp13rp", new Item("FG Bottle | 0.5L", "js92hp13rp", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 1.00));
        itemDatabase.put("8hgij9rqv5", new Item("FG Bottle | 1L", "8hgij9rqv5", RecyclingType.MULTI_USE, MaterialType.PLASTIC, 2.00));
    }

    public Item getItem(String barcode) {
        Item item = itemDatabase.get(barcode);
        if (item == null) {
            log.warn("Item with barcode {} not found in database.", barcode);
        } else if (item.getDepositAmount() == null) {
            log.warn("Item with barcode {} has an invalid deposit amount.", barcode);
        } else {
            log.info("Item with barcode {} found in the database.", barcode);
        }
        return item;
    }
}

