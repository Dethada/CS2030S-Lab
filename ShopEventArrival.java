/**
 * Class representing a Service Arrival Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventArrival extends ShopEvent {
  /**
   * Constructor for ShopEventArrival.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param serviceTime The time this customer takes for service.
   * @param counters The state of all counters.
   */
  public ShopEventArrival(double time, Customer customer, Shop shop, Queue queue) {
    super(time, customer, shop, queue);
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
            ": %s arrived %s", this.getCustomer().toString(), this.getQueue().toString());
  }

  /**
   * Simulate the Service Arrival Event.
   *
   * @return Event Array containing the service begin event or departure event.
   */
  @Override
  public Event[] simulate() {
    // System.out.println(Arrays.toString(this.getCounters()));
    boolean hasCounter = this.getShop().hasAvailableCounter();
    if (!hasCounter) {
      // If no such counter can be found, the customer should try to join the queue
      Queue queue = this.getQueue();
      if (!queue.isFull()) {
        return new Event[] {new ShopEventJoinQueue(this.getTime(), this.getCustomer(), queue)};
      } else {
        return new Event[] {new ShopEventDirectDeparture(this.getTime(), this.getCustomer())};
      }
    } else {
      // Else, the customer should go the the first
      // available counter and get served.
      return new Event[] {
        new ShopEventServiceBegin(
            this.getTime(), this.getCustomer(), this.getShop(), this.getQueue())
      };
    }
  }
}
