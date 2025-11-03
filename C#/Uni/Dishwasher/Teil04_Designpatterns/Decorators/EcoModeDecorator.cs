namespace Teil04_DesignPatterns.Models
{
    /// Eco mode trades time for resources: -10°C, +60 min, -0.30 kWh, -20% water
    public sealed class EcoModeDecorator : WashProgramDecorator
    {
        public EcoModeDecorator(IWashProgram inner) : base(inner) { }
        protected override string Tag => "EcoMode";
        public override byte TemperatureC => ClampTemp(Inner.TemperatureC - 10);
        public override ushort DurationMinutes => ClampMinutes(Inner.DurationMinutes + 60);
        public override decimal EnergyKWh => NonNegative(Inner.EnergyKWh - 0.30m);
        public override decimal WaterLiters => Inner.WaterLiters * 0.80m;
    }
}
