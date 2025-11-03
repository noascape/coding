using System;
using Teil01_Attributes.Enums;

namespace Teil01_Attributes.Models
{
    public sealed class Dishwasher
    {
        private string _manufacturer = "";
        public string Manufacturer
        {
            get => _manufacturer;
            init => _manufacturer = string.IsNullOrWhiteSpace(value)
                ? throw new ArgumentException("Manufacturer required.", nameof(Manufacturer))
                : value.Trim();
        }

        private string _modelNumber = "";
        public string ModelNumber
        {
            get => _modelNumber;
            init => _modelNumber = string.IsNullOrWhiteSpace(value)
                ? throw new ArgumentException("ModelNumber required.", nameof(ModelNumber))
                : value.Trim();
        }

        // 0..2000 kWh/a: ushort is small and sufficient
        private ushort _powerKWhPerYear;
        public ushort PowerConsumptionKWhPerYear
        {
            get => _powerKWhPerYear;
            init => _powerKWhPerYear = value <= 2000
                ? value
                : throw new ArgumentOutOfRangeException(nameof(PowerConsumptionKWhPerYear), "Max 2000 kWh/a.");
        }

        // 1..200 kg: float is enough
        private float _weightKg;
        public float WeightKg
        {
            get => _weightKg;
            init => _weightKg = (value >= 1f && value <= 200f)
                ? value
                : throw new ArgumentOutOfRangeException(nameof(WeightKg), "1..200 kg.");
        }

        public EnergyRating EnergyRating { get; init; }

        private string _color = "";
        public string Color
        {
            get => _color;
            init => _color = string.IsNullOrWhiteSpace(value)
                ? throw new ArgumentException("Color required.", nameof(Color))
                : value.Trim();
        }

        public DishwasherType Type { get; init; }

        // 6..16 place settings: byte is sufficient
        private byte _placeSettings;
        public byte PlaceSettings
        {
            get => _placeSettings;
            init => _placeSettings = (value >= 6 && value <= 16)
                ? value
                : throw new ArgumentOutOfRangeException(nameof(PlaceSettings), "6..16.");
        }

        // Default Off
        public DishwasherStatus Status { get; init; } = DishwasherStatus.Off;

        // 0..120 months: byte is enough
        private byte _warrantyMonths;
        public byte WarrantyMonths
        {
            get => _warrantyMonths;
            init => _warrantyMonths = value <= 120
                ? value
                : throw new ArgumentOutOfRangeException(nameof(WarrantyMonths), "Max 120 months.");
        }
    }
}