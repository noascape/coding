namespace Teil04_DesignPatterns.Models
{
    /// Sanitizing rinse: +5°C, +0.10 kWh
    public sealed class HygienePlusDecorator : WashProgramDecorator
    {
        public HygienePlusDecorator(IWashProgram inner) : base(inner) { }
        protected override string Tag => "HygienePlus";
        public override byte TemperatureC => ClampTemp(Inner.TemperatureC + 5);
        public override decimal EnergyKWh => Inner.EnergyKWh + 0.10m;
    }
}
