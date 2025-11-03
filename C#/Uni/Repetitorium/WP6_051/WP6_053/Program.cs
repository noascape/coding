using System;
using System.Collections.Generic;
using WP6_053.Services;

namespace WP6_053
{
    internal static class Program
    {
        private static void Main()
        {
            var stock = new Dictionary<string, int>
            {
                ["USB Cable"] = 3,
                ["Mouse"] = 10,
                ["Keyboard"] = 1
            };

            var low = InventoryTools.FindBelowThreshold(stock, threshold: 5);
            Console.WriteLine("Below threshold:");
            foreach (var name in low)
                Console.WriteLine($"- {name}");

            Console.WriteLine("WP6_053 done.");
        }
    }
}