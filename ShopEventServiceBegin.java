import java.util.*;

/**
 * Class representing a Service Begin Event.
 *
 * @author David
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceBegin extends ShopEvent {
  /**
   * The id of the counter associated with this event. This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private final Counter counter;

  /**
   * Constructor for ShopEventServiceBegin.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param serviceTime The time this customer takes for service.
   * @param counterId The id of the counter associated with this event.
   * @param counters The state of all counters.
   */
  public ShopEventServiceBegin(double time, Customer customer, Counter[] counters, Queue queue) {
    super(time, customer, counters, queue);
    this.counter = this.useCounter().get();
  }

  /**
   * Returns the string representation of this service begin event.
   *
   * @return A string representing this service begin event.
   */
  @Override
  public String toString() {
    return super.toString()
        + String.format(
            ": %s service begin (by %s)", this.getCustomer().toString(), this.counter.toString());
  }

  /**
   * Simulate the Service Begin Event.
   *
   * @return Event Array containing the service end event.
   */
  @Override
  public Event[] simulate() {
    // The current event is a service-begin event.
    // Mark the counter is unavailable, then schedule
    // a service-end event at the current time + service time.
    // this.available[this.counterId] = false;
    // System.out.println(Arrays.toString(this.getCounters()));
    double endTime =
        this.getTime() + this.getCustomer().getServiceTime(); // extract this out to the
    // ShopEvent class?
    return new Event[] {
      new ShopEventServiceEnd(
          endTime, this.getCustomer(), this.counter, super.getCounters(), this.getQueue())
    };
  }
}
