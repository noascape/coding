using Toll_Vending.Domain;
using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing.Rules;

public sealed class VignetteRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        var v = ctx.Account.ActiveVignette;
        if (v == VignetteType.None) return;

        if (Catalogs.Covers(v, ctx.Segment.Category))
        {
            items.Add(new($"Covered by {v}", -running));
            running = 0m;
        }
    }
}
