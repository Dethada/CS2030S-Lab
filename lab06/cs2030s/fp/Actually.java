package cs2030s.fp;
/**
 * <pre>The Actually class that imitates Result behaviour of rust.
 *
 * CS2030S Lab 5
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 */
public abstract class Actually<T> implements Immutatorable<T>, Actionable<T> {

  public static <T> Actually<T> ok(T res) {
    return (Success<T>) new Success<>(res);
  }

  public static <T> Actually<T> err(Exception exc) {
    @SuppressWarnings("unchecked")
    Actually<T> tmp = (Actually<T>) new Failure(exc);
    return tmp;
  }

  public abstract boolean equals(Object x);

  public abstract T unwrap() throws Exception;

  public abstract T except(Constant<? extends T> x);

  public abstract void finish(Action<? super T> x);

  public abstract T unless(T x);

  public abstract <R> Actually<R> next(Immutator<? extends Actually<R>, ? super T> x);

  private static final class Success<T> extends Actually<T> {
    private final T res;

    private Success(T res) {
      this.res = res;
    }

    @Override
    public String toString() {
      if (this.res == null) {
        return "<>";
      } else {
        return "<" + this.res.toString() + ">";
      }
    }

    @Override
    public boolean equals(Object x) {
      if (x == this) {
        return true;
      }
      if (x instanceof Success<?>) {
        Success<?> some = (Success<?>) x;
        if (this.res == some.res) {
          return true;
        }
        if (this.res == null || some.res == null) {
          return false;
        }
        return this.res.equals(some.res);
      }
      return false;
    }

    @Override
    public T unwrap() {
      return this.res;
    }

    @Override
    public T except(Constant<? extends T> x) {
      return this.res;
    }

    @Override
    public void finish(Action<? super T> x) {
      x.call(this.res);
    }

    @Override
    public T unless(T x) {
      return this.res;
    }

    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super T> x) {
      Actually<R> tmp;
      try {
        tmp = Actually.ok(x.invoke(this.res));
      } catch (Exception e) {
        tmp = Actually.err(e);
      }
      return tmp;
    }

    @Override
    public void act(Action<? super T> action) {
      if (this.res == null) {
        return;
      }
      action.call(this.res);
    }

    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<R>, ? super T> x) {
      try {
        return x.invoke(this.res);
      } catch (Exception e) {
        return Actually.err(e);
      }
    }
  }

  private static final class Failure extends Actually<Object> {
    private final Exception exc;

    private Failure(Exception exc) {
      this.exc = exc;
    }

    @Override
    public String toString() {
      return "[" + this.exc.getClass().getCanonicalName() + "] " + this.exc.getMessage();
    }

    @Override
    public boolean equals(Object x) {
      if (x == this) {
        return true;
      }
      if (x instanceof Failure) {
        Failure some = (Failure) x;
        if (this.exc.getMessage() == null || some.exc.getMessage() == null) {
          return false;
        } else if (this.exc.getMessage() == some.exc.getMessage()) {
          return true;
        }
        return this.exc.equals(some.exc);
      }
      return false;
    }

    @Override
    public Object unwrap() throws Exception {
      throw this.exc;
    }

    @Override
    public Object except(Constant<? extends Object> x) {
      return x.init();
    }

    @Override
    public void finish(Action<Object> x) {}

    @Override
    public Object unless(Object x) {
      return x;
    }

    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super Object> x) {
      return Actually.err(this.exc);
    }

    @Override
    public void act(Action<? super Object> action) {}

    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<R>, Object> x) {
      return Actually.err(this.exc);
    }
  }
}
