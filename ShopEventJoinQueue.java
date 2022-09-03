/**
 * Class representing a Service Arrival Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventJoinQueue extends ShopEvent {

  private final String originalQueue;
  /**
   * Constructor for ShopEventArrival.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param serviceTime The time this customer takes for service.
   * @param counters The state of all counters.
   */
  public ShopEventJoinQueue(double time, Customer customer, String originalQueue) {
    super(time, customer);
    this.originalQueue = originalQueue;
  }

  /**
   * Returns the string representation of this service arrival event.
   *
   * @return A string representing this service arrival event.
   */
  @Override
  public String toString() {
    return super.toString()
        + String.format(": %s joined queue %s", this.getCustomer().toString(), this.originalQueue);
  }

  /**
   * Simulate the Service Arrival Event.
   *
   * @return Event Array containing the service begin event or departure event.
   */
  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
