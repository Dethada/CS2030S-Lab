import cs2030s.fp.Constant;
import cs2030s.fp.Memo;

/**
 *
 *
 * <pre>The Bool class that stores a boolean value.
 *
 * CS2030S Lab 6
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 */
class Bool implements Cond {

  /** The memoized boolean value. */
  private Memo<Boolean> val;

  /**
   * The constructor for Boolean class.
   *
   * @param val The Constant boolean value.
   */
  public Bool(Constant<Boolean> val) {
    this.val = Memo.from(val);
  }

  /*
   * Get the boolean value.
   *
   * @return The boolean value.
   */
  @Override
  public boolean eval() {
    return this.val.get();
  }

  /*
   * Get the string representation of this value.
   *
   * @return The string representation of this value.
   */
  @Override
  public String toString() {
    return this.val.toString().substring(0, 1);
  }

  /*
   * Negate this boolean value.
   *
   * @return A Cond.
   */
  @Override
  public Cond neg() {
    return new Not(this);
  }
}
