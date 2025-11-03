namespace pizza_delivery.Models
{
    public class PostalCodeOrderStatsDTO
    {
        public string PostalCode { get; set; } = string.Empty;
        public int OrderCount { get; set; }
        public decimal AverageOrderValue { get; set; }
        public decimal TotalRevenue { get; set; }

        public override string ToString() =>
            $"{PostalCode}: {OrderCount} Bestellungen | ∅ {AverageOrderValue:F2} € | Summe {TotalRevenue:F2} €";
    }
}
