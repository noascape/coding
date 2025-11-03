using System;
using Teil02_Methods.Enums;

namespace Teil02_Methods.Models
{
    public sealed class Dishwasher
    {
        // --- Attributes (compact types + brief validation) ---

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

        // Annual consumption in kWh (0..2000) -> ushort is small and sufficient
        private ushort _powerConsumptionKWhPerYear;
        public ushort PowerConsumptionKWhPerYear
        {
            get => _powerConsumptionKWhPerYear;
            init
            {
                if (value > 2000) throw new ArgumentOutOfRangeException(nameof(PowerConsumptionKWhPerYear), "Max 2000 kWh/year.");
                _powerConsumptionKWhPerYear = value;
            }
        }

        // Weight in kg (1..200) -> float precision is enough
        private float _weightKg;
        public float WeightKg
        {
            get => _weightKg;
            init
            {
                if (value < 1f || value > 200f) throw new ArgumentOutOfRangeException(nameof(WeightKg), "Expected range: 1–200 kg.");
                _weightKg = value;
            }
        }

        public EnergyRating EnergyRating { get; init; }

        private string _color = "";
        public string Color
        {
            get => _color;
            init => _color = string.IsNullOrWhiteSpace(value)
                ? throw new ArgumentException("Color is required.", nameof(Color))
                : value.Trim();
        }

        public DishwasherType Type { get; init; }

        // Place settings (6..16) -> byte is sufficient
        private byte _placeSettings;
        public byte PlaceSettings
        {
            get => _placeSettings;
            init
            {
                if (value < 6 || value > 16) throw new ArgumentOutOfRangeException(nameof(PlaceSettings), "Expected range: 6–16.");
                _placeSettings = value;
            }
        }

        // Status is controlled by methods; default is Off
        public DishwasherStatus Status { get; private set; } = DishwasherStatus.Off;

        // Warranty months (0..120) -> byte is sufficient
        private byte _warrantyMonths;
        public byte WarrantyMonths
        {
            get => _warrantyMonths;
            init
            {
                if (value > 120) throw new ArgumentOutOfRangeException(nameof(WarrantyMonths), "Max 120 months.");
                _warrantyMonths = value;
            }
        }

        // --- Methods (overloads + static + private) ---

        // Overload 1: immediate start
        public void StartProgram(string programName)
            => StartProgram(programName, delayedStart: false, delayHours: 0);

        // Overload 2: delayed start with default delay (1 hour if delayedStart=true)
        public void StartProgram(string programName, bool delayedStart)
            => StartProgram(programName, delayedStart, delayedStart ? 1 : 0);

        // Overload 3: main logic (minimal)
        public void StartProgram(string programName, bool delayedStart, int delayHours)
        {
            if (!CheckDoorLock()) throw new InvalidOperationException("Door is not locked.");
            if (delayHours < 0) delayHours = 0;

            Status = delayedStart ? DishwasherStatus.On : DishwasherStatus.Running;

            Console.WriteLine(delayedStart
                ? $"Program '{programName}' scheduled in {delayHours} hour(s)."
                : $"Program '{programName}' started.");

            Console.WriteLine($"Estimated water: {EstimateWaterUsage(programName)} L");
        }

        // Static method: rough water usage estimate per program (liters)
        public static double EstimateWaterUsage(string programName)
        {
            var p = (programName ?? "").Trim().ToLowerInvariant();
            return p switch
            {
                "eco" => 9.5,
                "auto" => 10.5,
                "intensive" => 12.0,
                "quick" or "express" => 8.0,
                _ => 11.0
            };
        }

        // Private check (simple for demo)
        private bool CheckDoorLock() => true;
    }
}
