/**
 * <pre>The Applicable interface that can probably
 * transform if given something that is
 * probably an Immutator.
 *
 * Contains a single abstract method apply.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 */
interface Applicable<T> {
  <R> Probably<R> apply(Probably<Immutator<R, T>> x);
}
