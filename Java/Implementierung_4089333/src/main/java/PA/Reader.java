package PA;

import Customer.Smartphone;
import Employee.IDCard;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Reader {
    private final CentralUnit centralUnit;

    public void scanCard(IDCard idCard) {
        centralUnit.authenticateCard(idCard.getId());
    }

    public void scanSmartphone(Smartphone smartphone) {
        centralUnit.transferToWallet(smartphone);
    }
}
