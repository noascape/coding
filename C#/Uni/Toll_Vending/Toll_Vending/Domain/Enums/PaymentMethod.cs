namespace Toll_Vending.Domain.Enums;

public enum PaymentMethod : byte
{
    TollBoothCash,
    TollBoothCard,
    Vignette7Days,
    VignetteMonth,
    VignetteYear,
    Transponder,
    VideoToll,
    MobileApp,
    FleetAccount
}
