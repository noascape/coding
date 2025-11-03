namespace pizza_delivery.Models
{
    public class ActiveCustomerDTO
    {
        public string CustomerName { get; set; } = string.Empty;
        public string PostalCode { get; set; } = string.Empty;
        public int OrderCount { get; set; }
        public decimal AverageOrderValue { get; set; }

        public override string ToString() =>
            $"{CustomerName} ({PostalCode}) - {OrderCount} Bestellungen | ∅ {AverageOrderValue:F2} €";
    }
}