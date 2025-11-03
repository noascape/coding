using System;
using Teil03_OOP;
using Teil03_OOP.Enums;
using Teil03_OOP.Models;

var dishwasher = new Dishwasher
{
    Manufacturer = "Miele",
    ModelNumber = "G7310SCi",
    PowerConsumptionKWhPerYear = 237, // kWh/year
    WeightKg = 56.5,
    EnergyRating = EnergyRating.ATriplePlus, // "A+++"
    Color = ApplianceColor.White,
    WarrantyMonths = 24,
    Type = DishwasherType.BuiltIn,
    PlaceSettings = 14
};

// Show overrides + overloads (short demo)
Console.WriteLine($"TurnOn OK: {dishwasher.TurnOn()}");
dishwasher.StartProgram("Eco");
Console.WriteLine($"Yearly operating cost (0.35 €/kWh): {dishwasher.GetOperatingCost(0.35):0.00} €");
Console.WriteLine(dishwasher.PerformMaintenance());
dishwasher.TurnOff();

// Static compare example (negative => first is better)
Console.WriteLine($"Compare ratings: {KitchenAppliance.CompareEnergyRatings(EnergyRating.ATriplePlus, EnergyRating.APlus)}");