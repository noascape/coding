using System;
using WP6_051.Enums;

namespace WP6_051.Models
{
    public sealed class PhysicalProduct : ProductBase
    {
        public PhysicalProduct(string name, ProductKind kind, int stock) : base(name, kind)
        {
            Stock = stock;
        }

        public int Stock { get; private set; }

        public override void PlaceOrder()
        {
            if (Stock <= 0)
            {
                Console.WriteLine($"[Physical] {Name}: out of stock – order rejected.");
                return;
            }
            Stock--;
            Console.WriteLine($"[Physical] {Name}: order placed. New stock: {Stock}.");
        }

        public override void CancelOrder()
        {
            Stock++;
            Console.WriteLine($"[Physical] {Name}: order canceled. Stock restored to {Stock}.");
        }
    }
}
