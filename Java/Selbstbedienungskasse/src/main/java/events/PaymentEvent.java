package events;

import enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentEvent {
    private PaymentType paymentType;
}
