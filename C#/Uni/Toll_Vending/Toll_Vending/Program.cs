using Toll_Vending.Domain;
using Toll_Vending.Domain.Enums;
using Toll_Vending.Domain.Models;
using Toll_Vending.Pricing;

namespace Toll_Vending;

public static class Program
{
    public static void Main()
    {
        var calc = new TollCalculator();
        var vehicle = new VehicleInfo(VehicleClass.Class2, EmissionStandard.Euro6);

        // 1) London–Manchester (350 km, A1), Peak
        var ctx1 = new TollContext(
            new Segment(RoadCategory.A1, DistanceKm: 350),
            vehicle,
            new AccountState(PaymentMethod.TollBoothCash),
            TimeBand.Peak,
            DateTime.Today
        );

        Console.WriteLine("Scenario: London–Manchester 350 km (Peak)");
        var r1 = calc.PriceSegment(ctx1);
        Console.WriteLine(r1 + "\n");

        // With Transponder (−10 %)
        var r1t = calc.PriceSegment(ctx1 with { Account = new AccountState(PaymentMethod.Transponder) });
        Console.WriteLine("…with Transponder");
        Console.WriteLine(r1t + "\n");

        // Subscription (Business) – Tuple erst definieren, dann nutzen!
        var business = Catalogs.Subscription(SubscriptionType.Business);
        var r1sub = calc.PriceSegment(ctx1 with
        {
            Account = new AccountState(
                PaymentMethod.TollBoothCash,
                Subscription: SubscriptionType.Business,
                InclusiveKmRemaining: 0,
                SubscriptionCoversCity: business.coversCity)
        });
        Console.WriteLine("…with Subscription (Business) overage");
        Console.WriteLine(r1sub + "\n");

        // Vignette (Month) – A-Straßen abgedeckt
        var r1vig = calc.PriceSegment(ctx1 with
        {
            Account = new AccountState(PaymentMethod.VignetteMonth, ActiveVignette: VignetteType.Month)
        });
        Console.WriteLine("…with Vignette (Month)");
        Console.WriteLine(r1vig + "\n");

        // 2) City B1
        var cityCtx = new TollContext(
            new Segment(RoadCategory.B1, CityZone: "Inner ring"),
            vehicle,
            new AccountState(PaymentMethod.TollBoothCash),
            TimeBand.Peak,
            DateTime.Today
        );

        Console.WriteLine("Scenario: City entry B1 (Peak)");
        Console.WriteLine(calc.PriceSegment(cityCtx) + "\n");

        Console.WriteLine("…with Transponder");
        Console.WriteLine(calc.PriceSegment(cityCtx with { Account = new AccountState(PaymentMethod.Transponder) }) + "\n");

        Console.WriteLine("…with Business subscription (included)");
        var businessAcc = new AccountState(PaymentMethod.TollBoothCash, Subscription: SubscriptionType.Business, SubscriptionCoversCity: true);
        Console.WriteLine(calc.PriceSegment(cityCtx with { Account = businessAcc }) + "\n");

        // 3) Thames Crossing, Off-Peak
        var thames = new TollContext(
            new Segment(RoadCategory.S1, StructureName: "Thames Crossing"),
            vehicle,
            new AccountState(PaymentMethod.TollBoothCash),
            TimeBand.OffPeak,
            DateTime.Today
        );
        Console.WriteLine("Scenario: Thames Crossing (Off-Peak)");
        Console.WriteLine(calc.PriceSegment(thames) + "\n");
        Console.WriteLine("…with Transponder");
        Console.WriteLine(calc.PriceSegment(thames with { Account = new AccountState(PaymentMethod.Transponder) }) + "\n");

        // 4) Weekend trip 200 km on A1
        var weekend = new TollContext(
            new Segment(RoadCategory.A1, DistanceKm: 200),
            vehicle,
            new AccountState(PaymentMethod.TollBoothCash),
            TimeBand.Weekend,
            DateTime.Today
        );
        Console.WriteLine("Scenario: Weekend trip 200 km (A1)");
        Console.WriteLine(calc.PriceSegment(weekend) + "\n");
        Console.WriteLine("…with Transponder");
        Console.WriteLine(calc.PriceSegment(weekend with { Account = new AccountState(PaymentMethod.Transponder) }) + "\n");

        // 5) Off-Peak city B1 is free
        var cityFree = cityCtx with { TimeBand = TimeBand.OffPeak };
        Console.WriteLine("Scenario: City B1 (Off-Peak) – free");
        Console.WriteLine(calc.PriceSegment(cityFree) + "\n");
    }
}