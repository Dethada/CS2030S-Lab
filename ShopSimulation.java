import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Wei Tsang
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopSimulation extends Simulation {
  /** The list of customer arrival events to populate the simulation with. */
  private final Event[] initEvents;

  /**
   * Constructor for a shop simulation.
   *
   * @param sc A scanner to read the parameters from. The first integer scanned is the number of
   *     customers; followed by the number of service counters. Next is a sequence of (arrival time,
   *     service time) pair, each pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    this.initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    int queueLength = sc.nextInt();
    Shop shop = new Shop(numOfCounters);
    Queue queue = new Queue(queueLength);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer customer = new Customer(id, serviceTime);
      initEvents[id] = new ShopEventArrival(arrivalTime, customer, shop, queue);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
