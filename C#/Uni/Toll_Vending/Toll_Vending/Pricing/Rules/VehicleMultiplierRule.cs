using Toll_Vending.Domain;
using Toll_Vending.Domain.Models;

namespace Toll_Vending.Pricing.Rules;

public sealed class VehicleMultiplierRule : ITollRule
{
    public void Apply(TollContext ctx, List<LineItem> items, ref decimal running)
    {
        var mult = Catalogs.VehicleMultiplier[ctx.Vehicle.VehicleClass]; 
        var delta = running * mult - running;
        if (delta != 0m)
        {
            items.Add(new($"Vehicle class x{mult:0.##}", delta));
            running += delta;
        }
    }
}