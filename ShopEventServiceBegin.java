/**
 * Class representing a Service Begin Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceBegin extends ShopEvent {
  /**
   * The service time of the customer associated this event. This field matters only if the event
   * type is ARRIVAL or SERVICE_BEGIN.
   */
  private double serviceTime;

  /**
   * The id of the counter associated with this event. This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private int counterId;

  /**
   * Constructor for ShopEventServiceBegin.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param serviceTime The time this customer takes for service.
   * @param counterId The id of the counter associated with this event.
   * @param counters The state of all counters.
   */
  public ShopEventServiceBegin(
      double time, int customerId, double serviceTime, int counterId, Counters counters) {
    super(time, customerId, counters);
    this.serviceTime = serviceTime;
    this.counterId = counterId;
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
            ": Customer %d service begin (by Counter %d)", this.getCustomerID(), this.counterId);
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
    // this.available[this.counterId] = false;
    double endTime = this.getTime() + this.serviceTime;
    return new Event[] {
      new ShopEventServiceEnd(endTime, this.getCustomerID(), this.counterId, super.getCounters())
    };
  }
}
