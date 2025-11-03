namespace Toll_Vending.Domain.Enums;

public enum RoadCategory : byte
{
    A1, A2, A3, // Highways/Overland (per-km)
    B1, B2,     // City toll rings (flat/day)
    S1, S2      // Special structures (fixed)
}
