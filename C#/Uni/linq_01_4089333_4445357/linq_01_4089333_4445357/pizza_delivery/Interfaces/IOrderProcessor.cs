using pizza_delivery.Models;

namespace pizza_delivery.Interfaces
{
    /// Requirement: IOrderProcessor with ProcessOrder and CancelOrder.
    public interface IOrderProcessor
    {
        void ProcessOrder(Order order);
        bool CancelOrder(int orderNumber);
    }
}
