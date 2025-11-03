namespace Teil04_DesignPatterns.Models
{
    /// Base decorator (OCP): wraps another IWashProgram
    public abstract class WashProgramDecorator : WashProgramBase
    {
        protected readonly IWashProgram Inner;
        protected WashProgramDecorator(IWashProgram inner) => Inner = inner;


        protected abstract string Tag { get; }
        public override string Name => $"{Inner.Name} + {Tag}";
        public override byte TemperatureC => Inner.TemperatureC;
        public override ushort DurationMinutes => Inner.DurationMinutes;
        public override decimal EnergyKWh => Inner.EnergyKWh;
        public override decimal WaterLiters => Inner.WaterLiters;
        public override string Describe() => $"{Inner.Describe()} -> {Tag}";
    }
}