package events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScanEvent {
    //soll ich die alle drin lassen oder kommen diese Informationen dann erst beim ScanEvent aus der Datenbank
    //private String itemName;
    //private float price;
    //private int quantity;
    private String barcode;
    private int quantity;
}
