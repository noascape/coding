using System;
using Teil01_Attributes;
using Teil01_Attributes.Enums;
using Teil01_Attributes.Models;

var dw = new Dishwasher
{
    Manufacturer = "Miele",
    ModelNumber = "G7310SCi",
    PowerConsumptionKWhPerYear = 237,
    WeightKg = 56.5f,
    EnergyRating = EnergyRating.A3Plus,   // equivalent to “A+++”
    Color = "White",
    Type = DishwasherType.BuiltIn,
    PlaceSettings = 14,
    Status = DishwasherStatus.Off,
    WarrantyMonths = 24
};

Console.WriteLine("Dishwasher instance created.");