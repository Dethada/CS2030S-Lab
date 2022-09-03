import java.util.Optional;

/**
 * This class represents a shop, containing all the counters.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Shop {
  /** The counters in the shop. */
  private Counter[] counters;

  /**
   * Constructor for Shop.
   *
   * @param numCounters The number of counters in the shop.
   */
  public Shop(int numCounters) {
    Counter[] counters = new Counter[numCounters];
    for (int i = 0; i < numCounters; i++) {
      counters[i] = new Counter(i);
    }
    this.counters = counters;
  }

  /**
   * Get a counter based on the index (Id) of the counter.
   *
   * @param i The index of the counter.
   * @return The counter at index i.
   */
  public Counter getCounter(int i) {
    return this.counters[i];
  }

  /**
   * Get an available counter and set it to be unavailable.
   *
   * @return An available Counter that was just set to unavailable or null.
   */
  public Optional<Counter> useCounter() {
    Optional<Counter> c = this.getAvailableCounter();
    if (c.isPresent()) {
      c.get().setUnavailable();
    }
    return c;
  }

  /**
   * Get an available counter.
   *
   * @return An available Counter or null.
   */
  private Optional<Counter> getAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].isAvailable()) {
        return Optional.of(this.counters[i]);
      }
    }
    return Optional.ofNullable(null);
  }

  /**
   * Check if there is a available counter in the shop.
   *
   * @return true if there is a available counter, false if there is no available counter.
   */
  public boolean hasAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].isAvailable()) {
        return true;
      }
    }
    return false;
  }
}
