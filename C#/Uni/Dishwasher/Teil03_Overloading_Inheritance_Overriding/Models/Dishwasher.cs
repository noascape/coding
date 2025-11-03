using System;
using Teil03_OOP.Enums;

namespace Teil03_OOP.Models
{
    /// <summary>Dishwasher specialization with minimal overrides and required methods.</summary>
    public sealed class Dishwasher : KitchenAppliance
    {
        // Extra attributes for a dishwasher
        public DishwasherType Type { get; init; } = DishwasherType.BuiltIn;

        private byte _placeSettings;
        public byte PlaceSettings
        {
            get => _placeSettings;
            init
            {
                if (value < 6 || value > 16) throw new ArgumentOutOfRangeException(nameof(PlaceSettings));
                _placeSettings = value;
            }
        }

        // Simple internal states used by overrides (kept private and minimal)
        private bool _doorLocked = true;           // assume locked by default for demo
        private bool _waterSupplyAvailable = true; // assume water supply available

        // --- Overrides ---

        /// <summary>Checks water supply and door lock before powering on.</summary>
        public override bool TurnOn()
        {
            if (!CheckWaterSupply())
            {
                LogStatus("Cannot turn on: water supply not available.");
                return false;
            }
            if (!CheckDoorLock())
            {
                LogStatus("Cannot turn on: door is not locked.");
                return false;
            }

            Status = ApplianceStatus.On;
            LogStatus("Powered on.");
            return true;
        }

        /// <summary>Turns off and simulates draining residual water.</summary>
        public override void TurnOff()
        {
            LogStatus("Draining residual water...");
            Status = ApplianceStatus.Off;
            LogStatus("Powered off.");
        }

        /// <summary>Cleans filter and checks spray arms.</summary>
        public override string PerformMaintenance()
        {
            var msg = "Cleaned filter; inspected spray arms.";
            LogStatus(msg);
            return msg;
        }

        /// <summary>Adds a simple water cost estimate to the base electricity cost.</summary>
        public override double GetOperatingCost(double electricityPricePerKwh)
        {
            // Electricity: same as base (yearly)
            double electricity = base.GetOperatingCost(electricityPricePerKwh);

            // Water: assume ~10 L per cycle and ~220 cycles/year (typical household)
            const int yearlyCycles = 220;
            const double litersPerCycle = 10.0;
            const double waterPricePerLiter = 0.004; // ~4 €/m³

            double water = yearlyCycles * litersPerCycle * waterPricePerLiter;
            return electricity + water;
        }

        // --- Assignment methods (overloads + static + private) ---

        public void StartProgram(string programName)
            => StartProgram(programName, delayedStart: false, delayHours: 0);

        public void StartProgram(string programName, bool delayedStart)
            => StartProgram(programName, delayedStart, delayedStart ? 1 : 0);

        public void StartProgram(string programName, bool delayedStart, int delayHours)
        {
            if (Status == ApplianceStatus.Off && !TurnOn())
                throw new InvalidOperationException("Cannot start program: appliance failed to turn on.");

            if (delayHours < 0) delayHours = 0;

            Status = delayedStart ? ApplianceStatus.On : ApplianceStatus.Running;

            LogStatus(delayedStart
                ? $"Program '{programName}' scheduled in {delayHours} hour(s)."
                : $"Program '{programName}' started.");

            Console.WriteLine($"Estimated water: {EstimateWaterUsage(programName)} L");
        }

        public static double EstimateWaterUsage(string programName)
        {
            var p = (programName ?? "").Trim().ToLowerInvariant();
            return p switch
            {
                "eco" => 9.5,
                "auto" => 10.5,
                "intensive" => 12.0,
                "quick" or "express" => 8.0,
                _ => 11.0
            };
        }

        // Private checks kept deliberately simple
        private bool CheckDoorLock() => _doorLocked;
        private bool CheckWaterSupply() => _waterSupplyAvailable;
    }
}