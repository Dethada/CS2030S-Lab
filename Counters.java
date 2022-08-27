/**
 * The Counters class stores the state of all counters and handles operations related to the
 * counters.
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class Counters {
  /**
   * Boolean array to represent whether a counter is available or not. false value will indicate the
   * counter is not in use and available. true value will indicate the counter is in use and not
   * available.
   */
  private boolean[] counters;

  /**
   * Constructor for Counters.
   *
   * @param n The number of counters to create.
   */
  public Counters(int n) {
    this.counters = new boolean[n]; // defaults to false values
  }

  /**
   * Constructor for Counters.
   *
   * @param counters The boolean array to represent the counters states.
   */
  public Counters(boolean[] counters) {
    this.counters = counters;
  }

  /**
   * Get an available counter and set it to be unavailable.
   *
   * @return The Counter ID of an available counter or -1 if there is no available counter.
   */
  public int useCounter() {
    int c = this.getAvailableCounter();
    if (c != -1) {
      this.setState(c, true);
    }
    return c;
  }

  /**
   * Set an counter to be available.
   *
   * @param i The index (counterID) of the counter to free up.
   */
  public void freeCounter(int i) {
    setState(i, false);
  }

  /**
   * Get an available counter.
   *
   * @return The Counter ID of an available counter or -1 if there is no available counter.
   */
  private int getAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (!this.counters[i]) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Set the state of a counter.
   *
   * @param i The index (counterID) of the counter to set the state for.
   * @param state The state of the counter, true for unavailable, false for available.
   */
  private void setState(int i, boolean state) {
    this.counters[i] = state;
  }
}
