using System;
using System.Collections.Generic;

namespace WP6_057.Models
{
    public sealed class Order
    {
        private readonly List<OrderItem> _items = new();
        public IReadOnlyList<OrderItem> Items => _items;

        public decimal TotalPrice
        {
            get
            {
                decimal sum = 0m;
                foreach (var it in _items)
                    sum += it.LineTotal;
                return sum;
            }
        }

        public void AddItem(OrderItem item)
        {
            if (item is null) throw new ArgumentNullException(nameof(item));
            _items.Add(item);
        }
    }
}