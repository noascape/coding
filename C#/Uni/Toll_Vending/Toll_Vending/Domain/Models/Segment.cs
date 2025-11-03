using Toll_Vending.Domain.Enums;

namespace Toll_Vending.Domain.Models;

/// Immutable journey segment
public sealed record Segment(
    RoadCategory Category,
    decimal DistanceKm = 0m,            // used for A1/A2/A3
    string? StructureName = null,       // used for S1/S2
    string? CityZone = null             // used for B1/B2
);
