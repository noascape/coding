namespace Teil04_DesignPatterns.Models
{
    /// Immutable baseline program for 14 place settings
    public sealed class StandardProgram : WashProgramBase
    {
        public override string Name => "Standard (14 place settings)";
        public override byte TemperatureC => 55;
        public override ushort DurationMinutes => 150; // minutes
        public override decimal EnergyKWh => 0.95m; // per cycle
        public override decimal WaterLiters => 12.0m; // per cycle
    }
}
