using System;
using System.Collections.Generic;
using System.Linq;
using pizza_delivery.Models;

namespace pizza_delivery.Collections
{
    /// Simple generic collection for managing different order types
    /// Shows generalization 

    public class OrderCollection<T> where T : class
    {
        private readonly List<T> _items = new();

        public void Add(T item)
        {
            if (item is null) throw new ArgumentNullException(nameof(item));
            _items.Add(item);
        }

        public void AddRange(IEnumerable<T> items)
        {
            if (items is null) throw new ArgumentNullException(nameof(items));
            _items.AddRange(items);
        }

        public bool Remove(T item) => _items.Remove(item);

        public IEnumerable<T> Find(Func<T, bool> predicate) =>
            _items.Where(predicate);

        public IReadOnlyList<T> Items => _items.AsReadOnly();
    }
}