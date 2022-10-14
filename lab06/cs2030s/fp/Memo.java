package cs2030s.fp;

/**
 *
 *
 * <pre>The Memo class is lazy and caches the value and does not recompute the value.
 *
 * CS2030S Lab 6
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 * @param <T> The type of the value to be memoized.
 */
public class Memo<T> extends Lazy<T> {

  /** The value wrapped in the Actually class. */
  private Actually<T> value;

  /**
   * Constructor for Memo class.
   *
   * @param v The value to be memoized.
   */
  private Memo(T v) {
    super(() -> v);
    this.value = Actually.ok(v);
  }

  /**
   * Constructor for Memo class.
   *
   * @param c The Constant to be memoized.
   */
  private Memo(Constant<T> c) {
    super(c);
    this.value = Actually.err(new Exception());
  }

  /**
   * Factory method to create a new memo instance.
   *
   * @param <T> The type for the value to be memoized.
   * @param v The value to be memoized.
   * @return The memoized value.
   */
  public static <T> Memo<T> from(T v) {
    return (Memo<T>) new Memo<>(v);
  }

  /**
   * Factory method to create a new memo instance.
   *
   * @param <T> The type for the value to be memoized.
   * @param c The value to be memoized.
   * @return The memoized value.
   */
  public static <T> Memo<T> from(Constant<T> c) {
    return (Memo<T>) new Memo<>(c);
  }

  /*
   * Get the memoized value.
   *
   * @return The memoized value.
   */
  @Override
  public T get() {
    return this.value.except(
        () -> {
          T x = super.get();
          this.value = Actually.ok(x);
          return x;
        });
  }

  /*
   * Get the string representation of this value.
   *
   * @return The string representation of this value
   * if it is caluculated, "?" if it is not.
   */
  @Override
  public String toString() {
    return this.value.next(x -> Actually.ok(x.toString())).unless("?");
  }

  /*
   * Mutates the memoized value with a Immutator that returns a value.
   *
   * @param <R> The type of the memoized value to be returned.
   * @param f The Immutator that will mutate the value.
   * @return The new memoized value.
   */
  @Override
  public <R> Memo<R> transform(Immutator<? extends R, ? super T> f) {
    return Memo.from(() -> f.invoke(this.get()));
  }

  /*
   * Mutates the memoized value with a Immutator that returns a memoized value.
   *
   * @param <R> The type of the memoized value to be returned.
   * @param f The Immutator that will mutate the value.
   * @return The new memoized value.
   */
  @Override
  public <R> Memo<R> next(Immutator<? extends Lazy<R>, ? super T> f) {
    return Memo.from(() -> f.invoke(this.get()).get());
  }

  /*
   * Combines two memoized values into one.
   *
   * @param <R> The type of the memoized value to be returned.
   * @param <S> The type of the other memoized value to be combined.
   * @param x The other memoized value to be combined.
   * @param c The Combiner that will combine the value.
   * @return The new memoized value.
   */
  public <R, S> Memo<R> combine(Memo<S> x, Combiner<? extends R, ? super T, ? super S> c) {
    return Memo.from(() -> c.combine(this.get(), x.get()));
  }
}
