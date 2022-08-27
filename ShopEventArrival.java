/**
 * TODO
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventArrival extends ShopEvent {
  /** The service time of the customer associated this event. */
  private double serviceTime;

  public ShopEventArrival(double time, int customerId, double serviceTime, Counters counter) {
    super(time, customerId, counter);
    this.serviceTime = serviceTime;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": Customer %d arrives", this.getCustomerID());
  }

  @Override
  public Event[] simulate() {
    int counter = super.getCounters().useCounter();
    if (counter == -1) {
      // If no such counter can be found, the customer
      // should depart.
      return new Event[] {new ShopEventDeparture(this.getTime(), this.getCustomerID())};
    } else {
      // Else, the customer should go the the first
      // available counter and get served.
      return new Event[] {
        new ShopEventServiceBegin(
            this.getTime(), this.getCustomerID(), this.serviceTime, counter, super.getCounters())
      };
    }
  }
}
