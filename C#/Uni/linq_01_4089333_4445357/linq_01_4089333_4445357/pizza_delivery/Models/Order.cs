using pizza_delivery.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace pizza_delivery.Models
{
    public class Order
    {
        // Datatypes according to task: int, decimal, bool (abgeleitet), string
        public int OrderNumber { get; private set; }
        public Customer Customer { get; private set; }
        public Driver? Driver { get; set; }
        public DateTime OrderDate { get; private set; }
        public decimal TotalPrice { get; private set; }
        public OrderStatus Status { get; private set; }
        public TimeSpan? DeliveryTime { get; set; } // actual time from order to delivery

        private readonly List<Topping> _toppings;
        public IReadOnlyList<Topping> Toppings => _toppings.AsReadOnly();

        public bool IsDelivered => Status == OrderStatus.Delivered;

        public Order(int orderNumber,
                     Customer customer,
                     DateTime orderDate,
                     decimal totalPrice,
                     OrderStatus status,
                     List<Topping> toppings)
        {
            if (orderNumber <= 0) throw new ArgumentOutOfRangeException(nameof(orderNumber));
            Customer = customer ?? throw new ArgumentNullException(nameof(customer));
            if (totalPrice < 0) throw new ArgumentOutOfRangeException(nameof(totalPrice));
            if (toppings == null || toppings.Count == 0)
                throw new ArgumentException("Mindestens ein Topping ist erforderlich.", nameof(toppings));

            OrderNumber = orderNumber;
            OrderDate = orderDate;
            TotalPrice = totalPrice;
            Status = status;
            _toppings = new List<Topping>(toppings);
        }

        // Methodoverloading: single topping
        public void AddTopping(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("Topping-Name darf nicht leer sein.", nameof(name));

            _toppings.Add(new Topping(name.Trim()));
        }

        // Methodoverloading: multiple toppings
        public void AddTopping(params string[] names)
        {
            if (names == null || names.Length == 0) return;
            foreach (var n in names) AddTopping(n);
        }

        public void UpdateStatus(OrderStatus newStatus) => Status = newStatus;

        // Date/time operations
        public DateTime EstimatedDeliveryAt => OrderDate.AddMinutes(30);  // simple estimate
        public DateTime? ActualDeliveryAt => DeliveryTime.HasValue ? OrderDate + DeliveryTime.Value : null;
        public TimeSpan? TimeFromOrderToDelivery => DeliveryTime;

        public override string ToString()
        {
            var tops = string.Join(", ", _toppings.Select(t => t.Name));
            var sb = new StringBuilder();
            sb.Append($"Order #{OrderNumber} | {Customer.Name} | {Customer.FormattedAddress} | ");
            sb.Append($"{OrderDate:g} | {TotalPrice:F2} € | {Status} | Toppings: {tops}");
            if (ActualDeliveryAt.HasValue)
                sb.Append($" | Delivered at {ActualDeliveryAt:HH\\:mm} (Δ {TimeFromOrderToDelivery?.TotalMinutes:F0} min)");
            else
                sb.Append($" | ETA {EstimatedDeliveryAt:HH\\:mm}");
            return sb.ToString();
        }
    }
}
