/**
 * Class representing a Join Queue Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventJoinQueue extends Event {

  private final Customer customer;

  private final Queue<Customer> queue;

  /**
   * Constructor for ShopEventJoinQueue.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param queue The queue for the customers.
   */
  public ShopEventJoinQueue(double time, Customer customer, Queue<Customer> queue) {
    super(time);
    this.customer = customer;
    this.queue = queue;
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
            ": %s joined shop queue %s", this.customer.toString(), this.queue.toString());
  }

  /**
   * Simulate the a join queue event.
   *
   * @return Empty Events array.
   */
  @Override
  public Event[] simulate() {
    this.queue.enq(this.customer);
    return new Event[] {};
  }
}
