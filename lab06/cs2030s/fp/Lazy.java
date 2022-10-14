package cs2030s.fp;

/**
 *
 *
 * <pre>The Lazy class does lazy computations.
 *
 * CS2030S Lab 6
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 * @param <T> The type of the value to be lazied.
 */
public class Lazy<T> implements Immutatorable<T> {

  /** The value wrapped in the Constant class. */
  private final Constant<? extends T> init;

  /**
   * The constructor for Lazy class.
   *
   * @param c The Constant value to be lazified.
   */
  protected Lazy(Constant<? extends T> c) {
    this.init = c;
  }

  /**
   * Factory method to create a new lazy instance.
   *
   * @param <T> The type of the value to be lazified.
   * @param v The value to be lazified.
   * @return The lazified value.
   */
  public static <T> Lazy<T> from(T v) {
    return (Lazy<T>) new Lazy<>(() -> v);
  }

  /**
   * Factory method to create a new lazy instance.
   *
   * @param <T> The type for the value to be lazified.
   * @param c The value to be lazified.
   * @return The lazified value.
   */
  public static <T> Lazy<T> from(Constant<T> c) {
    return (Lazy<T>) new Lazy<>(c);
  }

  /*
   * Get the lazified value.
   *
   * @return The lazified value.
   */
  public T get() {
    return this.init.init();
  }

  /*
   * Get the string representation of this value.
   *
   * @return The string representation of this value.
   */
  @Override
  public String toString() {
    return this.get().toString();
  }

  /*
   * Mutates the lazified value with a Immutator that returns a value.
   *
   * @param <R> The type of the lazified value to be returned.
   * @param f The Immutator that will mutate the value.
   * @return The new lazified value.
   */
  @Override
  public <R> Lazy<R> transform(Immutator<? extends R, ? super T> f) {
    return Lazy.from(() -> f.invoke(this.get()));
  }

  /*
   * Mutates the lazified value with a Immutator that returns a lazified value.
   *
   * @param <R> The type of the lazified value to be returned.
   * @param f The Immutator that will mutate the value.
   * @return The new lazified value.
   */
  public <R> Lazy<R> next(Immutator<? extends Lazy<R>, ? super T> f) {
    return f.invoke(this.get());
  }
}
