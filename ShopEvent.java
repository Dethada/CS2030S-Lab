/**
 * This class encapsulates an event in the shop simulation. Your task is to replace this class with
 * new classes, following proper OOP principles.
 *
 * @author Wei Tsang
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
abstract class ShopEvent extends Event {
  /**
   * The id of a customer associated with this event. First customer has id 0. Next is 1, 2, etc.
   */
  private final int customerId;

  /** The availability of counters in the shop. */
  private Counters counters;

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param counters The state of all counters.
   */
  public ShopEvent(double time, int customerId, Counters counters) {
    this(time, customerId);
    this.counters = counters;
  }

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   */
  public ShopEvent(double time, int customerId) {
    super(time);
    this.customerId = customerId;
  }

  public int getCustomerID() {
    return this.customerId;
  }

  public Counters getCounters() {
    return this.counters;
  }
}
