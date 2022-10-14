package cs2030s.fp;

public class Memo<T> extends Lazy<T> {
  private Actually<T> value;

  private Memo(T v) {
    super(() -> v);
    this.value = Actually.ok(v);
  }

  private Memo(Constant<T> c) {
    super(c);
    this.value = Actually.err(new Exception());
  }

  public static <T> Memo<T> from(T v) {
    return (Memo<T>) new Memo<>(v);
  }

  public static <T> Memo<T> from(Constant<T> c) {
    return (Memo<T>) new Memo<>(c);
  }

  @Override
  public T get() {
    return this.value.except(() -> {
      T x = super.get();
      this.value = Actually.ok(x);
      return x;
    });
  }

  @Override
  public String toString() {
    return this.value.next(x -> Actually.ok(x.toString())).unless("?");
  }

  @Override
  public <R> Memo<R> transform(Immutator<? extends R, ? super T> f) {
    return Memo.from(() -> f.invoke(this.get()));
  }

  @Override
  public <R> Memo<R> next(Immutator<? extends Lazy<R>, ? super T> f) {
    return Memo.from(() -> f.invoke(this.get()).get());
  }

  public <R, S> Memo<R> combine(Memo<S> x, Combiner<? extends R, ? super T, ? super S> c) {
    return Memo.from(() -> c.combine(this.get(), x.get()));
  }
}
