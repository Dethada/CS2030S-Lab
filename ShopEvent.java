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
  private final Customer customer;

  /** The availability of counters in the shop. */
  private Shop shop;

  private Queue queue;

  public ShopEvent(double time, Customer customer, Shop shop, Queue queue) {
    this(time, customer, shop);
    this.queue = queue;
  }

  public ShopEvent(double time, Customer customer, Queue queue) {
    this(time, customer);
    this.queue = queue;
  }

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   * @param counters The array of Counters.
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

  public Shop getShop() {
    return this.shop;
  }

  public Queue getQueue() {
    return this.queue;
  }

  public Customer getCustomer() {
    return this.customer;
  }
}
