package cs2030s.fp;
/**
 * <pre>The Actionable interface that can
 * act when given an action.
 *
 * Contains a single abstract method act.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 */
public interface Actionable<T> {
  void act(Action<? super T> action);
}
