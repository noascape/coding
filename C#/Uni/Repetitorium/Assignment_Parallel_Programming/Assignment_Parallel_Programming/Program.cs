using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;

class Program
{
    private const int NumSensors = 4;
    private const int ReadingsPerSensor = 10;
    private const double SensorIntervalSeconds = 0.3;

    static void SensorProcess(
        int sensorId,
        List<double> sharedReadings,
        object listLock,
        int readingsCount,
        double intervalSeconds)
    {
        Random random = new Random(Guid.NewGuid().GetHashCode());

        for (int i = 0; i < readingsCount; i++)
        {
            // 1. Generate
            double value = Math.Round(random.NextDouble() * 50.0 - 10.0, 1);

            // 2-4. Lock, write, release
            lock (listLock)
            {
                sharedReadings.Add(value);
            }

            // 5. Log
            Console.WriteLine($"[Sensor {sensorId}] {value:F1} °C");

            // 6. Pause
            Thread.Sleep(TimeSpan.FromSeconds(intervalSeconds));
        }
    }

    static void AggregatorProcess(
        List<double> sharedReadings,
        object listLock,
        ManualResetEvent stopEvent)
    {
        while (!stopEvent.WaitOne(0))
        {
            List<double> snapshot;

            // 2-3. Lock, read copy, release
            lock (listLock)
            {
                snapshot = new List<double>(sharedReadings);
            }

            // 4-5. Compute and print
            if (snapshot.Count > 0)
            {
                double average = snapshot.Average();
                Console.WriteLine($"[Aggregator] readings: {snapshot.Count} | average: {average:F1} °C");
            }
            else
            {
                Console.WriteLine("[Aggregator] readings: 0 | average: n/a");
            }

            // 6. Pause
            Thread.Sleep(1000);
        }
    }

    static void Main()
    {
        // 1-2. Shared structures, lock, stop event
        List<double> sharedReadings = new List<double>();
        object listLock = new object();
        ManualResetEvent stopEvent = new ManualResetEvent(false);

        // 3. Start aggregator first
        Thread aggregatorThread = new Thread(() =>
            AggregatorProcess(sharedReadings, listLock, stopEvent));

        aggregatorThread.Start();

        // 4. Start all sensor processes
        List<Thread> sensorThreads = new List<Thread>();

        for (int i = 0; i < NumSensors; i++)
        {
            int sensorId = i;
            Thread sensorThread = new Thread(() =>
                SensorProcess(sensorId, sharedReadings, listLock, ReadingsPerSensor, SensorIntervalSeconds));

            sensorThreads.Add(sensorThread);
            sensorThread.Start();
        }

        // 5. Wait for sensors
        foreach (Thread sensorThread in sensorThreads)
        {
            sensorThread.Join();
        }

        // 6. Signal aggregator to stop
        stopEvent.Set();

        // 7. Wait for aggregator
        aggregatorThread.Join();

        // 8. Final summary
        lock (listLock)
        {
            int total = sharedReadings.Count;
            double finalAverage = total > 0 ? sharedReadings.Average() : 0.0;
            Console.WriteLine($"Done. Total readings: {total} | Final average: {finalAverage:F1} °C");
        }
    }
}