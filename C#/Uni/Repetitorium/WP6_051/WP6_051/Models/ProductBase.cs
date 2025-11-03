using System;
using WP6_051.Enums;

namespace WP6_051.Models
{
    public abstract class ProductBase : IOrderable
    {
        protected ProductBase(string name, ProductKind kind)
        {
            Name = name ?? throw new ArgumentNullException(nameof(name));
            Kind = kind;
        }

        public string Name { get; }
        public ProductKind Kind { get; }

        public abstract void PlaceOrder();
        public abstract void CancelOrder();
    }
}