package customer;

import enums.ItemType;
import lombok.Data;
import visitor.Visitable;
import java.time.LocalDateTime;

@Data
public abstract class Item implements Visitable {
    public String name;
    public String barcode;
    public float price;
    public float weight;
    public ItemType type;
    public float discount;
    public int ageRestriction;
    public LocalDateTime discountEnd;
    public int quantity;

    public Item(String name, String barcode, float price, float weight, float discount, int ageRestriction, LocalDateTime discountEnd, int quantity) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
        this.ageRestriction = ageRestriction;
        this.discountEnd = discountEnd;
        this.quantity = quantity;
    }

    public boolean isDiscountActive() {
        return discountEnd != null && discountEnd.isAfter(LocalDateTime.now());
    }
}


