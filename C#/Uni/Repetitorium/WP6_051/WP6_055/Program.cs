using System;
using WP6_055.Services;

namespace WP6_055
{
    internal static class Program
    {
        private static void Main()
        {
            Console.WriteLine($"'{TextTrimmer.Trim("  hello  ")}'");
            Console.WriteLine($"'{TextTrimmer.Trim("--hello--", '-')}')");
            Console.WriteLine("WP6_055 done.");
        }
    }
}