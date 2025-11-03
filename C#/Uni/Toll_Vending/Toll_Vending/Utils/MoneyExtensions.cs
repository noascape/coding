namespace Toll_Vending.Utils;

public static class MoneyExtensions
{
    public static decimal ClipNeg(this decimal v) => v < 0m ? 0m : v;
    public static decimal ApplyDiscount(this decimal amount, decimal pct) => (amount * (1m - pct)).ClipNeg();
    public static decimal ApplySurcharge(this decimal amount, decimal pct) => (amount * (1m + pct)).ClipNeg();
    public static decimal Round2(this decimal v) => Math.Round(v, 2, MidpointRounding.AwayFromZero);
}