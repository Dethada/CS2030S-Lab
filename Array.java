/**
 * The Array<T> for CS2030S
 *
 * @author David
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> {
  private final T[] array;

  private final int length;

  Array(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    final T[] tmp = (T[]) new Comparable[size];
    this.array = tmp;
    this.length = size;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return array[index];
  }

  public T min() {
    T min = this.array[0];
    for (int i = 1; i < this.array.length; i++) {
        if (min.compareTo(this.array[i]) > 0) {
            min = this.array[i];
        }
    }
    return min;
  }

  public int length() {
        return this.length;
    }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
