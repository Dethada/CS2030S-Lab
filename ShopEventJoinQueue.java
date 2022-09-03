/**
 * Class representing a Service Arrival Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventJoinQueue extends ShopEvent {
  /**
   * Constructor for ShopEventArrival.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param serviceTime The time this customer takes for service.
   * @param counters The state of all counters.
   */
  public ShopEventJoinQueue(double time, Customer customer, Queue queue) {
    super(time, customer, queue);
  }

  /**
   * Returns the string representation of this service arrival event.
   *
   * @return A string representing this service arrival event.
   */
  @Override
  public String toString() {
    return super.toString()
        + String.format(
            ": %s joined queue %s", this.getCustomer().toString(), this.getQueue().toString());
  }

  @Override
  public Event[] simulate() {
    this.getQueue().enq(this.getCustomer());
    return new Event[] {};
  }
}
