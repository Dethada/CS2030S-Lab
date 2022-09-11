/**
 * Class representing a Service End Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceEnd extends Event {
  /**
   * The counter associated with this event. This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private final Counter counter;

  private final Customer customer;
  private final Shop shop;

  /**
   * Constructor for ShopEventServiceEnd.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param counter The counter serving the customer.
   * @param shop The shop containing all the counters.
   */
  public ShopEventServiceEnd(double time, Customer customer, Counter counter, Shop shop) {
    super(time);
    this.counter = counter;
    this.customer = customer;
    this.shop = shop;
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
            ": %s service done (by %s)", this.customer.toString(), this.counter.toString());
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
    this.counter.finishService();
    return new Event[] {
      new ShopEventDeparture(this.getTime(), this.customer, this.shop, this.counter),
    };
  }
}
