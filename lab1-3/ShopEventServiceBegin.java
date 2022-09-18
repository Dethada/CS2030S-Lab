/**
 * Class representing a Service Begin Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceBegin extends Event {
  /** The counter associated with this event. */
  private final Counter counter;

  /** The customer associated with this event. */
  private final Customer customer;

  /** The shop associated with this event. */
  private final Shop shop;

  /**
   * Constructor for ShopEventServiceBegin.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   * @param counter The queue to use for the customer.
   */
  public ShopEventServiceBegin(double time, Customer customer, Shop shop, Counter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = counter;
  }

  /**
   * Constructor for ShopEventServiceBegin.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   */
  public ShopEventServiceBegin(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = this.shop.getAvailableCounter();
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
            ": %s service begin (by %s)", this.customer.toString(), this.counter.toString());
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
    this.counter.serveCustomer(this.customer);
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {new ShopEventServiceEnd(endTime, this.customer, this.counter, this.shop)};
  }
}
