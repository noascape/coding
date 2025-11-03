using Toll_Vending.Domain.Enums;

namespace Toll_Vending.Services;

public static class PenaltyService
{
    // Simplified: days since incident decides tier  :contentReference[oaicite:13]{index=13}
    public static decimal Compute(ViolationType type, int daysSince)
        => type switch
        {
            ViolationType.NoPaymentVideoToll => daysSince switch
            {
                <= 14 => 25m,
                <= 30 => 50m,
                _ => 100m
            },
            ViolationType.WrongVehicleClass => daysSince switch
            {
                <= 14 => 40m,
                <= 30 => 80m,
                _ => 150m
            },
            ViolationType.TransponderTamper => daysSince switch
            {
                <= 14 => 200m,
                <= 30 => 400m,
                _ => 800m
            },
            ViolationType.UnauthorizedCityEntry => daysSince switch
            {
                <= 14 => 65m,
                <= 30 => 130m,
                _ => 200m
            },
            ViolationType.NoVignette => daysSince switch
            {
                <= 14 => 120m,
                <= 30 => 180m,
                _ => 300m
            },
            _ => 0m
        };
}
