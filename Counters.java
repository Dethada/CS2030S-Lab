/**
 * TODO
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class Counters {
  private boolean counters[];

  /** */
  public Counters(int n) {
    this.counters = new boolean[n]; // defaults to false values
  }

  /** */
  public Counters(boolean[] counters) {
    this.counters = counters;
  }

  public int useCounter() {
    int c = this.getAvailableCounter();
    if (c != -1) {
      this.setState(c, true);
      return c;
    }
    return -1;
  }

  public void freeCounter(int i) {
    setState(i, false);
  }

  private int getAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (!this.counters[i]) {
        return i;
      }
    }
    return -1;
  }

  private void setState(int i, boolean state) {
    this.counters[i] = state;
  }
}
