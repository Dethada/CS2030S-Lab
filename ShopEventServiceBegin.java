/**
 * Class representing a Service Begin Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceBegin extends Event {
  /**
   * The counter associated with this event. This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private final Counter counter;

  private final Customer customer;
  private final Shop shop;

  /**
   * Constructor for ShopEventServiceBegin.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   * @param queue The queue for the customers.
   */
  public ShopEventServiceBegin(double time, Customer customer, Shop shop, Counter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = counter;
    // System.out.println("Here");
  }

  /**
   * Constructor for ShopEventServiceBegin.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   * @param queue The queue for the customers.
   */
  public ShopEventServiceBegin(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = this.shop.getAvailableCounter();
    // System.out.println(this.counter);
    // System.out.println("Here2");
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
    boolean status = this.counter.serveCustomer(this.customer);
    // System.out.println(status);
    // System.out.println(this.counter.isAvailable());
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {new ShopEventServiceEnd(endTime, this.customer, this.counter, this.shop)};
  }
}
