using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing;

public sealed record TollContext(
    Segment Segment,
    VehicleInfo Vehicle,
    AccountState Account,
    TimeBand TimeBand,
    DateTime Date,                 // <- statt DateOnly
    bool IsReturnWithinWindow = false
);

public interface ITollRule
{
    void Apply(TollContext ctx, List<LineItem> items, ref decimal running);
}