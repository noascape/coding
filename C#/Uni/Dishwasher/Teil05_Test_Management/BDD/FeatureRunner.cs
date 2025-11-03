using System;


namespace Teil05_TestManagement.BDD
{
    public sealed class FeatureRunner
    {
        private readonly string _feature;
        public FeatureRunner(string feature) => _feature = feature;


        public void Scenario(string title, Action body)
        {
            Console.WriteLine($"\nFeature: {_feature}\nScenario: {title}");
            body();
            Console.WriteLine("Result: OK");
        }


        public void Given(string text, Action step) { Step("Given", text, step); }
        public void When(string text, Action step) { Step("When", text, step); }
        public void Then(string text, Action step) { Step("Then", text, step); }


        private static void Step(string kind, string text, Action step)
        {
            Console.WriteLine($" {kind} {text}");
            step();
        }
    }
}