using System;


namespace Teil04_DesignPatterns.Models
{
    public abstract class WashProgramBase : IWashProgram
    {
        public abstract string Name { get; }
        public abstract byte TemperatureC { get; }
        public abstract ushort DurationMinutes { get; }
        public abstract decimal EnergyKWh { get; }
        public abstract decimal WaterLiters { get; }


        public virtual string Describe() =>
        $"{Name}: {TemperatureC}°C, {DurationMinutes} min, {EnergyKWh:0.00} kWh, {WaterLiters:0.0} L";


        protected static byte ClampTemp(int value) => (byte)Math.Min(Math.Max(value, 0), 100);
        protected static ushort ClampMinutes(int value) => (ushort)Math.Min(Math.Max(value, 0), ushort.MaxValue);
        protected static decimal NonNegative(decimal value) => value < 0m ? 0m : value;
    }
}
