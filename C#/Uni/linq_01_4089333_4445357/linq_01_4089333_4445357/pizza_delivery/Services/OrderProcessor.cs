using pizza_delivery.Enums;
using pizza_delivery.Interfaces;
using pizza_delivery.Models;
using System;

namespace pizza_delivery.Services
{
    /// Simple implementation of IOrderProcessor
    public class OrderProcessor : IOrderProcessor
    {
        private readonly OrderManager _manager;

        public OrderProcessor(OrderManager manager)
        {
            _manager = manager ?? throw new ArgumentNullException(nameof(manager));
        }

        public void ProcessOrder(Order order)
        {
            if (order == null) throw new ArgumentNullException(nameof(order));

            order.UpdateStatus(OrderStatus.Preparing);
            _manager.AddOrder(order);
            _manager.IncomingQueue.Enqueue(order);
        }

        public bool CancelOrder(int orderNumber)
        {
            var order = _manager.FindById(orderNumber);
            if (order is null) return false;

            _manager.ActiveOrders.Remove(order);
            _manager.OrderById.Remove(orderNumber);
            _manager.RemoveFromQueue(orderNumber);
            return true;
        }
    }
}
