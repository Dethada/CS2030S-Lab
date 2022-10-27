package cs2030s.fp;

import java.util.List;

public class InfiniteList<T> {
  private Memo<Actually<T>> head;
  private Memo<InfiniteList<T>> tail;
  private static final End END = new End();

  private InfiniteList() {
    this.head = null;
    this.tail = null;
  }

  private InfiniteList(Memo<Actually<T>> head, Memo<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }
  // You may add other private constructor but it's not necessary.

  public static <T> InfiniteList<T> generate(Constant<T> prod) {
    return new InfiniteList<T>(
        Memo.from(() -> Actually.ok(prod.init())),
        Memo.from(() -> InfiniteList.generate(prod)));
  }

  public static <T> InfiniteList<T> iterate(T seed, Immutator<T, T> func) {
    return new InfiniteList<T>(
      Memo.from(Actually.ok(seed)),
      Memo.from(() -> InfiniteList.iterate(func.invoke(seed), func)));
  }

  public T head() {
    return this.head.get().except(() -> this.tail.get().head());
  }

  public InfiniteList<T> tail() {
    return this.head.get()
      .transform(_x -> this.tail.get())
      .except(() -> this.tail.get().tail());
  }

  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    return new InfiniteList<R>(
      Memo.from(() -> Actually.ok(f.invoke(this.head()))),
      Memo.from(() -> this.tail().map(f)));
  }

  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    return new InfiniteList<T>(
      Memo.from(() -> this.head.get().check(pred)),
      Memo.from(() -> this.tail.get().filter(pred))
    );
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    return new InfiniteList<>(null, null);
  }

  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    // TODO
    return new InfiniteList<>(null, null);
  }

  public List<T> toList() {
    // TODO
    return List.of();
  }

  public <U> U reduce (U id, Combiner<U, U, ? super T> acc) {
    // TODO
    return null;
  }


  public long count() {
    // TODO
    return 0L;
  }

  @Override
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  public boolean isEnd() {
    return this.equals(InfiniteList.END);
  }


  private static final class End extends InfiniteList<Object> {
    private End() {
      super();
    }

    @Override
    public Object head() {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public InfiniteList<Object> tail() {
      throw new java.util.NoSuchElementException();
    }

    @Override
    public <R> InfiniteList<R> map(Immutator<? extends R, ? super Object> op) {
      return InfiniteList.<R>end();
    }

    @Override
    public InfiniteList<Object> filter(Immutator<Boolean, ? super Object> op)  {
      return InfiniteList.end();
    }

    @Override
    public String toString() {
      return "[]";
    }

  }

  public static <T> InfiniteList<T> end() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> res = (InfiniteList<T>) InfiniteList.END;
    return res;
  }
}
