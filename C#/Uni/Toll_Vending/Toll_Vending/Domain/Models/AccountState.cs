using Toll_Vending.Domain.Enums;

namespace Toll_Vending.Domain.Models;

public sealed record AccountState(
    PaymentMethod PaymentMethod,
    VignetteType ActiveVignette = VignetteType.None,
    SubscriptionType Subscription = SubscriptionType.None,
    int InclusiveKmRemaining = 0,          // for A-roads
    bool SubscriptionCoversCity = false,   // Business etc.
    bool FrequentUser = false,             // 15% next month (not applied here)
    bool OffPeakWarrior = false,           // 20% off Off-Peak
    bool LowEmissionRegistered = false,    // up to 40%
    bool TransponderUser = false,          // 10% always (also via Payment)
    bool PrePaymentWallet500 = false,      // +5% bonus
    bool CarSharingAccount = false         // 25% permanent
);
