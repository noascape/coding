namespace Toll_Vending.Domain.Enums;

public enum ViolationType : byte
{
    NoPaymentVideoToll,
    WrongVehicleClass,
    TransponderTamper,
    UnauthorizedCityEntry,
    NoVignette
}
