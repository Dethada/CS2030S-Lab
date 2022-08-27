import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Wei Tsang
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopSimulation extends Simulation {
  /** The availability of counters in the shop. */
  private Counters counters;

  /** The list of customer arrival events to populate the simulation with. */
  private Event[] initEvents;

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
    this.counters = new Counters(numOfCounters);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      initEvents[id] = new ShopEventArrival(arrivalTime, id, serviceTime, this.counters);
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
