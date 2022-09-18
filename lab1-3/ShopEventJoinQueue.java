/**
 * Class representing a Join Queue Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventJoinQueue extends Event {
  /** The customer associated with this event. */
  private final Customer customer;

  /** The shop associated with this event. */
  private final Shop shop;

  /**
   * Constructor for ShopEventJoinQueue.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   */
  public ShopEventJoinQueue(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
    this.shop = shop;
  }

  /**
   * Returns the string representation of this Join Queue event.
   *
   * @return A string representing this service arrival event.
   */
  @Override
  public String toString() {
    return super.toString()
        + String.format(
            ": %s joined shop queue %s", this.customer.toString(), this.shop.entranceQueueString());
  }

  /**
   * Simulate the a join queue event.
   *
   * @return Empty Events array.
   */
  @Override
  public Event[] simulate() {
    this.shop.enQueueEntrance(this.customer);
    return new Event[] {};
  }
}
