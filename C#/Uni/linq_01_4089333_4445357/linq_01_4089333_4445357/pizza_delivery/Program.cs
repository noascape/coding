using System;
using System.Linq;
using pizza_delivery.Collections;
using pizza_delivery.Data;
using pizza_delivery.Interfaces;
using pizza_delivery.Models;
using pizza_delivery.Services;

namespace pizza_delivery
{
    /// Console entry: first executes the basic functionality (part 1) then the LINQ analyses (part 2 a-e)
    public class Program
    {
        public static void Main()
        {
            var (orders, drivers) = SampleData.CreateSampleData();

            // === part 1: Basis-Order-Management ===
            var manager = new OrderManager(orders);
            IOrderProcessor processor = new OrderProcessor(manager);

            Console.WriteLine("=== Pizza Delivery Order Management (Teil 1) ===\n");
            BasicsDemo(manager, processor);

            // === part 2: LINQ-Analytics ===
            IOrderAnalysisService analysis = new OrderAnalysisService(orders, drivers);

            Console.WriteLine("\n=== LINQ Tasks (Teil 2 a–e) ===\n");
            RunTaskA(analysis);
            RunTaskB(analysis);
            RunTaskC(analysis);
            RunTaskD(analysis);
            RunTaskE(analysis);

            Console.WriteLine("\n--- Program terminated ---");
        }

        private static void BasicsDemo(OrderManager manager, IOrderProcessor processor)
        {
            // Array of sizes
            Console.WriteLine("Verfügbare Größen: " + string.Join(", ", manager.AvailableSizes));

            // Search by using Dictionary
            var found = manager.FindById(1009);
            Console.WriteLine(found != null
                ? $"Schnell gefunden per ID: {found}"
                : "Bestellung 1009 nicht gefunden.");

            // Search by customer name
            var evaOrders = manager.FindByCustomerName("Eva").ToList();
            Console.WriteLine($"Bestellungen von 'Eva': {evaOrders.Count}");

            // Sort
            var byPriceDesc = manager.SortByPrice(descending: true).Take(3).ToList();
            Console.WriteLine("\nTop 3 nach Preis:");
            foreach (var o in byPriceDesc) Console.WriteLine(o);

            var byTimeAsc = manager.SortByOrderTime().Take(3).ToList();
            Console.WriteLine("\nFrüheste 3 Bestellungen:");
            foreach (var o in byTimeAsc) Console.WriteLine(o);

            // OrderCollection<T> – Generalization
            var today = new OrderCollection<Order>();
            today.AddRange(manager.ActiveOrders.Where(o => o.OrderDate.Date == DateTime.Today));
            Console.WriteLine($"\nOrderCollection: {today.Items.Count} Bestellungen am heutigen Tag.");

            // Methods Overloading: AddTopping
            if (found != null)
            {
                found.AddTopping("Peperoni");
                found.AddTopping("Mais", "Spinat");
                Console.WriteLine("\nNach AddTopping-Overloads:");
                Console.WriteLine(found);
            }

            // Queue/Stack Demo
            var example = manager.FindById(1007);
            if (example != null)
            {
                processor.ProcessOrder(example); // queue preparing
                Console.WriteLine($"\nIn Queue: {manager.IncomingQueue.Count} | Top: #{manager.IncomingQueue.Peek().OrderNumber}");

                manager.MarkDelivered(example.OrderNumber, TimeSpan.FromMinutes(33));
                Console.WriteLine($"DeliveredHistory Top: #{manager.DeliveredHistory.Peek().OrderNumber}");
            }
        }

        // ======== part 2: Analytics a–e ========

        private static void RunTaskA(IOrderAnalysisService service)
        {
            Console.WriteLine("=== (a) Teure Bestellungen >30€, OutForDelivery, heute ===");
            var results = service.GetExpensiveOutForDeliveryOrders(DateTime.Today).ToList();
            if (!results.Any())
            {
                Console.WriteLine("Keine passenden Bestellungen.\n");
                return;
            }

            foreach (var order in results)
            {
                Console.WriteLine(
                    $"#{order.OrderNumber} | {order.Customer.Name} | {order.Customer.FormattedAddress} | " +
                    $"{order.OrderDate:HH:mm} | {order.TotalPrice:F2}€ | {order.Status}");
            }
            Console.WriteLine();
        }

        private static void RunTaskB(IOrderAnalysisService service)
        {
            Console.WriteLine("=== (b) Gruppierung nach Postleitzahl (nur PLZ mit >5 Bestellungen) ===");
            var results = service.GetOrderStatsByPostalCode().ToList();
            if (!results.Any())
            {
                Console.WriteLine("Keine PLZ mit mehr als 5 Bestellungen.\n");
                return;
            }

            foreach (var stat in results) Console.WriteLine(stat);
            Console.WriteLine();
        }

        private static void RunTaskC(IOrderAnalysisService service)
        {
            Console.WriteLine("=== (c) Top 5 Toppings ===");
            var top = service.GetTopToppings(5).ToList();
            if (!top.Any())
            {
                Console.WriteLine("Keine Toppings gefunden.\n");
                return;
            }

            int rank = 1;
            foreach (var t in top)
                Console.WriteLine($"{rank++}. {t.ToppingName,-15} | {t.Count}x bestellt");
            Console.WriteLine();
        }

        private static void RunTaskD(IOrderAnalysisService service)
        {
            Console.WriteLine("=== (d) Aktive Kunden (30 Tage, ≥3 Orders, ∅ >25€) ===");
            var results = service.GetActiveCustomers().ToList();
            if (!results.Any())
            {
                Console.WriteLine("Keine aktiven Kunden.\n");
                return;
            }

            foreach (var c in results) Console.WriteLine(c);
            Console.WriteLine();
        }

        private static void RunTaskE(IOrderAnalysisService service)
        {
            Console.WriteLine("=== (e) Fahrer-Performance heute ===");
            var results = service.GetDriverPerformance(DateTime.Today).ToList();
            if (!results.Any())
            {
                Console.WriteLine("Keine Fahrerstatistik.\n");
                return;
            }

            foreach (var d in results) Console.WriteLine(d);
            Console.WriteLine();
        }
    }
}