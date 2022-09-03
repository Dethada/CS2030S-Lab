/**
 * Class representing a Departure Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventDeparture extends ShopEvent {
  /**
   * Constructor for ShopEventDeparture.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   */
  public ShopEventDeparture(double time, Customer customer, Counter[] counters, Queue queue) {
    super(time, customer, counters, queue);
  }

  /**
   * Returns the string representation of this departure event.
   *
   * @return A string representing this departure event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": %s departed", this.getCustomer().toString());
  }

  /**
   * Simulate the departure event.
   *
   * @return Empty Events array.
   */
  @Override
  public Event[] simulate() {
    boolean hasCounter = this.hasAvailableCounter();
    if (hasCounter) {
      Customer next_customer = (Customer) this.getQueue().deq();
      if (next_customer != null) {
        // System.out.printf("%s got deqed\n", next_customer.toString());
        return new Event[] {
          new ShopEventServiceBegin(
              this.getTime(), next_customer, this.getCounters(), this.getQueue())
        };
      }
    }
    return new Event[] {};
  }
}
