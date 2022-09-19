/**
 * The Immutatorable interface that can
 * transform when given something that is
 * Immutator.
 *
 * Contains a single abstract method transform.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 *
 * @author David Zhu (Group 12B)
 */
interface Immutatorable<T> {
  <R> Immutatorable<R> transform(Immutator<R, T> x);
}
