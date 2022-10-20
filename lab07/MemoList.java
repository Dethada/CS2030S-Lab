import cs2030s.fp.Combiner;
import cs2030s.fp.Immutator;
import cs2030s.fp.Memo;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper around a lazily evaluated and memoized list that can be generated with a lambda
 * expression.
 *
 * <p>CS2030S Lab 7
 *
 * @author Adi Yoga S. Prabawa
 * @author David Zhu (Group 12B)
 * @version CS2030S AY 22/23 Sem 1
 * @param <T> The type of the values in the Memo List.
 */
class MemoList<T> {
  /** The wrapped java.util.List object */
  private List<Memo<T>> list;

  /**
   * A private constructor to initialize the list to the given one.
   *
   * @param list The given java.util.List to wrap around.
   */
  private MemoList(List<Memo<T>> list) {
    this.list = list;
  }

  /**
   * Generate the content of the list. Given x and a lambda f, generate the list of n elements as
   * [x, f(x), f(f(x)), f(f(f(x))), ... ]
   *
   * @param <T> The type of the elements in the list.
   * @param n The number of elements.
   * @param seed The first element.
   * @param f The immutator function on the elements.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(int n, T seed, Immutator<? extends T, ? super T> f) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    Memo<T> curr = Memo.from(seed);
    for (int i = 0; i < n; i++) {
      memoList.list.add(curr);
      Memo<T> tmp = curr;
      curr = Memo.from(() -> f.invoke(tmp.get()));
    }
    return memoList;
  }

  /**
   * Generate the content of the list. Given x, y and a lambda f, generate the list of n elements as
   * [x, y, f(x, y), f(f(x, y)), ... ]
   *
   * @param <T> The type of the elements in the list.
   * @param n The number of elements.
   * @param fst The first element.
   * @param snd The second element.
   * @param f The combiner function on the elements.
   * @return The created list.
   */
  public static <T> MemoList<T> generate(
      int n, T fst, T snd, Combiner<? extends T, ? super T, ? super T> f) {
    MemoList<T> memoList = new MemoList<>(new ArrayList<>());
    Memo<T> xfst = Memo.from(fst);
    Memo<T> xsnd = Memo.from(snd);
    memoList.list.add(xfst);
    memoList.list.add(xsnd);
    for (int i = 2; i < n; i++) {
      Memo<T> tfst = xfst;
      Memo<T> tsnd = xsnd;
      xfst = xsnd;
      xsnd = Memo.from(() -> f.combine(tfst.get(), tsnd.get()));
      memoList.list.add(xsnd);
    }
    return memoList;
  }

  /**
   * Return the element at index i of the list.
   *
   * @param i The index of the element to retrieved (0 for the 1st element).
   * @return The element at index i.
   */
  public T get(int i) {
    return this.list.get(i).get();
  }

  /**
   * Find the index of a given element.
   *
   * @param v The value of the element to look for.
   * @return The index of the element in the list. -1 is element is not in the list.
   */
  public int indexOf(T v) {
    return this.list.indexOf(Memo.from(v));
  }

  /**
   * Return the string representation of the list.
   *
   * @return The string representation of the list.
   */
  @Override
  public String toString() {
    return this.list.toString();
  }

  /**
   * Lazily applies the given Immutator on each element in the list and returns the resulting
   * MemoList.
   *
   * @param <R> The return type of the Immutator.
   * @param f The Immutator.
   * @return The resulting MemoList.
   */
  public <R> MemoList<R> map(Immutator<? extends R, ? super T> f) {
    MemoList<R> memoList = new MemoList<>(new ArrayList<>());
    for (Memo<T> x : this.list) {
      Memo<R> tmp = Memo.from(() -> f.invoke(x.get()));
      memoList.list.add(tmp);
    }
    return memoList;
  }

  /**
   * Lazily applies the given Immutator on each element in the list and returns the resulting
   * MemoList. Also flattens the resulting MemoList.
   *
   * @param <R> The return type of the Immutator.
   * @param f The Immutator.
   * @return The resulting MemoList.
   */
  public <R> MemoList<R> flatMap(Immutator<MemoList<R>, ? super T> f) {
    MemoList<R> memoList = new MemoList<>(new ArrayList<>());
    for (Memo<T> x : this.list) {
      for (Memo<R> y : f.invoke(x.get()).list) {
        memoList.list.add(y);
      }
    }
    return memoList;
  }
}
