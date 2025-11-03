using Toll_Vending.Domain;             
using Toll_Vending.Domain.Enums;       
using Toll_Vending.Domain.Models;      
using Toll_Vending.Pricing;            

namespace Toll_Vending.Pricing.Rules;

public sealed class CapsRule : ITollRule
{
    // Provide what the user has already spent to enforce caps (here mocked in AccountState)
    // We enforce a per-segment guard to not exceed the day cap.
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        var (aDay, _, _) = Catalogs.CapsFor(ctx.Vehicle.VehicleClass);
        var (cityDay, _, _) = Catalogs.CityCaps;
        var (comboDay, _, _) = Catalogs.CombinedCaps;

        var cap = ctx.Segment.Category switch
        {
            RoadCategory.B1 or RoadCategory.B2 => cityDay,
            RoadCategory.A1 or RoadCategory.A2 or RoadCategory.A3 => aDay,
            _ => comboDay
        };

        var roundedSoFar = items.Sum(i => Math.Round(i.Amount, 2, MidpointRounding.AwayFromZero));
        var deltaToCap = cap - roundedSoFar;

        if (deltaToCap < 0m)
        {
            items.Add(new LineItem($"Capped to daily max ({cap:C0})", deltaToCap));
            running += deltaToCap; 
        }
    }
}
