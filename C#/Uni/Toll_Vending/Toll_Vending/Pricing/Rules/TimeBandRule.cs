using Toll_Vending.Domain;
using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;
using Toll_Vending.Utils;

namespace Toll_Vending.Pricing.Rules;

public sealed class TimeBandRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        var factor = Catalogs.TimeFactors[ctx.Segment.Category][ctx.TimeBand];
        var delta = (running * factor) - running;

        if (delta != 0m)
        {
            var label = factor == 0m
                ? $"Time band {Pretty.Band(ctx.TimeBand)} (free)"
                : $"Time band {Pretty.Band(ctx.TimeBand)} x{factor:0.##}";
            items.Add(new(label, delta));
            running += delta;
        }
    }
}