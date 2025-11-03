using Toll_Vending.Domain.Enums;

namespace Toll_Vending.Utils;

public static class Pretty
{
    public static string Band(TimeBand b) => b switch
    {
        TimeBand.Peak => "Peak",
        TimeBand.Standard => "Standard",
        TimeBand.OffPeak => "Off-Peak",
        TimeBand.Weekend => "Weekend",
        _ => b.ToString()
    };
}