using System;

namespace pizza_delivery.Models
{
    public class Customer
    {
        public string Name { get; }
        public string Address { get; }        // "Street housenr."
        public string PostalCode { get; }

        // Comfort construcotr: Merge street + number + postal code (string operations)
        public Customer(string name, string street, string houseNumber, string postalCode)
            : this(name, ComposeAddress(street, houseNumber), postalCode) { }

        public Customer(string name, string address, string postalCode)
        {
            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("Kundenname darf nicht leer sein.", nameof(name));
            if (string.IsNullOrWhiteSpace(address))
                throw new ArgumentException("Adresse darf nicht leer sein.", nameof(address));
            if (string.IsNullOrWhiteSpace(postalCode))
                throw new ArgumentException("Postleitzahl darf nicht leer sein.", nameof(postalCode));

            Name = name.Trim();
            Address = address.Trim();
            PostalCode = postalCode.Trim();
        }

        /// Formatted full adress: "Street No, postal code"
        public string FormattedAddress => $"{Address}, {PostalCode}";

        /// Helpermethod: Combine street + house number + postal code 
        public static string ComposeAddress(string street, string houseNumber) =>
            $"{street?.Trim()} {houseNumber?.Trim()}";

        public override string ToString() => $"{Name} ({FormattedAddress})";
    }
}
