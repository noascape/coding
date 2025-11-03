using System;
using WP6_060.Models;

namespace WP6_060
{
    internal static class Program
    {
        private static void Main()
        {
            var purchase = new Purchase
            {
                OrderDate = new DateTime(2025, 10, 27),
                PurchaseNumber = 1001,
                CustomerName = "Mia"
            };

            Console.WriteLine($"{purchase.PurchaseNumber} / {purchase.CustomerName} / {purchase.OrderDate:yyyy-MM-dd}");

            // purchase.OrderDate = DateTime.Now; // ERROR: init-only, not assignable here
            Console.WriteLine("WP6_060 done.");
        }
    }
}