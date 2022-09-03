/**
 * Class representing a Direct Departure Event.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventDirectDeparture extends ShopEvent {
  /**
   * Constructor for ShopEventDirectDeparture.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   */
  public ShopEventDirectDeparture(double time, Customer customer) {
    super(time, customer);
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
   * Simulate the a direct departure event.
   *
   * @return Empty Events array.
   */
  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
