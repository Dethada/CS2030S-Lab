/**
 * TODO
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventDeparture extends ShopEvent {
    public ShopEventDeparture(double time, int customerId) {
        super(time, customerId);
    }

  /**
   * Returns the string representation of the event,
   * depending on the type of event.
   *
   * @return A string representing the event.
   */
    @Override
    public String toString() {
      return super.toString() + String.format(": Customer %d departed", this.getCustomerID());
    }

    /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
    @Override
    public Event[] simulate() {
        return new Event[] {};
    }
}
