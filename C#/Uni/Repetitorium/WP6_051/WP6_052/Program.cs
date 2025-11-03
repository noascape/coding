using System;
using WP6_052.Services;

namespace WP6_052
{
    internal static class Program
    {
        private static void Main()
        {
            var start = new DateTime(2025, 10, 27); // Monday
            var after3 = BusinessDayCalculator.AddBusinessDays(start, 3);
            var before2 = BusinessDayCalculator.AddBusinessDays(start, -2);

            Console.WriteLine($"Start:   {start:d}");
            Console.WriteLine($"+3 bdays: {after3:d}");
            Console.WriteLine($"-2 bdays: {before2:d}");
            Console.WriteLine("WP6_052 done.");
        }
    }
}