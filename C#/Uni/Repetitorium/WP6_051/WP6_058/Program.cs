using System;
using WP6_058.Structs;

namespace WP6_058
{
    internal static class Program
    {
        private static void Main()
        {
            OrderId id = 42; // implicit from int
            int i = id;      // implicit to int

            Console.WriteLine($"OrderId: {id} / int: {i}");
            Console.WriteLine("WP6_058 done.");
        }
    }
}