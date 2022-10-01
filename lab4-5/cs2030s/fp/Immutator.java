package cs2030s.fp;
/**
 * <pre>The Immutator interface that can transform
 * to type T2, an object of type T1.
 *
 * Contains a single abstract method invoke.
 *
 * CS2030S Lab 4
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 */
public interface Immutator<R, P> {
  R invoke(P x);
}
