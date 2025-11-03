namespace Teil04_DesignPatterns.Models
{
    /// Minimal contract – keeps ISP intact and programs composable
    public interface IWashProgram
    {
        string Name { get; }
        byte TemperatureC { get; }
        ushort DurationMinutes { get; }
        decimal EnergyKWh { get; }
        decimal WaterLiters { get; }
        string Describe();
    }
}
