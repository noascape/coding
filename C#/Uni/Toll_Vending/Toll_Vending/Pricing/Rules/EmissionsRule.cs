using Toll_Vending.Domain;
using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing.Rules;

public sealed class EmissionRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        var cat = ctx.Segment.Category;
        var e = ctx.Vehicle.Emission;

        decimal pct = cat switch
        {
            RoadCategory.A1 or RoadCategory.A2 or RoadCategory.A3 => Catalogs.HighwayEmissionDiscount(e),
            RoadCategory.B1 or RoadCategory.B2 => Catalogs.CityEmissionAdjustment(e),
            _ => 0m
        };

        if (pct > 0m)
        {
            var cut = -(running * pct);
            items.Add(new($"Emission discount {(pct * 100):0}% ", cut));
            running += cut;
        }
        else if (pct < 0m)
        {
            var add = running * (-pct);
            items.Add(new($"Emission surcharge {(-pct * 100):0}% ", add));
            running += add;
        }
    }
}
