using System;
using WP6_059.Enums;

namespace WP6_059.Services
{
    public static class TimeOfDayHelper
    {
        public static string GetTimeOfDay(DateTime when) =>
            Classify(when) switch
            {
                TimeOfDay.Morning => "Morning",
                TimeOfDay.Afternoon => "Afternoon",
                _ => "Evening"
            };

        public static TimeOfDay Classify(DateTime when)
        {
            int h = when.Hour;
            if (h >= 5 && h < 12) return TimeOfDay.Morning;
            if (h >= 12 && h < 18) return TimeOfDay.Afternoon;
            return TimeOfDay.Evening;
        }
    }
}