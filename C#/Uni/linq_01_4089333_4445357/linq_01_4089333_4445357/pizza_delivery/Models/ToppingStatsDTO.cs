namespace pizza_delivery.Models
{
    public class ToppingStatsDTO
    {
        public string ToppingName { get; set; } = string.Empty;
        public int Count { get; set; }

        public override string ToString() => $"{ToppingName} ({Count}x)";
    }
}


