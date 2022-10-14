package cs2030s.fp;

public class Lazy<T> implements Immutatorable<T> {
  private Constant<? extends T> init;

  protected Lazy(Constant<? extends T> c) {
    this.init = c;
  }

  public static <T> Lazy<T> from(T v) {
    return (Lazy<T>) new Lazy<>(() -> v);
  }

  public static <T> Lazy<T> from(Constant<T> c) {
    return (Lazy<T>) new Lazy<>(c);
  }

  public T get() {
    return this.init.init();
  }

  @Override
  public String toString() {
    return this.get().toString();
  }

  @Override
  public <R> Lazy<R> transform(Immutator<? extends R, ? super T> f) {
    return Lazy.from(() -> f.invoke(this.get()));
  }

  public <R> Lazy<R> next(Immutator<? extends Lazy<R>, ? super T> f) {
    return f.invoke(this.get());
  }
}
