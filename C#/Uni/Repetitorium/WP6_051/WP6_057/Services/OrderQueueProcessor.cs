using System;
using System.Collections.Generic;
using WP6_057.Models;

namespace WP6_057.Services
{
    public static class OrderQueueProcessor
    {
        public static void RemoveOldestIfAny(Queue<Order> orders)
        {
            if (orders is null) throw new ArgumentNullException(nameof(orders));

            if (orders.Count > 0)
            {
                var removed = orders.Dequeue();
                Console.WriteLine($"Removed oldest order. Items: {removed.Items.Count}, Total: {removed.TotalPrice:0.00}");
            }
            else
            {
                Console.WriteLine("Queue empty – nothing to remove.");
            }
        }
    }
}