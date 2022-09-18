/**
 * Class representing a Service Arrival Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventArrival extends Event {
  /** The customer associated with this event. */
  private final Customer customer;

  /** The shop associated with this event. */
  private final Shop shop;

  /**
   * Constructor for ShopEventArrival.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   */
  public ShopEventArrival(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
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
            ": %s arrived %s", this.customer.toString(), this.shop.entranceQueueString());
  }

  /**
   * Simulate the Service Arrival Event.
   *
   * @return Event Array containing the service begin event or departure event.
   */
  @Override
  public Event[] simulate() {
    if (!this.shop.hasAvailableCounter()) {
      // If no such counter can be found, the customer should try to
      // join the shortest counter queue
      if (this.shop.canJoinCounterQueue()) {
        return new Event[] {
          new ShopEventCounterJoinQueue(this.getTime(), this.customer, this.shop.getMinCounter())
        };
      } else if (!this.shop.entranceQueueFull()) {
        // else join the entrance queue
        return new Event[] {new ShopEventJoinQueue(this.getTime(), this.customer, this.shop)};
      }
      // else depart customer because the shop is full
      return new Event[] {new ShopEventDeparture(this.getTime(), this.customer, this.shop, null)};
    }
    // Else, the customer should go the the first
    // available counter and get served.
    return new Event[] {new ShopEventServiceBegin(this.getTime(), this.customer, this.shop)};
  }
}
