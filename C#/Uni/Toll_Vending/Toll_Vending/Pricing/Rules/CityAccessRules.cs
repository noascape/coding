using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing.Rules;

/// Enforces simplified B1 access restrictions  :contentReference[oaicite:12]{index=12}
public sealed class CityAccessRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        if (ctx.Segment.Category is not RoadCategory.B1) return;

        var e = ctx.Vehicle.Emission;
        if (e == EmissionStandard.Euro0to2)
            throw new InvalidOperationException("Access denied: Euro 0–2 vehicles are banned in B1.");

        if (e == EmissionStandard.Euro3 && ctx.TimeBand != TimeBand.OffPeak)
            throw new InvalidOperationException("Access restricted: Euro 3 in B1 allowed Off-Peak only.");

        if (e == EmissionStandard.Euro4 && ctx.TimeBand == TimeBand.Peak)
            throw new InvalidOperationException("Access restricted: Euro 4 in B1 not permitted during Peak.");
    }
}
