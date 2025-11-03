using Toll_Vending.Domain;
using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing.Rules;

public sealed class BasePriceRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        var s = ctx.Segment;
        decimal baseAmount = s.Category switch
        {
            RoadCategory.A1 or RoadCategory.A2 or RoadCategory.A3 =>
                Catalogs.PerKmRate[s.Category] * s.DistanceKm,

            RoadCategory.B1 or RoadCategory.B2 =>
                Catalogs.FlatRate[s.Category],

            RoadCategory.S1 or RoadCategory.S2 =>
                s.StructureName is { Length: > 0 }
                    ? Catalogs.SpecialPrice(s.StructureName!, ctx.Vehicle.VehicleClass) 
                    : Catalogs.FlatRate[s.Category],

            _ => 0m
        };

        items.Add(new LineItem(
            $"Base {s.Category} {(s.DistanceKm > 0 ? $"{s.DistanceKm:0} km" : s.StructureName ?? "")}",
            baseAmount));
        running += baseAmount;
    }
}
