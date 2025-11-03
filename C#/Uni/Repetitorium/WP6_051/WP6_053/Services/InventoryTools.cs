using System;
using System.Collections.Generic;

namespace WP6_053.Services
{
    public static class InventoryTools
    {
        public static IReadOnlyList<string> FindBelowThreshold(Dictionary<string, int> stockByProduct, int threshold)
        {
            if (stockByProduct is null) throw new ArgumentNullException(nameof(stockByProduct));

            var result = new List<string>();
            foreach (var kv in stockByProduct)
            {
                if (kv.Value < threshold)
                    result.Add(kv.Key);
            }
            return result;
        }
    }
}
