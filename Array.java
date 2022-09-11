/**
 * The {@literal Array<T>} for CS2030S.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Array<T extends Comparable<T>> {
  /** The array to store the items. */
  private final T[] array;

  /** The length of the array. */
  private final int length;

  /**
   * Constructor for Array.
   *
   * @param size The size of the Array.
   */
  Array(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    final T[] tmp = (T[]) new Comparable[size];
    this.array = tmp;
    this.length = size;
  }

  /**
   * Store item at index.
   *
   * @param index The index to store the item at.
   * @param item The item to be stored.
   */
  public void set(int index, T item) {
    this.array[index] = item;
  }

  /**
   * Get item at index.
   *
   * @param index The index the item is stored at.
   * @return The item at the index.
   */
  public T get(int index) {
    return array[index];
  }

  /**
   * Get the smallest item stored in the array.
   *
   * @return The smallest item stored in the array.
   */
  public T min() {
    T min = this.array[0];
    for (int i = 1; i < this.array.length; i++) {
      if (min.compareTo(this.array[i]) > 0) {
        min = this.array[i];
      }
    }
    return min;
  }

  /**
   * Return the length of the array.
   *
   * @return the length of the array.
   */
  public int length() {
    return this.length;
  }

  /**
   * Returns the string representation of this array.
   *
   * @return A string representing this array.
   */
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
