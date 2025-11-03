namespace WP6_054.Models
{
    public sealed class OrderItem
    {
        public OrderItem(string name, decimal unitPrice, int quantity)
        {
            Name = name;
            UnitPrice = unitPrice;
            Quantity = quantity;
        }

        public string Name { get; }
        public decimal UnitPrice { get; }
        public int Quantity { get; }
        public decimal LineTotal => UnitPrice * Quantity;
    }
}