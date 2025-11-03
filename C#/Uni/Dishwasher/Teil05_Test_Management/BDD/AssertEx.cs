using System;
using System.Collections.Generic;


namespace Teil05_TestManagement.BDD
{
    public static class AssertEx
    {
        public static void AreEqual<T>(T expected, T actual, string label)
        {
            if (!EqualityComparer<T>.Default.Equals(expected, actual))
                throw new InvalidOperationException($"ASSERT FAILED [{label}] Expected: {expected}, Actual: {actual}");
            Console.WriteLine($"PASS [{label}] -> {actual}");
        }
    }
}
