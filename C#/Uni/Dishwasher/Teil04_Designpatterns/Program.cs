using System;
using Teil04_DesignPatterns.Enums;
using Teil04_DesignPatterns.Models;


namespace Teil04_DesignPatterns
{
    public static class Program
    {
        public static void Main()
        {
            // Quick demo (tests live in Teil05 project)
            var dishwasher = new Dishwasher("Miele", "G7310SCi", EnergyRating.ATriplePlus);
            IWashProgram program = new StandardProgram();
            program = new IntensiveCleaningDecorator(program);
            program = new ExtraDryDecorator(program);
            program = new HygienePlusDecorator(program);


            Console.WriteLine(dishwasher.Run(program));
        }
    }
}