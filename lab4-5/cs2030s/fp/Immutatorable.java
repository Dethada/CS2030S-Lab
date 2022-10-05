package cs2030s.fp;
/**
 * <pre>The Immutatorable interface that can
 * transform when given something that is
 * Immutator.
 *
 * Contains a single abstract method transform.
 *
 * CS2030S Lab 5
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 */
public interface Immutatorable<T> {
  <R> Immutatorable<R> transform(Immutator<? extends R, ? super T> x);
}
