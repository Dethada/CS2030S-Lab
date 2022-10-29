package cs2030s.fp;

import java.util.List;
import java.util.ArrayList;

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
      .except(() -> this.tail.get().isEnd() ? this.tail.get() : this.tail.get().tail());
  }

  public <R> InfiniteList<R> map(Immutator<? extends R, ? super T> f) {
    return new InfiniteList<R>(
      Memo.from(() -> this.head.get().transform(x -> f.invoke(x))),
      Memo.from(() -> this.tail.get().map(f)));
  }

  public InfiniteList<T> filter(Immutator<Boolean, ? super T> pred) {
    return new InfiniteList<T>(
      Memo.from(() -> this.head.get().check(pred)),
      Memo.from(() -> this.tail.get().filter(pred))
    );
  }

  public InfiniteList<T> limit(long n) {
    if (n <= 0) {
      return InfiniteList.end();
    }
    return new InfiniteList<T>(
      Memo.from(() -> Actually.ok(this.head())),
      // Memo.from(Actually.ok(this.head())),
      Memo.from(() -> this.tail().limit(n-1))
    );
  }

  public InfiniteList<T> takeWhile(Immutator<Boolean, ? super T> pred) {
    Memo<Boolean> c = Memo.from(() -> pred.invoke(this.head()));
    return new InfiniteList<T>(
      Memo.from(() -> c.get() ? Actually.ok(this.head()) : Actually.err()),
      Memo.from(() -> c.get() ? this.tail().takeWhile(pred) : InfiniteList.end())
    );
  }

  public List<T> toList() {
    List<T> list = new ArrayList<>(new ArrayList<>());
    InfiniteList<T> tmp = this;
    while (!tmp.isEnd()) {
      tmp.head.get().finish(x -> list.add(x));
      tmp = tmp.tail.get();
    }
    return list;
  }

  public <U> U reduce (U id, Combiner<U, U, ? super T> acc) {
    InfiniteList<T> tmp = this;
    U res = id;
    // Memo<U> res = Memo.from(id);
    while (!tmp.isEnd()) {
      if (tmp.head.get().transform(_x -> true).unless(false)) {
        res = acc.combine(res, tmp.head());
      }
      // tmp.head.get().finish(x -> {
      //   res = res.combine(Memo.from(x), acc);
      // });
      tmp = tmp.tail.get();
    }
    return res;
  }


  public long count() {
    return this.map(x -> 1).reduce(0, (x, y) -> x + y);
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
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.end();
    }

    @Override
    public InfiniteList<Object> takeWhile(Immutator<Boolean, ? super Object> pred) {
      return InfiniteList.end();
    }

    @Override
    public String toString() {
      return "-";
    }

  }

  public static <T> InfiniteList<T> end() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> res = (InfiniteList<T>) InfiniteList.END;
    return res;
  }
}
