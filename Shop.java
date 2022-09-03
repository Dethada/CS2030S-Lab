import java.util.Optional;

/**
 * This class encapsulates an event in the shop simulation. Your task is to replace this class with
 * new classes, following proper OOP principles.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Shop {
  /** The availability of counters in the shop. */
  private Counter[] counters;

  public Shop(int numCounters) {
    Counter[] counters = new Counter[numCounters];
    for (int i = 0; i < numCounters; i++) {
      counters[i] = new Counter(i);
    }
    this.counters = counters;
  }

  /**
   * Constructor for a shop event.
   *
   * @param time The time this event occurs.
   * @param customer The customer associated with this event.
   */
  public Shop(Counter[] counters) {
    this.counters = counters;
  }

  public Counter getCounter(int i) {
    return this.counters[i];
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
