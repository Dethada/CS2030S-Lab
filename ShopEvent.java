/**
 * This class encapsulates an event in the shop simulation.
 *
 * @author Wei Tsang
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
abstract class ShopEvent extends Event {
  /** The customer associated with this event. */
  private final Customer customer;

  /** The availability of counters in the shop. */
  private Shop shop;

  /** The queue for the cutomers. */
  private Queue queue;

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   * @param queue The queue for the customers.
   */
  public ShopEvent(double time, Customer customer, Shop shop, Queue queue) {
    this(time, customer, shop);
    this.queue = queue;
  }

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param queue The queue for the customers.
   */
  public ShopEvent(double time, Customer customer, Queue queue) {
    this(time, customer);
    this.queue = queue;
  }

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param shop The shop containing all the counters.
   */
  public ShopEvent(double time, Customer customer, Shop shop) {
    this(time, customer);
    this.shop = shop;
  }

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   */
  public ShopEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  /**
   * Return the Shop of this event.
   *
   * @return The Shop of this event.
   */
  public Shop getShop() {
    return this.shop;
  }

  /**
   * Return the queue of this event.
   *
   * @return The Queue of this event.
   */
  public Queue getQueue() {
    return this.queue;
  }

  /**
   * Return the customer of this event.
   *
   * @return The Customer of this event.
   */
  public Customer getCustomer() {
    return this.customer;
  }
}
