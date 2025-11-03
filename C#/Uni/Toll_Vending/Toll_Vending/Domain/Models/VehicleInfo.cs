using Toll_Vending.Domain.Enums;

namespace Toll_Vending.Domain.Models;

public sealed record VehicleInfo(
    VehicleClass VehicleClass,
    EmissionStandard Emission,
    bool IsResidentBZone = false,
    bool IsDisabledBadge = false,
    bool IsEmergencyInAction = false,
    bool IsLicensedPublicTransport = false,
    bool IsHistoric30Plus = false,
    bool IsCarpool3Plus = false
)
{
    public bool IsMotorcycle => VehicleClass == VehicleClass.Class1;
}
