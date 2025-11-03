using System;

namespace WP6_060.Models
{
    public sealed class Purchase
    {
        public DateTime OrderDate { get; init; } = DateTime.Today;
        public int PurchaseNumber { get; init; }
        public string CustomerName { get; init; } = string.Empty;
    }
}