/**
 * This class represents a shop, containing all the counters.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Shop {
  /** The counters in the shop. */
  private Array<Counter> counters;

  private final Queue<Customer> entranceQueue;

  /**
   * Constructor for Shop.
   *
   * @param numCounters The number of counters in the shop.
   * @param entranceQueueLength The queue length of the entrance queue in the shop.
   * @param counterQueueLength The queue length of the counters in the shop.
   */
  public Shop(int numCounters, int entranceQueueLength, int counterQueueLength) {
    Array<Counter> counters = new Array<Counter>(numCounters);
    for (int i = 0; i < numCounters; i++) {
      counters.set(i, new Counter(i, counterQueueLength));
    }
    this.counters = counters;
    this.entranceQueue = new Queue<Customer>(entranceQueueLength);
  }

  /**
   * Get a counter based on the index (Id) of the counter.
   *
   * @param i The index of the counter.
   * @return The counter at index i.
   */
  public Counter getCounter(int i) {
    return this.counters.get(i);
  }

  /**
   * Get an available counter and set it to be unavailable.
   *
   * @return An available Counter that was just set to unavailable or null.
   */
  public Counter useCounter(Customer customer) {
    Counter c = this.getAvailableCounter();
    if (c != null) {
      c.serveCustomer(customer);
    }
    return c;
  }

  /**
   * Get an available counter.
   *
   * @return An available Counter or null.
   */
  public Counter getAvailableCounter() {
    for (int i = 0; i < this.counters.length(); i++) {
      if (this.counters.get(i).isAvailable()) {
        return this.counters.get(i);
      }
    }
    return null;
  }

  /**
   * Check if there is a available counter in the shop.
   *
   * @return true if there is a available counter, false if there is no available counter.
   */
  public boolean hasAvailableCounter() {
    for (int i = 0; i < this.counters.length(); i++) {
      if (this.counters.get(i).isAvailable()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Join the shortest counter queue.
   *
   * @return true if success, false if all counter queues are full.
   */
  public boolean joinCounterQueue(Customer customer) {
    return this.counters.min().joinQueue(customer);
  }

  /**
   * Check if there is a counter queue available to join.
   *
   * @return true if available, false if not available.
   */
  public boolean canJoinCounterQueue() {
    for (int i = 0; i < this.counters.length(); i++) {
      if (!this.counters.get(i).isQueueFull()) {
        return true;
      }
    }
    return false;
  }

  public Queue<Customer> getEntranceQueue() {
    return this.entranceQueue;
  }

  public Counter getMinCounter() {
    return this.counters.min();
  }

  public Customer deQueueEntrance() {
    return this.entranceQueue.deq();
  }
}
