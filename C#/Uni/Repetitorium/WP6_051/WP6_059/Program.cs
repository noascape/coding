using System;
using WP6_059.Services;

namespace WP6_059
{
    internal static class Program
    {
        private static void Main()
        {
            Console.WriteLine(TimeOfDayHelper.GetTimeOfDay(new DateTime(2025, 10, 27, 9, 0, 0)));   // Morning
            Console.WriteLine(TimeOfDayHelper.GetTimeOfDay(new DateTime(2025, 10, 27, 14, 0, 0)));  // Afternoon
            Console.WriteLine(TimeOfDayHelper.GetTimeOfDay(new DateTime(2025, 10, 27, 20, 0, 0)));  // Evening
            Console.WriteLine("WP6_059 done.");
        }
    }
}