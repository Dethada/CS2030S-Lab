import java.util.Optional;

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
  private Counter[] counters;

  private Queue queue;

  public ShopEvent(double time, Customer customer, Counter[] counters, Queue queue) {
    this(time, customer, counters);
    this.queue = queue;
  }

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   * @param counters The state of all counters.
   */
  public ShopEvent(double time, Customer customer, Counter[] counters) {
    this(time, customer);
    this.counters = counters;
  }

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customerId The customer associated with this event.
   */
  public ShopEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  public Counter getCounter(int i) {
    return this.counters[i];
  }

  public Counter[] getCounters() {
    return this.counters;
  }

  public Queue getQueue() {
    return this.queue;
  }

  public Customer getCustomer() {
    return this.customer;
  }

  /**
   * Get an available counter and set it to be unavailable.
   *
   * @return The Counter ID of an available counter or -1 if there is no available counter.
   */
  public Optional<Counter> useCounter() {
    Optional<Counter> c = this.getAvailableCounter();
    if (c.isPresent()) {
      c.get().setUnavailable();
    }
    return c;
  }

  public void useCounter(Counter counter) {
    this.counters[counter.getId()].setUnavailable();
  }

  /**
   * Set an counter to be available.
   *
   * @param i The index (counterID) of the counter to free up.
   */
  public void freeCounter(Counter counter) {
    this.counters[counter.getId()].setAvailable();
  }

  /**
   * Get an available counter.
   *
   * @return The Counter ID of an available counter or -1 if there is no available counter.
   */
  private Optional<Counter> getAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].isAvailable()) {
        return Optional.of(this.counters[i]);
      }
    }
    return Optional.ofNullable(null);
  }

  public boolean hasAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].isAvailable()) {
        return true;
      }
    }
    return false;
  }
}
