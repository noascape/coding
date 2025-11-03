using System;
using System.Collections.Generic;
using pizza_delivery.Models;

namespace pizza_delivery.Services
{
    public interface IOrderAnalysisService
    {
        // a
        IEnumerable<Order> GetExpensiveOutForDeliveryOrders(DateTime targetDate);
        // b
        IEnumerable<PostalCodeOrderStatsDTO> GetOrderStatsByPostalCode();
        // c
        IEnumerable<ToppingStatsDTO> GetTopToppings(int topCount = 5);
        // d
        IEnumerable<ActiveCustomerDTO> GetActiveCustomers();
        // e
        IEnumerable<DriverPerformanceDto> GetDriverPerformance(DateTime targetDate);
    }
}
