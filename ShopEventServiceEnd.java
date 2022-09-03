import java.util.*;

/**
 * Class representing a Service End Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceEnd extends ShopEvent {
  /**
   * The id of the counter associated with this event. This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private final Counter counter;

  /**
   * Constructor for ShopEventServiceEnd.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param counterId The id of the counter associated with this event.
   * @param counters The state of all counters.
   */
  public ShopEventServiceEnd(
      double time, Customer customer, Counter counter, Counter[] counters, Queue queue) {
    super(time, customer, counters, queue);
    this.counter = counter;
  }

  /**
   * Returns the string representation of this service end event.
   *
   * @return A string representing this service end event.
   */
  @Override
  public String toString() {
    return super.toString()
        + String.format(
            ": %s service done (by %s)", this.getCustomer().toString(), this.counter.toString());
  }

  /**
   * Simulate the Service End Event.
   *
   * @return Event Array containing the departure event.
   */
  @Override
  public Event[] simulate() {
    // The current event is a service-end event.
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    // this.available[this.counterId] = true;
    this.freeCounter(this.counter);
    // System.out.println(Arrays.toString(this.getCounters()));
    return new Event[] {
      new ShopEventDeparture(
          this.getTime(), this.getCustomer(), this.getCounters(), this.getQueue()),
    };
  }
}
