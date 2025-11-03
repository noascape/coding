namespace Teil04_DesignPatterns.Models
{
    /// Stronger spray/heat: +10°C, +15 min, +0.20 kWh, +1 L
    public sealed class IntensiveCleaningDecorator : WashProgramDecorator
    {
        public IntensiveCleaningDecorator(IWashProgram inner) : base(inner) { }
        protected override string Tag => "IntensiveCleaning";
        public override byte TemperatureC => ClampTemp(Inner.TemperatureC + 10);
        public override ushort DurationMinutes => ClampMinutes(Inner.DurationMinutes + 15);
        public override decimal EnergyKWh => Inner.EnergyKWh + 0.20m;
        public override decimal WaterLiters => Inner.WaterLiters + 1.0m;
    }
}