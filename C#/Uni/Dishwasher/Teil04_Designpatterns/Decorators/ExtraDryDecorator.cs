namespace Teil04_DesignPatterns.Models
{
    /// Prolonged drying: +30 min, +0.10 kWh
    public sealed class ExtraDryDecorator : WashProgramDecorator
    {
        public ExtraDryDecorator(IWashProgram inner) : base(inner) { }
        protected override string Tag => "ExtraDry";
        public override ushort DurationMinutes => ClampMinutes(Inner.DurationMinutes + 30);
        public override decimal EnergyKWh => Inner.EnergyKWh + 0.10m;
    }
}
