package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A infinite list.
 *
 * <p>CS2030S Lab 8
 *
 * @author Adi Yoga S. Prabawa
 * @author David Zhu (Group 12B)
 * @version CS2030S AY 22/23 Sem 1
 * @param <T> The type of the values in the Infinite List.
 */
public class InfiniteList<T> {
  /** The Memoized value. */
  private Memo<Actually<T>> head;

  /** The Memoized tail. */
  private Memo<InfiniteList<T>> tail;

  /** The cached end object. */
  private static final End END = new End();

  /**
   * A private constructor to initialize a empty infinite list.
   */
  private InfiniteList() {
    this.head = null;
    this.tail = null;
  }

  /**
   * A private constructor to initialize the infinite list.
   *
   * @param head The memoized head value.
   * @param tail The memoized tail value.
   */
  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Generate a infinite list using the given constant.
   *
   * @param <T> The type of the values in the list.
   * @param prod The constant to generate values from.
   * @return A infinite list.
   */
  public static <T> InfiniteList<T> generate(Constant<T> prod) {
    return new InfiniteList<T>(
        Memo.from(() -> Actually.ok(prod.init())), Memo.from(() -> InfiniteList.generate(prod)));
  }

  /**
   * Generate a infinite list using the given function and seed.
   *
   * @param <T> The type of the values in the list.
   * @param seed The starting value.
   * @param func The function to generate values from.
   * @return A infinite list.
   */
  public static <T> InfiniteList<T> iterate(T seed, Immutator<? extends T, ? super T> func) {
    return new InfiniteList<T>(
        Memo.from(Actually.ok(seed)),
        Memo.from(() -> InfiniteList.iterate(func.invoke(seed), func)));
  }

  /**
   * Get the value of the head.
   *
   * @return The value of the head.
   */
  public T head() {
    return this.head.get().except(() -> this.tail.get().head());
  }

  /**
   * Get the tail of the list.
   *
   * @return The tail of the list.
   */
  public InfiniteList<T> tail() {
    return this.head
        .get()
        .transform(x -> this.tail.get())
        .except(() -> this.tail.get().isEnd() ? this.tail.get() : this.tail.get().tail());
  }

  /**
   * Lazily applies the given Immutator on each element in the list and returns the resulting
   * InfiniteList.
   *
   * @param <R> The return type of the Immutator.
   * @param f The Immutator.
   * @return The resulting InfiniteList.
   */
  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    return new InfiniteList<R>(
        Memo.from(() -> this.head.get().transform(x -> f.invoke(x))),
        Memo.from(() -> this.tail.get().map(f)));
  }

  /**
   * Lazily filters the list using the given predicate.
   *
   * @param pred The predicate.
   * @return The resulting InfiniteList.
   */
  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    return new InfiniteList<T>(
        Memo.from(() -> this.head.get().check(pred)),
        Memo.from(() -> this.tail.get().filter(pred)));
  }

  /**
   * Lazily limits the list to the given number of elements.
   *
   * @param n The number of elements to limit to.
   * @return The resulting InfiniteList.
   */
  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.end();
    }
    return new InfiniteList<T>(
        this.head.toString() == "?"
            ? Memo.from(() -> Actually.ok(this.head()))
            : Memo.from(Actually.ok(this.head())),
        Memo.from(() -> this.tail().limit(n - 1)));
  }

  /**
   * Lazily terminates the list using the given predicate.
   *
   * @param pred The predicate.
   * @return The resulting InfiniteList.
   */
  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    Memo<Boolean> c = Memo.from(() -> pred.invoke(this.head()));
    return new InfiniteList<T>(
        Memo.from(() -> c.get() ? Actually.ok(this.head()) : Actually.err()),
        Memo.from(() -> c.get() ? this.tail().takeWhile(pred) : InfiniteList.end()));
  }

  /**
   * Converts the Infinite list to a List.
   *
   * @return The converted list.
   */
  public List<T> toList() {
    List<T> list = new ArrayList<>(new ArrayList<>());
    InfiniteList<T> tmp = this;
    while (!tmp.isEnd()) {
      tmp.head.get().finish(x -> list.add(x));
      tmp = tmp.tail.get();
    }
    return list;
  }

  /**
   * Performs a reduction on the elements of the list using the
   * provided identity element and combiner function.
   *
   * @param <U> The type of the result.
   * @param id The identity element.
   * @param acc The combiner function.
   * @return The result of the reduction.
   */
  public <U> U reduce(U id, Combiner<? extends U, ? super U, ? super T> acc) {
    InfiniteList<T> tmp = this;
    U res = id;
    while (!tmp.isEnd()) {
      if (tmp.head.get().transform(x -> true).unless(false)) {
        res = acc.combine(res, tmp.head());
      }
      tmp = tmp.tail.get();
    }
    return res;
  }

  /**
   * Returns the number of elements in the list.
   *
   * @return The number of elements in the list.
   */
  public long count() {
    return this.map(x -> 1).reduce(0, (x, y) -> x + y);
  }

  /**
   * Return the string representation of this list.
   *
   * @return The string representation of this list.
   */
  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  /**
   * Return whether this list signals the end of a list.
   *
   * @return True if this is the end of a list, else false.
   */
  public boolean isEnd() {
    return this.equals(InfiniteList.END);
  }

  /**
   * Static nested class to represent the end of a list.
   */
  private static final class End extends InfiniteList<Object> {

    /**
     * Private constructor to initialize the end of a list.
     */
    private End() {
      super();
    }

    /**
     * Don't allow calling head.
     *
     * @throws NoSuchElementException No element for the end class.
     */
    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    /**
     * Don't allow calling tail.
     *
     * @throws NoSuchElementException No element for the end class.
     */
    @Override
    public InfiniteList<Object> tail() {
      throw new NoSuchElementException();
    }

    /**
     * Return the end of a list if map is called.
     *
     * @param <R> The return type of the Immutator.
     * @param op The Immutator.
     * @return End of a list.
     */
    @Override
    public <R> InfiniteList<R> map(Immutator<? extends R, ? super Object> op) {
      return InfiniteList.<R>end();
    }

    /**
     * Return the end of a list if filter is called.
     *
     * @param op The Immutator.
     * @return End of a list.
     */
    @Override
    public InfiniteList<Object> filter(Immutator<Boolean, ? super Object> op) {
      return InfiniteList.end();
    }

    /**
     * Return the end of a list if limit is called.
     *
     * @param n The number of elements to limit to.
     * @return End of a list.
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.end();
    }

    /**
     * Return the end of a list if takeWhile is called.
     *
     * @param pred The predicate.
     * @return End of a list.
     */
    @Override
    public InfiniteList<Object> takeWhile(Immutator<Boolean, ? super Object> pred) {
      return InfiniteList.end();
    }

    /**
     * Return the string representation of the end of a list.
     *
     * @return The string representation of the end of a list.
     */
    @Override
    public String toString() {
      return "-";
    }
  }

  /**
   * Generate a list that represents the end of a list.
   *
   * @param <T> The type of the list.
   * @return The list that represents the end of a list.
   */
  public static <T> InfiniteList<T> end() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> res = (InfiniteList<T>) InfiniteList.END;
    return res;
  }
}
