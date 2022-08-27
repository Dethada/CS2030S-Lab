/**
 * Class representing a Service End Event.
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceEnd extends ShopEvent {
  /**
   * The id of the counter associated with this event. This field only matters if the event type if
   * SERVICE_BEGIN or SERVICE_END.
   */
  private int counterId;

  /**
   * Constructor for ShopEventServiceEnd.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param counterId The id of the counter associated with this event.
   * @param counters The state of all counters.
   */
  public ShopEventServiceEnd(double time, int customerId, int counterId, Counters counters) {
    super(time, customerId, counters);
    this.counterId = counterId;
  }

  /**
   * Returns the string representation of this service end event.
   *
   * @return A string representing this service end event.
   */
  @Override
  public String toString() {
    return super.toString()
        + String.format(
            ": Customer %d service done (by Counter %d)", this.getCustomerID(), this.counterId);
  }

  /**
   * Simulate the Service End Event.
   *
   * @return Event Array containing the departure event.
   */
  @Override
  public Event[] simulate() {
    // The current event is a service-end event.
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    // this.available[this.counterId] = true;
    super.getCounters().freeCounter(this.counterId);
    return new Event[] {
      new ShopEventDeparture(this.getTime(), this.getCustomerID()),
    };
  }
}
