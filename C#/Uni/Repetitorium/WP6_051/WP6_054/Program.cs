using System;
using WP6_054.Models;

namespace WP6_054
{
    internal static class Program
    {
        private static void Main()
        {
            var order = new Order();
            order.AddItem(new OrderItem("USB Cable", 5.99m, 2));
            order.AddItem(new OrderItem("Mouse", 19.90m, 1));

            Console.WriteLine($"Items: {order.Items.Count}");
            Console.WriteLine($"Total: {order.TotalPrice:0.00}");
            Console.WriteLine("WP6_054 done.");
        }
    }
}