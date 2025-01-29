package events;

import enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScanEvent {
    private String name;
    private float basePrice;
    private int quantity;
    //Optional
    private float customWeight;
    private ItemType itemType;
    private float discount;
    private LocalDateTime discountEnd;
    private int ageRestriction;
}
