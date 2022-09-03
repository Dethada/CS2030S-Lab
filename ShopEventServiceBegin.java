/**
 * Class representing a Service Begin Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceBegin extends ShopEvent {
  /**
   * The counter associated with this event. This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private final Counter counter;

  /**
   * Constructor for ShopEventServiceBegin.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   * @param queue The queue for the customers.
   */
  public ShopEventServiceBegin(double time, Customer customer, Shop shop, Queue queue) {
    super(time, customer, shop, queue);
    this.counter = this.getShop().useCounter().get();
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
    double endTime = this.getTime() + this.getCustomer().getServiceTime();
    return new Event[] {
      new ShopEventServiceEnd(
          endTime, this.getCustomer(), this.counter, this.getShop(), this.getQueue())
    };
  }
}
