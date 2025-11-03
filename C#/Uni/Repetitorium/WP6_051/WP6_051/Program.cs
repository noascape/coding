using System;
using WP6_051.Enums;
using WP6_051.Models;

namespace WP6_051
{
    internal static class Program
    {
        private static void Main()
        {
            var book = new PhysicalProduct("Book", ProductKind.Physical, stock: 1);
            var ebook = new DigitalProduct("E-Book", ProductKind.Digital);
            var cleaning = new ServiceProduct("Cleaning", ProductKind.Service, DateTime.Today.AddDays(1).AddHours(14));

            book.PlaceOrder();
            book.PlaceOrder(); // stock is 0 -> rejected
            book.CancelOrder();

            ebook.PlaceOrder();
            ebook.CancelOrder();

            cleaning.PlaceOrder();
            cleaning.CancelOrder();

            Console.WriteLine("WP6_051 done.");
        }
    }
}