using Teil04_DesignPatterns.Enums;


namespace Teil04_DesignPatterns.Models
{
    /// Device only depends on abstractions (DIP)
    public sealed class Dishwasher
    {
        public string Manufacturer { get; }
        public string ModelNumber { get; }
        public EnergyRating EnergyRating { get; }


        public Dishwasher(string manufacturer, string modelNumber, EnergyRating rating)
        {
            Manufacturer = manufacturer;
            ModelNumber = modelNumber;
            EnergyRating = rating;
        }


        public string Run(IWashProgram program) =>
        $"{Manufacturer} {ModelNumber} runs -> {program.Describe()}";
    }
}
