/**
 * Class representing a Join Queue Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventCounterJoinQueue extends Event {

  private final Customer customer;

  private final Counter counter;

  /**
   * Constructor for ShopEventCounterJoinQueue.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param counter The counter related to this event.
   */
  public ShopEventCounterJoinQueue(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
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
            ": %s joined counter queue (at %s)", this.customer.toString(), this.counter.toString());
  }

  /**
   * Simulate the a join queue event.
   *
   * @return Empty Events array.
   */
  @Override
  public Event[] simulate() {
    this.counter.joinQueue(this.customer);
    return new Event[] {};
  }
}
