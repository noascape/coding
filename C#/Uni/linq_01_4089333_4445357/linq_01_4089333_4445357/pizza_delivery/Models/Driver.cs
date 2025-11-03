using System;

namespace pizza_delivery.Models
{
    public class Driver
    {
        public int Id { get; }
        public string Name { get; }

        public Driver(int id, string name)
        {
            if (id <= 0)
                throw new ArgumentOutOfRangeException(nameof(id), "Fahrer-ID muss positiv sein.");
            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("Fahrername darf nicht leer sein.", nameof(name));

            Id = id;
            Name = name.Trim();
        }

        public override string ToString() => $"{Name} (ID: {Id})";
    }
}