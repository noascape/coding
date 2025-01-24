package visitor;

import customer.*;

public interface IVisitor {
    void visit(NormalItem item);
    void visit(WeightBasedItem item);
    void visit(DiscountedItem item);
    void visit(AgeRestrictedItem item);
}
