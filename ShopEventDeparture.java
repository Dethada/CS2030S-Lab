/**
 * Class representing a Departure Event.
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventDeparture extends ShopEvent {

  /**
   * Constructor for ShopEventDeparture.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   */
  public ShopEventDeparture(double time, int customerId) {
    super(time, customerId);
  }

  /**
   * Returns the string representation of this departure event.
   *
   * @return A string representing this departure event.
   */
  @Override
  public String toString() {
    return super.toString() + String.format(": Customer %d departed", this.getCustomerID());
  }

  /**
   * Simulate the departure event.
   *
   * @return Empty Events array.
   */
  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
