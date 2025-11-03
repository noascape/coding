using pizza_delivery.Enums;
using pizza_delivery.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace pizza_delivery.Services
{
    /// Implements the LINQ-tasks (a–e).
    public class OrderAnalysisService : IOrderAnalysisService
    {
        private readonly IEnumerable<Order> _orders;
        private readonly IEnumerable<Driver> _drivers;

        public OrderAnalysisService(IEnumerable<Order> orders, IEnumerable<Driver> drivers)
        {
            _orders = orders ?? throw new ArgumentNullException(nameof(orders));
            _drivers = drivers ?? throw new ArgumentNullException(nameof(drivers));
        }

        // (a)
        public IEnumerable<Order> GetExpensiveOutForDeliveryOrders(DateTime targetDate)
        {
            if (targetDate == default)
                throw new ArgumentException("Date must not be default.", nameof(targetDate));

            return _orders
                .Where(o => o.OrderDate.Date == targetDate.Date
                            && o.TotalPrice > 30m
                            && o.Status == OrderStatus.OutForDelivery)
                .OrderByDescending(o => o.OrderDate)
                .ToList();
        }

        // (b)
        public IEnumerable<PostalCodeOrderStatsDTO> GetOrderStatsByPostalCode()
        {
            return _orders
                .GroupBy(o => o.Customer.PostalCode)
                .Select(g => new PostalCodeOrderStatsDTO
                {
                    PostalCode = g.Key,
                    OrderCount = g.Count(),
                    AverageOrderValue = g.Average(o => o.TotalPrice),
                    TotalRevenue = g.Sum(o => o.TotalPrice)
                })
                .Where(s => s.OrderCount > 5)
                .OrderByDescending(s => s.TotalRevenue)
                .ToList();
        }

        // (c)
        public IEnumerable<ToppingStatsDTO> GetTopToppings(int topCount = 5)
        {
            if (topCount <= 0) throw new ArgumentOutOfRangeException(nameof(topCount));

            return _orders
                .SelectMany(o => o.Toppings)
                .GroupBy(t => t.Name)
                .Select(g => new ToppingStatsDTO { ToppingName = g.Key, Count = g.Count() })
                .OrderByDescending(x => x.Count)
                .ThenBy(x => x.ToppingName)
                .Take(topCount)
                .ToList();
        }

        // (d)
        public IEnumerable<ActiveCustomerDTO> GetActiveCustomers()
        {
            DateTime since = DateTime.Today.AddDays(-30);

            return _orders
                .Where(o => o.OrderDate >= since)
                .GroupBy(o => o.Customer)
                .Select(g => new ActiveCustomerDTO
                {
                    CustomerName = g.Key.Name,
                    PostalCode = g.Key.PostalCode,
                    OrderCount = g.Count(),
                    AverageOrderValue = g.Average(o => o.TotalPrice)
                })
                .Where(c => c.OrderCount >= 3 && c.AverageOrderValue > 25m)
                .OrderByDescending(c => c.AverageOrderValue)
                .ThenByDescending(c => c.OrderCount)
                .ToList();
        }

        // (e)
        public IEnumerable<DriverPerformanceDto> GetDriverPerformance(DateTime targetDate)
        {
            if (targetDate == default)
                throw new ArgumentException("Date must not be default.", nameof(targetDate));

            var dayOrders = _orders
                .Where(o => o.Driver != null && o.OrderDate.Date == targetDate.Date && o.DeliveryTime.HasValue)
                .ToList();

            return _drivers
                .GroupJoin(
                    dayOrders,
                    d => d.Id,
                    o => o.Driver!.Id,
                    (d, os) => new DriverPerformanceDto
                    {
                        DriverName = d.Name,
                        DeliveredOrdersCount = os.Count(),
                        AverageDeliveryTimeMinutes = os.Any() ? os.Average(x => x.DeliveryTime!.Value.TotalMinutes) : 0,
                        MaxDeliveryTimeMinutes = os.Any() ? os.Max(x => x.DeliveryTime!.Value.TotalMinutes) : 0
                    })
                .OrderByDescending(x => x.DeliveredOrdersCount)
                .ToList();
        }
    }
}