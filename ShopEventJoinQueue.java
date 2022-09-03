/**
 * Class representing a Join Queue Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventJoinQueue extends ShopEvent {
  /**
   * Constructor for ShopEventJoinQueue.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param queue The queue for the customers.
   */
  public ShopEventJoinQueue(double time, Customer customer, Queue queue) {
    super(time, customer, queue);
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
            ": %s joined queue %s", this.getCustomer().toString(), this.getQueue().toString());
  }

  /**
   * Simulate the a join queue event.
   *
   * @return Empty Events array.
   */
  @Override
  public Event[] simulate() {
    this.getQueue().enq(this.getCustomer());
    return new Event[] {};
  }
}
