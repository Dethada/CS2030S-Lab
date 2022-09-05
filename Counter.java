/**
 * Class representing a Counter.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Counter {
  /*
   * Whether the counter is available, true for available, false for unavailable.
   */
  private boolean available;

  /*
   * Id of the Counter.
   */
  private final int id;

  /**
   * Constructor for Counter.
   *
   * @param id The Id of the Counter.
   */
  public Counter(int id) {
    this.available = true;
    this.id = id;
  }

  /**
   * Return the string representation this Counter.
   *
   * @return A string representation of the Counter Id.
   */
  @Override
  public String toString() {
    return "S" + this.id;
  }

  /** Set this counter to unavailable. */
  public void setUnavailable() {
    this.available = false;
  }

  /** Set this counter to available. */
  public void setAvailable() {
    this.available = true;
  }

  /**
   * Check if the counter is available.
   *
   * @return true if available, false if unavailable.
   */
  public boolean isAvailable() {
    return this.available;
  }

  /**
   * Return the Id of this Counter.
   *
   * @return the Id of this Counter.
   */
  public int getId() {
    return this.id;
  }
}
