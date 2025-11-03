using System;
using System.Collections.Generic;
using WP6_057.Models;
using WP6_057.Services;

namespace WP6_057
{
    internal static class Program
    {
        private static void Main()
        {
            var q = new Queue<Order>();
            var o1 = new Order();
            o1.AddItem(new OrderItem("USB Cable", 6m, 1));
            q.Enqueue(o1);

            var o2 = new Order();
            o2.AddItem(new OrderItem("Mouse", 20m, 1));
            q.Enqueue(o2);

            OrderQueueProcessor.RemoveOldestIfAny(q);
            OrderQueueProcessor.RemoveOldestIfAny(q);
            OrderQueueProcessor.RemoveOldestIfAny(q);

            Console.WriteLine("WP6_057 done.");
        }
    }
}