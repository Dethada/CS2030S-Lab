package cs2030s.fp;

public abstract class Actually<T> {

    public static <T> Success<T> ok(T res) {
        return (Success<T>) new Success<>(res);
    }

    public static Failure err(Exception exc) {
        return new Failure(exc);
    }

    final private static class Success<T> extends Actually<T> {
        private final T res;

        Success(T res) {
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

    final private static class Failure extends Actually<Object> {
        private final Exception exc;

        Failure(Exception exc) {
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
                if (this.exc.getMessage() == some.exc.getMessage()) {
                    return true;
                }
                if (this.exc == null || some.exc == null) {
                    return false;
                }
                // TODO
                return this.exc.equals(some.exc);
            }
            return false;
        }
    }
}