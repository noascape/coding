using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing.Rules;

public sealed class LoyaltyAndExemptionsRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        // Hard exemptions
        if (ctx.Vehicle.IsEmergencyInAction || (ctx.Vehicle.IsDisabledBadge && ctx.Segment.Category is not RoadCategory.S1 and not RoadCategory.S2))
        {
            items.Add(new("Exempt (emergency/disabled)", -running));
            running = 0m; return;
        }

        // Residents in B1/B2 90% off
        if ((ctx.Segment.Category is RoadCategory.B1 or RoadCategory.B2) && ctx.Vehicle.IsResidentBZone)
        {
            items.Add(new("Resident discount 90%", -(running * 0.90m)));
            running *= 0.10m;
        }

        if (ctx.Vehicle.IsLicensedPublicTransport && ctx.Segment.Category is RoadCategory.A1 or RoadCategory.A2 or RoadCategory.A3)
        {
            items.Add(new("Public transport 50%", -(running * 0.50m)));
            running *= 0.50m;
        }

        if (ctx.Vehicle.IsHistoric30Plus)
        {
            items.Add(new("Historic vehicle 30%", -(running * 0.30m)));
            running *= 0.70m;
        }

        if (ctx.Vehicle.IsCarpool3Plus)
        {
            items.Add(new("Carpooling 30%", -(running * 0.30m)));
            running *= 0.70m;
        }

        // Loyalty toggles
        if (ctx.Account.TransponderUser) { items.Add(new("Transponder user 10%", -(running * 0.10m))); running *= 0.90m; }
        if (ctx.Account.CarSharingAccount) { items.Add(new("Carsharing 25%", -(running * 0.25m))); running *= 0.75m; }
        if (ctx.Account.PrePaymentWallet500) { items.Add(new("Pre-payment bonus 5%", -(running * 0.05m))); running *= 0.95m; }

        // Off-Peak Warrior applies only in Off-Peak
        if (ctx.Account.OffPeakWarrior && ctx.TimeBand == TimeBand.OffPeak)
        {
            items.Add(new("Off-Peak Warrior 20%", -(running * 0.20m)));
            running *= 0.80m;
        }
    }
}