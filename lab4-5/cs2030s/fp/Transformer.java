package cs2030s.fp;
/**
 * <pre>The Transformer class that can
 * chain functions together.
 *
 * CS2030S Lab 5
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 */
public abstract class Transformer<R, P> implements Immutator<R, P> {
  public <N> Transformer<R, N> after(Transformer<? extends P, ? super N> g) {
    return new Transformer<R, N>() {
      @Override
      public R invoke(N x) {
        return Transformer.this.invoke(g.invoke(x));
      }
    };
  }

  public <T> Transformer<T, P> before(Transformer<? extends T, ? super R> g) {
    return new Transformer<T, P>() {
      @Override
      public T invoke(P x) {
        return g.invoke(Transformer.this.invoke(x));
      }
    };
  }
}
