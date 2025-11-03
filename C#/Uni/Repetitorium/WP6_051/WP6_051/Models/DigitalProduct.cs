using System;
using WP6_051.Enums;

namespace WP6_051.Models
{
    public sealed class DigitalProduct : ProductBase
    {
        public DigitalProduct(string name, ProductKind kind) : base(name, kind) { }

        public bool LicenseIssued { get; private set; }

        public override void PlaceOrder()
        {
            LicenseIssued = true;
            Console.WriteLine($"[Digital] {Name}: license issued.");
        }

        public override void CancelOrder()
        {
            if (!LicenseIssued)
            {
                Console.WriteLine($"[Digital] {Name}: no active license – nothing to cancel.");
                return;
            }
            LicenseIssued = false;
            Console.WriteLine($"[Digital] {Name}: cancel noted (download access revoked).");
        }
    }
}