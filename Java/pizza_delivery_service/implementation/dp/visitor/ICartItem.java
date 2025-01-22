package dp.visitor;

public interface ICartItem {
    double accept(CartVisitor visitor);
}