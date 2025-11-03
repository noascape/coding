using Toll_Vending.Domain;
using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing.Rules;

public sealed class PaymentRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        var (fee, pct) = Catalogs.PaymentAdjustment(ctx.Account.PaymentMethod);
        if (pct > 0m)
        {
            items.Add(new($"Payment discount {ctx.Account.PaymentMethod} {(pct * 100):0}%", -(running * pct)));
            running -= running * pct;
        }
        else if (pct < 0m)
        {
            items.Add(new($"Payment surcharge {ctx.Account.PaymentMethod} {(-pct * 100):0}%", running * (-pct)));
            running += running * (-pct);
        }

        if (fee > 0m)
        {
            items.Add(new($"Payment fee {ctx.Account.PaymentMethod}", fee));
            running += fee;
        }
    }
}
