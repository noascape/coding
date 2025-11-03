using System;
using Teil03_OOP.Enums;

namespace Teil03_OOP.Models
{
    /// <summary>
    /// Abstract base class for all kitchen appliances (kept minimal).
    /// </summary>
    public abstract class KitchenAppliance
    {
        // Required basics (brief validation, no overengineering)
        private string _manufacturer = "";
        public string Manufacturer
        {
            get => _manufacturer;
            init => _manufacturer = string.IsNullOrWhiteSpace(value)
                ? throw new ArgumentException("Manufacturer is required.", nameof(Manufacturer))
                : value.Trim();
        }

        private string _modelNumber = "";
        public string ModelNumber
        {
            get => _modelNumber;
            init => _modelNumber = string.IsNullOrWhiteSpace(value)
                ? throw new ArgumentException("ModelNumber is required.", nameof(ModelNumber))
                : value.Trim();
        }

        // We use kWh/year (double) for this assignment.
        private double _powerConsumptionKWhPerYear;
        public double PowerConsumptionKWhPerYear
        {
            get => _powerConsumptionKWhPerYear;
            init
            {
                if (value < 0 || value > 5000) throw new ArgumentOutOfRangeException(nameof(PowerConsumptionKWhPerYear));
                _powerConsumptionKWhPerYear = value;
            }
        }

        private double _weightKg;
        public double WeightKg
        {
            get => _weightKg;
            init
            {
                if (value < 1 || value > 200) throw new ArgumentOutOfRangeException(nameof(WeightKg));
                _weightKg = value;
            }
        }

        public EnergyRating EnergyRating { get; init; }
        public ApplianceColor Color { get; init; }

        public ApplianceStatus Status { get; protected set; } = ApplianceStatus.Off;

        private int _warrantyMonths;
        public int WarrantyMonths
        {
            get => _warrantyMonths;
            init
            {
                if (value < 0 || value > 120) throw new ArgumentOutOfRangeException(nameof(WarrantyMonths));
                _warrantyMonths = value;
            }
        }

        // --- Required members ---
        public abstract bool TurnOn();
        public abstract void TurnOff();

        public virtual string PerformMaintenance() => "General inspection performed.";

        /// <summary>Simple yearly operating cost using electricity only.</summary>
        public virtual double GetOperatingCost(double electricityPricePerKwh)
            => electricityPricePerKwh * PowerConsumptionKWhPerYear;

        /// <summary>
        /// Compares two ratings: negative if rating1 is better, positive if rating2 is better, 0 if equal.
        /// </summary>
        public static int CompareEnergyRatings(EnergyRating rating1, EnergyRating rating2)
            => rating1.CompareTo(rating2);

        /// <summary>Protected log helper for subclasses.</summary>
        protected void LogStatus(string message)
            => Console.WriteLine($"[{GetType().Name}] {message}");
    }
}
