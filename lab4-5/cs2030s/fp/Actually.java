package cs2030s.fp;

public abstract class Actually<T> {

    public static <T> Actually<T> ok(T res) {
        return (Success<T>) new Success<>(res);
    }

    public static Actually<Object> err(Exception exc) {
        return new Failure(exc);
    }

    public abstract boolean equals(Object x);

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
                // TODO
                return this.res.equals(some.res);
            }
            return false;
        }
    }

    private static final class Failure extends Actually<Object> {
        private final Exception exc;

        private Failure(Exception exc) {
            this.exc = exc;
        }

        @Override
        public String toString() {
            return "[" + this.exc.getClass() + "] " + this.exc.getMessage();
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
                // TODO
                return this.exc.equals(some.exc);
            }
            return false;
        }
    }
}
