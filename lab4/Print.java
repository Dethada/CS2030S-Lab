/**
 * A non-generic Action to print the String
 * representation of the object.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author David Zhu (Group 12B)
 */

class Print implements Action<Print> {
    @Override
    public <T> void call(T value) {
        System.out.println(value);
    }
}
