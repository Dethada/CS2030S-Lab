package cs2030s.fp;

/**
 *
 *
 * <pre>The Actually class that imitates Result behaviour of rust.
 *
 * CS2030S Lab 6
 * AY22/23 Semester 1
 * </pre>
 *
 * @author David Zhu (Group 12B)
 * @param <T> The type of the value to be wrapped.
 */
public abstract class Actually<T> implements Immutatorable<T>, Actionable<T> {

  /**
   * Factory method to create a new Success instance.
   *
   * @param <T> The type of the value to be wrapped.
   * @param res The value to be wrapped.
   * @return The wrapped value.
   */
  public static <T> Actually<T> ok(T res) {
    return (Success<T>) new Success<>(res);
  }

  /**
   * Factory method to create a new Failure instance.
   *
   * @param <T> The type of the exception to be wrapped.
   * @param exc The exception to be wrapped.
   * @return The wrapped exception.
   */
  public static <T> Actually<T> err(Exception exc) {
    @SuppressWarnings("unchecked")
    Actually<T> tmp = (Actually<T>) new Failure(exc);
    return tmp;
  }

  /**
   * Compare if this object is equals to another object.
   *
   * @param x The other object to be compared to.
   * @return True if equal, else false.
   */
  public abstract boolean equals(Object x);

  /**
   * Tries to retrieve the value, throw exception if it fails.
   *
   * @return The unwrapped value.
   * @throws Exception The exception encountered.
   */
  public abstract T unwrap() throws Exception;

  /**
   * Tries to retrieve the value, returns the provided constant instead if it fails.
   *
   * @param x The constant.
   * @return The unwrapped value or the provided constant.
   */
  public abstract T except(Constant<? extends T> x);

  /**
   * Do a provided action.
   *
   * @param x The action.
   */
  public abstract void finish(Action<? super T> x);

  /**
   * Tries to retrieve the value, returns the provided value instead if it fails.
   *
   * @param x The constant.
   * @return The unwrapped value or provided value if it fails.
   */
  public abstract T unless(T x);

  /**
   * Mutates the wrapped value with a Immutator that returns a value.
   *
   * @param <R> The type of the wrapped value to be returned.
   * @param x The Immutator that will mutate the value.
   * @return The wrapped mututated value.
   */
  public abstract <R> Actually<R> next(Immutator<? extends Actually<R>, ? super T> x);

  /**
   * Static nested class to represent success.
   *
   * @param <T> The type of the successful value.
   */
  private static final class Success<T> extends Actually<T> {
    /** The value. */
    private final T res;

    /**
     * Constructor for Success.
     *
     * @param res The value that was successful.
     */
    private Success(T res) {
      this.res = res;
    }

    /*
     * Get the string representation of this value.
     *
     * @return The string representation of this value.
     */
    @Override
    public String toString() {
      if (this.res == null) {
        return "<>";
      } else {
        return "<" + this.res.toString() + ">";
      }
    }

    /**
     * Compare if this object is equals to another object.
     *
     * @param x The other object to be compared to.
     * @return True if equal, else false.
     */
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

    /**
     * Returns the value.
     *
     * @return The unwrapped value.
     */
    @Override
    public T unwrap() {
      return this.res;
    }

    /**
     * Returns the value.
     *
     * @return The unwrapped value.
     */
    @Override
    public T except(Constant<? extends T> x) {
      return this.res;
    }

    /**
     * Perform a action on the value.
     *
     * @param x The action to perform.
     */
    @Override
    public void finish(Action<? super T> x) {
      x.call(this.res);
    }

    /**
     * Returns the value.
     *
     * @return The unwrapped value.
     */
    @Override
    public T unless(T x) {
      return this.res;
    }

    /**
     * Tries to mutate the value using the provided Immutator.
     *
     * @param <R> The type of the mutated value.
     * @param x The Immutator.
     * @return The new mutated wrapped value or a Failure instance.
     */
    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super T> x) {
      try {
        return Actually.<R>ok(x.invoke(this.res));
      } catch (Exception e) {
        return Actually.err(e);
      }
    }

    /**
     * Perform a action on the value, do nothing if the value is null.
     *
     * @param action The action to be performed.
     */
    @Override
    public void act(Action<? super T> action) {
      if (this.res == null) {
        return;
      }
      action.call(this.res);
    }

    /**
     * Tries to mutate the value using the provided Immutator that returns a wrapped value.
     *
     * @param <R> The type of the mutated value.
     * @param x The Immutator.
     * @return The new mutated wrapped value or a Failure instance.
     */
    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<R>, ? super T> x) {
      try {
        return x.invoke(this.res);
      } catch (Exception e) {
        return Actually.err(e);
      }
    }
  }

  /** Static nested class to represent failure. */
  private static final class Failure extends Actually<Object> {
    /** The exception. */
    private final Exception exc;

    /**
     * The constructor for Failure.
     *
     * @param exc The exception.
     */
    private Failure(Exception exc) {
      this.exc = exc;
    }

    /*
     * Get the string representation of this value.
     *
     * @return The string representation of this value.
     */
    @Override
    public String toString() {
      return "[" + this.exc.getClass().getCanonicalName() + "] " + this.exc.getMessage();
    }

    /**
     * Compare if this object is equals to another object.
     *
     * @param x The other object to be compared to.
     * @return True if equal, else false.
     */
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

    /**
     * Throw the stored exception.
     *
     * @throws Exception The stored exception.
     */
    @Override
    public Object unwrap() throws Exception {
      throw this.exc;
    }

    /**
     * Return the value of the constant.
     *
     * @param x The constant.
     * @return The value of the constant.
     */
    @Override
    public Object except(Constant<? extends Object> x) {
      return x.init();
    }

    /**
     * Do nothing.
     *
     * @param x A action.
     */
    @Override
    public void finish(Action<Object> x) {}

    /**
     * Return the object passed in.
     *
     * @param x The object to be returned.
     * @return x
     */
    @Override
    public Object unless(Object x) {
      return x;
    }

    /**
     * Return a new Failure with the stored exception.
     *
     * @param <R> The type of the mutated value.
     * @param x The Immutator to mutate the value.
     * @return The new Failure instance.
     */
    @Override
    public <R> Actually<R> transform(Immutator<? extends R, ? super Object> x) {
      return Actually.err(this.exc);
    }

    /**
     * Do nothing.
     *
     * @param action A action.
     */
    @Override
    public void act(Action<? super Object> action) {}

    /**
     * Return a new Failure with the stored exception.
     *
     * @param <R> The type of the mutated value.
     * @param x The Immutator to mutate the value.
     * @return The new Failure instance.
     */
    @Override
    public <R> Actually<R> next(Immutator<? extends Actually<R>, Object> x) {
      return Actually.err(this.exc);
    }
  }
}
