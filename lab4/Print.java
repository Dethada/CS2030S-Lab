/**
 * <pre>A non-generic Action to print the String
 * representation of the object.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 */
class Print implements Action<Object> {
  @Override
  public void call(Object value) {
    System.out.println(value);
  }
}
