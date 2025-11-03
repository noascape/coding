using System;
using WP6_052.Enums;

namespace WP6_052.Services
{
    public static class BusinessDayCalculator
    {
        public static DateTime AddBusinessDays(DateTime start, int businessDays)
        {
            if (businessDays == 0) return start;

            int dir = Math.Sign(businessDays);
            int left = Math.Abs(businessDays);
            var date = start;

            while (left > 0)
            {
                date = date.AddDays(dir);
                if (Classify(date) == DayKind.BusinessDay)
                    left--;
            }
            return date;
        }

        public static DayKind Classify(DateTime day) =>
            (day.DayOfWeek == DayOfWeek.Saturday || day.DayOfWeek == DayOfWeek.Sunday)
                ? DayKind.Weekend
                : DayKind.BusinessDay;
    }
}