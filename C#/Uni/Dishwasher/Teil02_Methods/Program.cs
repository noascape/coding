using System;
using Teil02_Methods.Enums;
using Teil02_Methods.Models;

var dishwasher = new Dishwasher
{
    Manufacturer = "Miele",
    ModelNumber = "G7310SCi",
    PowerConsumptionKWhPerYear = 237,
    WeightKg = 56.5f,
    EnergyRating = EnergyRating.ATriplePlus, // equals "A+++"
    Color = "White",
    Type = DishwasherType.BuiltIn,
    PlaceSettings = 14,
    WarrantyMonths = 24
};

// Show the overloads with minimal console output:
dishwasher.StartProgram("Eco");                       // immediate
Console.WriteLine($"Status: {dishwasher.Status}");

dishwasher.StartProgram("Auto", delayedStart: true);  // delayed (1h)
Console.WriteLine($"Status: {dishwasher.Status}");

dishwasher.StartProgram("Intensive", true, 2);        // delayed (2h)
Console.WriteLine($"Eco water: {Dishwasher.EstimateWaterUsage("Eco")} L");
