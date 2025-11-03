using System;
using WP6_051.Enums;

namespace WP6_051.Models
{
    public sealed class ServiceProduct : ProductBase
    {
        public ServiceProduct(string name, ProductKind kind, DateTime scheduledFor) : base(name, kind)
        {
            ScheduledFor = scheduledFor;
        }

        public DateTime ScheduledFor { get; private set; }

        public override void PlaceOrder() =>
            Console.WriteLine($"[Service] {Name}: scheduled for {ScheduledFor:yyyy-MM-dd HH:mm}.");

        public override void CancelOrder() =>
            Console.WriteLine($"[Service] {Name}: appointment canceled.");
    }
}