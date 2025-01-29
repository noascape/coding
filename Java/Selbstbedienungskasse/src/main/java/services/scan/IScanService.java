package services.scan;

public interface IScanService {
    boolean scanItem(String barcode, int quantity, float customWeight, int customerAge);
}
