using System;

namespace pizza_delivery.Models
{
    public class Topping
    {
        public string Name { get; }

        public Topping(string name)
        {
            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("Topping-Name darf nicht leer sein.", nameof(name));

            Name = name.Trim();
        }

        public override string ToString() => Name;
    }
}
