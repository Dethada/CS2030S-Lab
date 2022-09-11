/**
 * Class representing a Departure Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventDeparture extends Event {
  /** The customer associated with this event. */
  private final Customer customer;

  /** The shop associated with this event. */
  private final Shop shop;

  /** The counter associated with this event. */
  private final Counter counter;

  /**
   * Constructor for ShopEventDeparture.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   * @param counter The counter the cutomer departed from.
   */
  public ShopEventDeparture(double time, Customer customer, Shop shop, Counter counter) {
    super(time);
    this.customer = customer;
    this.shop = shop;
    this.counter = counter;
  }

  /**
   * Returns the string representation of this departure event.
   *
   * @return A string representing this departure event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s departed", this.customer.toString());
  }

  /**
   * Simulate the departure event.
   *
   * @return Empty Events array.
   */
  @Override
  public Event[] simulate() {
    if (this.counter != null) {
      Customer nextCustomer = this.counter.deQueue();
      if (nextCustomer != null) {
        // poll for customer in entrance queue transfer them to the counter queue
        Customer entranceCustomer = this.shop.deQueueEntrance();
        if (entranceCustomer != null) {
          Event[] events = new Event[2];
          events[0] = new ShopEventCounterJoinQueue(this.getTime(), entranceCustomer, this.counter);
          events[1] =
              new ShopEventServiceBegin(this.getTime(), nextCustomer, this.shop, this.counter);
          return events;
        }
        return new Event[] {
          new ShopEventServiceBegin(this.getTime(), nextCustomer, this.shop, this.counter)
        };
      }
      // poll entrance queue
      nextCustomer = this.shop.deQueueEntrance();
      if (nextCustomer != null) {
        return new Event[] {
          new ShopEventServiceBegin(this.getTime(), nextCustomer, this.shop, this.counter)
        };
      }
    }
    return new Event[] {};
  }
}
