package cs2030s.fp;

public abstract class Transformer<R, P> implements Immutator<R, P> {
    public <N> Transformer<R, N> after(Transformer<P, N> g) {
        return new Transformer<R, N>() {
            @Override
            public R invoke(N x) {
                return Transformer.this.invoke(g.invoke(x));
            }
        };
    }

    public <T> Transformer<T, P> before(Transformer<T, R> g) {
        return new Transformer<T, P>() {
            @Override
            public T invoke(P x) {
                return g.invoke(Transformer.this.invoke(x));
            }
        };
    }
}
