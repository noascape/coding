using Toll_Vending.Domain.Enums;

namespace Toll_Vending.Domain;

public static class Catalogs
{
    // Base per-km rates for A-roads (standard band)  :contentReference[oaicite:1]{index=1}
    public static readonly IReadOnlyDictionary<RoadCategory, decimal> PerKmRate = new Dictionary<RoadCategory, decimal>
    {
        [RoadCategory.A1] = 0.15m,
        [RoadCategory.A2] = 0.12m,
        [RoadCategory.A3] = 0.08m,
    };

    // Flat rates (standard band) for B/S  :contentReference[oaicite:2]{index=2}
    public static readonly IReadOnlyDictionary<RoadCategory, decimal> FlatRate = new Dictionary<RoadCategory, decimal>
    {
        [RoadCategory.B1] = 15m,
        [RoadCategory.B2] = 8m,
        [RoadCategory.S1] = 6m, // standard sample
        [RoadCategory.S2] = 3m
    };

    // Time factors by category (derived from the time-of-day table)  :contentReference[oaicite:3]{index=3}
    public static readonly IReadOnlyDictionary<RoadCategory, IReadOnlyDictionary<TimeBand, decimal>> TimeFactors
        = new Dictionary<RoadCategory, IReadOnlyDictionary<TimeBand, decimal>>
        {
            [RoadCategory.A1] = new Dictionary<TimeBand, decimal>
            {
                [TimeBand.Standard] = 1.00m,
                [TimeBand.Peak] = 1.20m,
                [TimeBand.OffPeak] = 0.70m,
                [TimeBand.Weekend] = 0.80m
            },
            [RoadCategory.A2] = new Dictionary<TimeBand, decimal>
            {
                [TimeBand.Standard] = 1.00m,
                [TimeBand.Peak] = 1.17m,
                [TimeBand.OffPeak] = 0.75m,
                [TimeBand.Weekend] = 0.83m
            },
            [RoadCategory.A3] = new Dictionary<TimeBand, decimal>
            {
                [TimeBand.Standard] = 1.00m,
                [TimeBand.Peak] = 1.25m,
                [TimeBand.OffPeak] = 0.75m,
                [TimeBand.Weekend] = 0.875m
            },
            [RoadCategory.B1] = new Dictionary<TimeBand, decimal>
            {
                [TimeBand.Standard] = 1.00m,
                [TimeBand.Peak] = 1.20m,
                [TimeBand.OffPeak] = 0.00m,
                [TimeBand.Weekend] = 0.00m
            },
            [RoadCategory.B2] = new Dictionary<TimeBand, decimal>
            {
                [TimeBand.Standard] = 1.00m,
                [TimeBand.Peak] = 1.25m,
                [TimeBand.OffPeak] = 0.00m,
                [TimeBand.Weekend] = 0.00m
            },
            [RoadCategory.S1] = new Dictionary<TimeBand, decimal>
            {
                [TimeBand.Standard] = 1.00m,
                [TimeBand.Peak] = 1.33m,
                [TimeBand.OffPeak] = 0.75m,
                [TimeBand.Weekend] = 0.83m
            },
            [RoadCategory.S2] = new Dictionary<TimeBand, decimal>
            {
                [TimeBand.Standard] = 1.00m,
                [TimeBand.Peak] = 1.33m,
                [TimeBand.OffPeak] = 0.75m,
                [TimeBand.Weekend] = 0.83m
            }
        };

    // Vehicle class multipliers  :contentReference[oaicite:4]{index=4}
    public static readonly IReadOnlyDictionary<VehicleClass, decimal> VehicleMultiplier =
        new Dictionary<VehicleClass, decimal>
        {
            [VehicleClass.Class1] = 0.5m,
            [VehicleClass.Class2] = 1.0m,
            [VehicleClass.Class3] = 1.3m,
            [VehicleClass.Class4] = 2.5m,
            [VehicleClass.Class5] = 3.5m,
            [VehicleClass.Class6] = 5.0m,
            [VehicleClass.Class7] = 3.0m
        };

    // Emission discounts/surcharges by road family  :contentReference[oaicite:5]{index=5}
    public static decimal HighwayEmissionDiscount(EmissionStandard e) => e switch
    {
        EmissionStandard.Euro4 => 0.05m,
        EmissionStandard.Euro5 => 0.10m,
        EmissionStandard.Euro6 => 0.15m,
        EmissionStandard.Hybrid => 0.25m,
        EmissionStandard.Electric or EmissionStandard.Hydrogen => 0.40m,
        _ => 0m
    };

    public static decimal CityEmissionAdjustment(EmissionStandard e) => e switch
    {
        EmissionStandard.Euro0to2 => -0.50m, // surcharge +50%  (negative discount)
        EmissionStandard.Euro3 => -0.25m, // surcharge +25%
        EmissionStandard.Euro4 => 0.00m,
        EmissionStandard.Euro5 => 0.10m,
        EmissionStandard.Euro6 => 0.20m,
        EmissionStandard.Hybrid => 0.35m,
        EmissionStandard.Electric or EmissionStandard.Hydrogen => 0.50m,
        _ => 0m
    };

    // Payment method fees/discounts (fee in £, discount in fraction)  :contentReference[oaicite:6]{index=6}
    public static (decimal fee, decimal discount) PaymentAdjustment(PaymentMethod m) => m switch
    {
        PaymentMethod.TollBoothCash => (0m, 0m),
        PaymentMethod.TollBoothCard => (0.50m, 0m),
        PaymentMethod.Vignette7Days => (0m, 0.05m),
        PaymentMethod.VignetteMonth => (0m, 0.15m),
        PaymentMethod.VignetteYear => (0m, 0.30m),
        PaymentMethod.Transponder => (0m, 0.10m),
        PaymentMethod.VideoToll => (2.00m, -0.10m),
        PaymentMethod.MobileApp => (0m, 0.05m),
        PaymentMethod.FleetAccount => (0m, 0.20m),
        _ => (0m, 0m)
    };

    // Vignette coverage  :contentReference[oaicite:7]{index=7}
    public static bool Covers(VignetteType v, RoadCategory c) => v switch
    {
        VignetteType.Day or VignetteType.Week or VignetteType.Month or VignetteType.Year
            => c is RoadCategory.A1 or RoadCategory.A2 or RoadCategory.A3,
        VignetteType.CityPass => c is RoadCategory.B1 or RoadCategory.B2,
        VignetteType.ComboPass => true,
        _ => false
    };

    // Subscription definition – per-km overage rate (A-roads) and city inclusion  :contentReference[oaicite:8]{index=8}
    public static (int inclusiveKm, decimal overageRate, bool coversCity) Subscription(SubscriptionType s) => s switch
    {
        SubscriptionType.CommuterBasic => (500, 0.10m, false),
        SubscriptionType.CommuterPlus => (1000, 0.08m, false),
        SubscriptionType.Business => (2500, 0.07m, true),
        SubscriptionType.Commercial => (6000, 0.06m, true),
        SubscriptionType.Fleet => (30000, 0.05m, true),
        _ => (0, 0m, false)
    };

    // Special structures with fixed class-specific standard prices (rounded)  :contentReference[oaicite:9]{index=9}
    public static decimal SpecialPrice(string name, VehicleClass cls) => (name.Trim().ToLower(), cls) switch
    {
        ("thames crossing", VehicleClass.Class2) => 6.00m,
        ("thames crossing", VehicleClass.Class4) => 15.00m,
        ("thames crossing", VehicleClass.Class6) => 30.00m,

        ("channel tunnel", VehicleClass.Class2) => 65.00m,
        ("channel tunnel", VehicleClass.Class4) => 162.00m,
        ("channel tunnel", VehicleClass.Class6) => 325.00m,

        ("severn bridge", VehicleClass.Class2) => 7.00m,
        ("severn bridge", VehicleClass.Class4) => 17.50m,
        ("severn bridge", VehicleClass.Class6) => 35.00m,

        ("mountain pass", VehicleClass.Class2) => 12.00m,
        ("mountain pass", VehicleClass.Class4) => 30.00m,
        ("mountain pass", VehicleClass.Class6) => 60.00m,

        ("city bypass", VehicleClass.Class2) => 8.00m,
        ("city bypass", VehicleClass.Class4) => 20.00m,
        ("city bypass", VehicleClass.Class6) => 40.00m,

        _ => FlatRate[RoadCategory.S1] // default to S1 std
    };

    public static decimal ReturnDiscountPercent(string name) => name.Trim().ToLower() switch
    {
        "thames crossing" => 0.50m,   // within 6h  :contentReference[oaicite:10]{index=10}
        "severn bridge" => 1.00m,   // free within 24h
        "city bypass" => 0.30m,   // within 12h
        _ => 0m
    };

    // Caps (A-roads and City)  :contentReference[oaicite:11]{index=11}
    public static (decimal day, decimal week, decimal month) CapsFor(VehicleClass cls) => cls switch
    {
        VehicleClass.Class2 => (45m, 180m, 600m),
        VehicleClass.Class4 => (120m, 480m, 1600m),
        VehicleClass.Class6 => (225m, 900m, 3000m),
        _ => (decimal.MaxValue, decimal.MaxValue, decimal.MaxValue)
    };
    public static (decimal day, decimal week, decimal month) CityCaps => (20m, 80m, 250m);
    public static (decimal day, decimal week, decimal month) CombinedCaps => (60m, 240m, 800m);
}
