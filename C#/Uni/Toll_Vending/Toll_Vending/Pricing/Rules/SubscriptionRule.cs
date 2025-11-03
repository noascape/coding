using Toll_Vending.Domain;
using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing.Rules;

public sealed class SubscriptionRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        if (ctx.Account.Subscription == SubscriptionType.None) return;

        var (inclusive, overage, coversCity) = Catalogs.Subscription(ctx.Account.Subscription);

        // A-roads: cover per km first, charge overage at plan rate
        if (ctx.Segment.Category is RoadCategory.A1 or RoadCategory.A2 or RoadCategory.A3)
        {
            var km = ctx.Segment.DistanceKm;
            var used = Math.Min(km, ctx.Account.InclusiveKmRemaining);
            if (used > 0)
            {
                items.Add(new($"Subscription inclusive {used:0} km", -running * (used / km)));
                running *= (km - used) / km;
            }

            if (km - used > 0)
            {
                var overKm = km - used;
                var overCost = overKm * overage;

                items.Add(new($"Subscription overage {overKm:0} km @ {overage:C}/km", -running));

                items.Add(new("Subscription charge", overCost));
                running = overCost;
            }
        }

        // City rings: if plan covers city, it becomes included
        if ((ctx.Segment.Category is RoadCategory.B1 or RoadCategory.B2) && coversCity)
        {
            items.Add(new("Subscription includes city entry", -running));
            running = 0m;
        }
    }
}
