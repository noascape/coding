using System;
using WP6_056.Models;

namespace WP6_056
{
    internal static class Program
    {
        private static void Main()
        {
            var c1 = new Customer();
            var c2 = new Customer(1, "Mia", "mia@example.com", isActive: true);

            Console.WriteLine($"{c1.Id} / {c1.Name} / {c1.Email} / Active={c1.IsActive}");
            Console.WriteLine($"{c2.Id} / {c2.Name} / {c2.Email} / Active={c2.IsActive}");
            Console.WriteLine("WP6_056 done.");
        }
    }
}
