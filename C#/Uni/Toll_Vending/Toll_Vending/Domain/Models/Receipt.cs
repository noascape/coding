namespace Toll_Vending.Domain.Models;

public sealed record LineItem(string Title, decimal Amount);
public sealed record Receipt(IReadOnlyList<LineItem> Items)
{
    public decimal Total => Items.Sum(i => i.Amount);
    public override string ToString()
        => string.Join(Environment.NewLine, Items.Select(i => $"{i.Title,-40} {i.Amount,8:C2}"))
           + Environment.NewLine + new string('-', 54)
           + Environment.NewLine + $"TOTAL{"",30}{Total,8:C2}";
}
