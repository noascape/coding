package de.project;

import de.project.adapter.PassengerDatabase;
import de.project.bridge.*;
import de.project.decorator.*;
import de.project.facade.SeatManagementSystem;
import de.project.factory.Seat;
import de.project.flyweight.*;
import de.project.proxy.*;
import de.project.chainofresponsibility.*;
import de.project.command.*;
import de.project.command.receiver.SeatReceiver;
import de.project.iterator.*;
import de.project.mediator.*;
import de.project.memento.*;
import de.project.observer.*;
import de.project.state.*;
import de.project.strategy.*;
import de.project.template.*;
import de.project.visitor.*;
import de.project.guard.*;
import de.project.filter.*;
import de.project.visitor.SeatGroup;


import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Starting Seat Management System...");
        try {
            demonstrateFactory();
            demonstrateBuilder();
            demonstrateAdapter();
            demonstrateBridge();
            demonstrateComposite();
            demonstrateDecorator();
            demonstrateFlyweight();
            demonstrateProxy();
            demonstrateChainOfResponsibility();
            demonstrateCommand();
            demonstrateIterator();
            demonstrateMediator();
            demonstrateMemento();
            demonstrateObserver();
            demonstrateState();
            demonstrateStrategy();
            demonstrateTemplate();
            demonstrateVisitor();
            demonstrateGuard();
            demonstrateFilter();
            demonstrateUnifiedCommand();
            demonstrateImprovedChainOfResponsibility();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred during execution", e);
        }
    }

    private static void demonstrateFactory() {
        SeatManagementSystem managementSystem = new SeatManagementSystem();
        Seat economySeat = managementSystem.createSeat("ECONOMY");
        Seat businessSeat = managementSystem.createSeat("BUSINESS");
        LOGGER.info(() -> "Created Seats:\n" + economySeat + "\n" + businessSeat);
    }

    private static void demonstrateBuilder() {
        SeatManagementSystem managementSystem = new SeatManagementSystem();
        var seatConfig = managementSystem.configureSeat(true, true, false);
        LOGGER.info(() -> "Seat Configuration: " + seatConfig);
    }

    private static void demonstrateAdapter() {
        PassengerDatabase passengerDatabase = new PassengerDatabase(List.of("Alice", "Bob", "Charlie"));
        SeatManagementSystem managementSystem = new SeatManagementSystem();
        managementSystem.addPassengersFromDatabase(passengerDatabase);
        LOGGER.info("Passengers added from database.");
    }

    private static void demonstrateBridge() {
        SeatManagerBridge airbusManager = new FlightSeatManager(new AirbusSeatImplementation());
        airbusManager.assignSeat("1A", "Alice");
        LOGGER.info(() -> "Seat Details: " + airbusManager.getSeatDetails("1A"));
    }

    private static void demonstrateComposite() {
        de.project.composite.SeatComponent row1 = new de.project.composite.SeatGroup("Row 1");
        row1.add(new de.project.composite.IndividualSeat("1A"));
        row1.add(new de.project.composite.IndividualSeat("1B"));
        LOGGER.info("Row 1 Details:");
        row1.displayDetails();
    }

    private static void demonstrateDecorator() {
        de.project.decorator.Seat decoratedSeat = new ExtraLegroomDecorator(new BasicSeat()).getSeat();
        LOGGER.info(() -> decoratedSeat.getDescription() + " | Cost: " + decoratedSeat.getCost() + " EUR");
    }

    private static void demonstrateFlyweight() {
        SeatFlyweightFactory flyweightFactory = new SeatFlyweightFactory();
        LOGGER.info(() -> "Flyweight cache size: " + flyweightFactory.getCacheSize());
    }

    private static void demonstrateProxy() {
        SeatDataAccessProxy proxy = new SeatDataAccessProxy(true);
        proxy.updateSeatData("1A", "Alice");
        LOGGER.info(() -> "Proxy Seat Data: " + proxy.getSeatData("1A"));
    }

    private static void demonstrateChainOfResponsibility() {
        SeatReservationHandler chain = new EconomyHandler();
        chain.setNextHandler(new BusinessHandler());
        chain.handleReservation("Economy");
    }

    private static void demonstrateCommand() {
        SeatReceiver receiver = new SeatReceiver();
        Invoker invoker = new Invoker();
        invoker.executeCommand(new ReserveSeatCommand(receiver, "1A", "Alice"));
        receiver.printReservations();
    }

    private static void demonstrateIterator() {
        SeatCollection collection = new SeatCollection();
        collection.addSeat("1A");
        SeatIterator iterator = collection.createIterator();
        while (iterator.hasNext()) {
            LOGGER.info(() -> "Seat: " + iterator.next());
        }
    }

    private static void demonstrateMediator() {
        SeatMediator mediator = new SeatMediator();
        Passenger passenger = new Passenger(mediator, "Alice");
        passenger.requestSeat("1A");
    }

    private static void demonstrateMemento() {
        SeatReservation reservation = new SeatReservation("1A", "Alice");
        ReservationCaretaker caretaker = new ReservationCaretaker();
        caretaker.saveMemento(reservation.save());
        reservation.restore(caretaker.restoreMemento());
    }

    private static void demonstrateObserver() {
        SeatManagement observerSeatManagement = new SeatManagement();
        observerSeatManagement.addObserver(new CabinCrew("Alice"));
        observerSeatManagement.changeSeat("1A", "Charlie");
    }

    private static void demonstrateState() {
        SeatContext context = new SeatContext();
        context.changeState(new ReservedState());
        LOGGER.info(() -> "Current State: " + context.getState().getStateName());
    }

    private static void demonstrateStrategy() {
        SeatAssigner assigner = new SeatAssigner(new ArrayList<>(List.of("1A", "1B")));
        assigner.setStrategy(new FirstAvailableStrategy());
        assigner.assignSeat();
    }

    private static void demonstrateTemplate() {
        SeatAssignmentTemplate vipTemplate = new VipSeatAssignment();
        vipTemplate.assignSeat(new ArrayList<>(List.of("1A", "1B")));
    }

    private static void demonstrateVisitor() {
        SeatVisitor visitor = new SeatReportVisitor();
        SeatGroup group = new SeatGroup("Group");
        group.addSeat(new de.project.visitor.IndividualSeat("1A", "Economy"));
        group.accept(visitor);
    }

    private static void demonstrateGuard() {
        SeatService service = new SeatService(new ExitRowGuard());
        service.reserveSeat(new SeatRequest("1A", true, 25));
    }

    private static void demonstrateFilter() {
        List<de.project.filter.Seat> seats = List.of(
                new de.project.filter.Seat("1A", true, true, false),
                new de.project.filter.Seat("1B", true, false, true)
        );
        new AvailableSeatFilter().filter(seats).forEach(seat -> LOGGER.info(() -> "Available Seat: " + seat.getSeatId()));
    }

    private static void demonstrateUnifiedCommand() {
        LOGGER.info("--- Unified Command Pattern ---");
        SeatReceiver receiver = new SeatReceiver();
        Invoker invoker = new Invoker();

        Command reserveCommand = new SeatCommand(receiver, SeatAction.RESERVE, "1A", "Alice");
        Command cancelCommand = new SeatCommand(receiver, SeatAction.CANCEL, "1A", null);

        invoker.executeCommand(reserveCommand);
        invoker.executeCommand(cancelCommand);

        invoker.showHistory();
        invoker.undoLastCommand();
    }

    private static void demonstrateImprovedChainOfResponsibility() {
        LOGGER.info("--- Improved Chain of Responsibility ---");

        SeatReservationHandler economyHandler = new GenericSeatReservationHandler(SeatClass.ECONOMY);
        SeatReservationHandler businessHandler = new GenericSeatReservationHandler(SeatClass.BUSINESS);

        economyHandler.setNextHandler(businessHandler);

        economyHandler.handleReservation("Economy");
        economyHandler.handleReservation("Business");
    }
}
