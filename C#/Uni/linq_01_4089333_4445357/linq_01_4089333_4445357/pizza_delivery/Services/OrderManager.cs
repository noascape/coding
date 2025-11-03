using pizza_delivery.Enums;
using pizza_delivery.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace pizza_delivery.Services
{
    /// Manages the core data structure from the basic requirement
    public class OrderManager
    {
        // Array of available pizza sizes
        public string[] AvailableSizes { get; } = new[] { "Small", "Medium", "Large", "XL" };

        // List for active orders
        public List<Order> ActiveOrders { get; } = new();

        // HashSet for unique topping names
        public HashSet<string> UniqueToppings { get; } =
            new HashSet<string>(StringComparer.OrdinalIgnoreCase);

        // Dictionary for quick search by ID
        public Dictionary<int, Order> OrderById { get; } = new();

        // Queue: incoming orders (kitchen)
        public Queue<Order> IncomingQueue { get; private set; } = new();

        // Stack: History of resently delivered orders
        public Stack<Order> DeliveredHistory { get; } = new();

        public OrderManager(IEnumerable<Order> initialOrders)
        {
            if (initialOrders == null) throw new ArgumentNullException(nameof(initialOrders));
            foreach (var o in initialOrders) AddExisting(o);
        }

        private void AddExisting(Order order)
        {
            ActiveOrders.Add(order);
            OrderById[order.OrderNumber] = order;
            foreach (var t in order.Toppings) UniqueToppings.Add(t.Name);

            if (order.Status is OrderStatus.Pending or OrderStatus.Preparing)
                IncomingQueue.Enqueue(order);

            if (order.Status == OrderStatus.Delivered)
                DeliveredHistory.Push(order);
        }

        public void AddOrder(Order order) => AddExisting(order);

        // Sorting functions
        public IEnumerable<Order> SortByOrderTime(bool descending = false) =>
            (descending ? ActiveOrders.OrderByDescending(o => o.OrderDate)
                        : ActiveOrders.OrderBy(o => o.OrderDate)).ToList();

        public IEnumerable<Order> SortByPrice(bool descending = false) =>
            (descending ? ActiveOrders.OrderByDescending(o => o.TotalPrice)
                        : ActiveOrders.OrderBy(o => o.TotalPrice)).ToList();

        // Search functions
        public Order? FindById(int orderNumber) =>
            OrderById.TryGetValue(orderNumber, out var o) ? o : null;

        public IEnumerable<Order> FindByCustomerName(string name) =>
            ActiveOrders
                .Where(o => o.Customer.Name.Contains(name ?? string.Empty,
                        StringComparison.OrdinalIgnoreCase))
                .ToList();

        public void MarkDelivered(int orderNumber, TimeSpan deliveryTime)
        {
            if (!OrderById.TryGetValue(orderNumber, out var order)) return;

            order.DeliveryTime = deliveryTime;
            order.UpdateStatus(OrderStatus.Delivered);
            DeliveredHistory.Push(order);
        }

        public void RemoveFromQueue(int orderNumber)
        {
            IncomingQueue = new Queue<Order>(
                IncomingQueue.Where(o => o.OrderNumber != orderNumber));
        }
    }
}