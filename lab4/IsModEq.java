/**
 * A non-generic Immutator with parameter
 * div and mod that takes in an integer val
 * and return the boolean value by checking
 * if the given value is equal to mod when
 * divided by div.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author David Zhu (Group 12B)
 */
class IsModEq implements Immutator<Boolean, Integer> {
  public IsModEq(int div, int check) {
    // TODO
  }

  @Override
  public Boolean invoke(Integer val) {
    // TODO
    return val == val;
  }
}
