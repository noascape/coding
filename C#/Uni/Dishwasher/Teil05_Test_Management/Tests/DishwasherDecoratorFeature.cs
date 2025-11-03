using Teil04_DesignPatterns.Models;
using Teil05_TestManagement.BDD;


namespace Teil05_TestManagement.Tests
{

    // We need a Project Reference to "Teil04_DesignPatterns" for this to work
    public static class DishwasherDecoratorFeature
    {
        public static void Run()
        {
            var feature = new FeatureRunner("Dishwasher program extension via Decorator");


            // Scenario 1: Base + Intensive + ExtraDry + HygienePlus
            feature.Scenario(
            "Base program extended with multiple decorators",
            () =>
            {
                IWashProgram program = new StandardProgram();


                feature.Given("a standard program (55°C, 150 min, 0.95 kWh)", () => { });
                feature.When("IntensiveCleaning, then ExtraDry, then HygienePlus are applied", () =>
                {
                    program = new IntensiveCleaningDecorator(program);
                    program = new ExtraDryDecorator(program);
                    program = new HygienePlusDecorator(program);
                });
                feature.Then("temperature is 70°C, duration is 195 min, energy is 1.35 kWh", () =>
                {
                    AssertEx.AreEqual<byte>(70, program.TemperatureC, "Temperature");
                    AssertEx.AreEqual<ushort>(195, program.DurationMinutes, "Duration");
                    AssertEx.AreEqual<decimal>(1.35m, program.EnergyKWh, "Energy");
                });
            });


            // Scenario 2: Eco mode reduces resource consumption
            feature.Scenario(
            "EcoMode reduces resources and increases runtime",
            () =>
            {
                IWashProgram program = new StandardProgram();


                feature.Given("a standard program with 0.95 kWh and 12 L", () => { });
                feature.When("EcoModeDecorator is applied", () => { program = new EcoModeDecorator(program); });
                feature.Then("temperature is 45°C, duration is 210 min, energy is 0.65 kWh, water -20%", () =>
                {
                    AssertEx.AreEqual<byte>(45, program.TemperatureC, "Temperature");
                    AssertEx.AreEqual<ushort>(210, program.DurationMinutes, "Duration");
                    AssertEx.AreEqual<decimal>(0.65m, program.EnergyKWh, "Energy");
                    AssertEx.AreEqual<decimal>(9.6m, program.WaterLiters, "WaterLiters");
                });
            });
        }
    }
}
