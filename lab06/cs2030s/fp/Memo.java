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
}
