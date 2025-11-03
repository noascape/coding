namespace pizza_delivery.Models
{
    public class DriverPerformanceDto
    {
        public string DriverName { get; set; } = string.Empty;
        public int DeliveredOrdersCount { get; set; }
        public double AverageDeliveryTimeMinutes { get; set; }
        public double MaxDeliveryTimeMinutes { get; set; }

        public override string ToString() =>
            $"{DriverName,-10} | Lieferungen: {DeliveredOrdersCount,2} | ∅ {AverageDeliveryTimeMinutes:F1} min | Max {MaxDeliveryTimeMinutes:F1} min";
    }
}

