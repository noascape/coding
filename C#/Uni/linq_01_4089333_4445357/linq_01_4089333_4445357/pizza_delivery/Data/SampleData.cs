using pizza_delivery.Enums;
using pizza_delivery.Models;
using System;
using System.Collections.Generic;

namespace pizza_delivery.Data
{
    public static class SampleData
    {
        /// <summary>
        /// Creates sample data for testing the pizza delivery system.
        /// Returns a tuple with a list of Orders and Drivers.
        /// </summary>
        public static (List<Order> Orders, List<Driver> Drivers) CreateSampleData()
        {
            // === DRIVERS ===
            var drivers = new List<Driver>
            {
                new Driver(1, "Lukas"),
                new Driver(2, "Sara"),
                new Driver(3, "Timo")
            };

            // === CUSTOMERS ===
            var customers = new List<Customer>
            {
                new Customer("Anna Müller", "Hauptstraße 1", "10115"),
                new Customer("Bernd Schmidt", "Bahnhofstraße 12", "10115"),
                new Customer("Clara Weber", "Marktplatz 5", "10243"),
                new Customer("David Lange", "Schillerstraße 8", "10367"),
                new Customer("Eva König", "Goethestraße 9", "10367"),
                new Customer("Frank Becker", "Friedrichstraße 20", "10367"),
                new Customer("Gina Schuster", "Rosenweg 4", "10367"),
                new Customer("Hans Klein", "Uhlandstraße 10", "10367"),
                new Customer("Iris Hoffmann", "Bismarckstraße 15", "10367"),
            };

            // === ORDERS ===
            var orders = new List<Order>
            {
                // --- Postal code 10115 (2 orders) ---
                new Order(1001, customers[0], DateTime.Today.AddDays(-5).AddHours(10), 34.50m, OrderStatus.OutForDelivery,
                    new List<Topping>{ new Topping("Salami"), new Topping("Käse") })
                    { Driver = drivers[0], DeliveryTime = TimeSpan.FromMinutes(25) },

                new Order(1002, customers[1], DateTime.Today.AddDays(-2).AddHours(11), 19.90m, OrderStatus.Delivered,
                    new List<Topping>{ new Topping("Pilze"), new Topping("Käse") })
                    { Driver = drivers[1], DeliveryTime = TimeSpan.FromMinutes(40) },

                // --- Postal code 10243 (1 order) ---
                new Order(1003, customers[2], DateTime.Today.AddDays(-1).AddHours(12), 42.00m, OrderStatus.OutForDelivery,
                    new List<Topping>{ new Topping("Thunfisch"), new Topping("Zwiebeln") })
                    { Driver = drivers[2], DeliveryTime = TimeSpan.FromMinutes(30) },

                // --- Postal code 10367 (several orders) ---
                new Order(1004, customers[3], DateTime.Today.AddDays(-4).AddHours(13), 15.20m, OrderStatus.Delivered,
                    new List<Topping>{ new Topping("Schinken"), new Topping("Käse") })
                    { Driver = drivers[1], DeliveryTime = TimeSpan.FromMinutes(22) },

                // Eva König – 4 orders last 30 days
                new Order(1005, customers[4], DateTime.Today.AddDays(-1).AddHours(14), 29.50m, OrderStatus.OutForDelivery,
                    new List<Topping>{ new Topping("Käse"), new Topping("Oliven") })
                    { Driver = drivers[0], DeliveryTime = TimeSpan.FromMinutes(35) },

                new Order(1011, customers[4], DateTime.Today.AddDays(-10).AddHours(12), 31.80m, OrderStatus.Delivered,
                    new List<Topping>{ new Topping("Salami"), new Topping("Champignons") })
                    { Driver = drivers[1], DeliveryTime = TimeSpan.FromMinutes(27) },

                new Order(1012, customers[4], DateTime.Today.AddDays(-15).AddHours(17), 26.40m, OrderStatus.Delivered,
                    new List<Topping>{ new Topping("Käse"), new Topping("Zwiebeln") })
                    { Driver = drivers[2], DeliveryTime = TimeSpan.FromMinutes(28) },

                new Order(1013, customers[4], DateTime.Today.AddDays(-25).AddHours(19), 35.10m, OrderStatus.Delivered,
                    new List<Topping>{ new Topping("Schinken"), new Topping("Käse") })
                    { Driver = drivers[1], DeliveryTime = TimeSpan.FromMinutes(32) },

                // Other random orders
                new Order(1006, customers[5], DateTime.Today.AddHours(15), 25.00m, OrderStatus.Delivered,
                    new List<Topping>{ new Topping("Salami"), new Topping("Champignons") })
                    { Driver = drivers[2], DeliveryTime = TimeSpan.FromMinutes(28) },

                new Order(1007, customers[6], DateTime.Today.AddHours(16), 32.90m, OrderStatus.OutForDelivery,
                    new List<Topping>{ new Topping("Käse"), new Topping("Zwiebeln") })
                    { Driver = drivers[1], DeliveryTime = TimeSpan.FromMinutes(30) },

                new Order(1008, customers[7], DateTime.Today.AddHours(17), 18.50m, OrderStatus.Delivered,
                    new List<Topping>{ new Topping("Schinken"), new Topping("Ananas") })
                    { Driver = drivers[0], DeliveryTime = TimeSpan.FromMinutes(25) },

                new Order(1009, customers[8], DateTime.Today.AddHours(18), 45.30m, OrderStatus.OutForDelivery,
                    new List<Topping>{ new Topping("Käse"), new Topping("Thunfisch") })
                    { Driver = drivers[2], DeliveryTime = TimeSpan.FromMinutes(40) },

                // Older than 30 days
                new Order(1010, customers[4], DateTime.Today.AddDays(-35).AddHours(19), 27.40m, OrderStatus.Delivered,
                    new List<Topping>{ new Topping("Salami"), new Topping("Käse") })
                    { Driver = drivers[1], DeliveryTime = TimeSpan.FromMinutes(26) }
            };

            return (orders, drivers);
        }
    }
}