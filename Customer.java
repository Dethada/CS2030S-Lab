/**
 * Class representing a Customer.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Customer {
  /** The service time of the customer associated this event. */
  private final int id;

  /** The service time of the customer associated this event. */
  private final double serviceTime;

  /**
   * Constructor for Customer.
   *
   * @param id The customer's ID.
   */
  public Customer(int id, double serviceTime) {
    this.id = id;
    this.serviceTime = serviceTime;
  }

  /**
   * Returns the string representation of this service arrival event.
   *
   * @return A string representing this service arrival event.
   */
  @Override
  public String toString() {
    return "C" + this.id;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }
}
