using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;
using Toll_Vending.Pricing.Rules;

namespace Toll_Vending.Pricing;

public sealed class TollCalculator
{
    private readonly ITollRule[] _rules;

    public TollCalculator()
    {
        // Order matters: build base, apply time, class, emissions, access, passes/subs, payment, loyalty, caps.
        _rules = new ITollRule[]
        {
            new BasePriceRule(),
            new TimeBandRule(),
            new VehicleMultiplierRule(),
            new EmissionRule(),
            new CityAccessRule(),
            new VignetteRule(),
            new SubscriptionRule(),
            new PaymentRule(),
            new LoyaltyAndExemptionsRule(),
            new CapsRule()
        };
    }

    public Receipt PriceSegment(TollContext ctx)
    {
        var items = new List<LineItem>();
        decimal running = 0m;

        foreach (var r in _rules) r.Apply(ctx, items, ref running);

        // Round at the end
        for (int i = 0; i < items.Count; i++)
            items[i] = items[i] with { Amount = Math.Round(items[i].Amount, 2, MidpointRounding.AwayFromZero) };

        return new Receipt(items);
    }

    public Receipt PriceJourney(IEnumerable<TollContext> contexts)
    {
        var items = new List<LineItem>();
        foreach (var c in contexts)
        {
            var receipt = PriceSegment(c);
            items.AddRange(receipt.Items);
        }
        return new Receipt(items);
    }
}
