/**
 * Class representing a Counter.
 *
 * @author David Zhu (Group 12B)
 * @version CS2030S AY22/23 Semester 1
 */
class Counter implements Comparable<Counter> {
  /*
   * Whether the counter is available, true for available, false for unavailable.
   */
  private Customer customer;

  /*
   * Id of the Counter.
   */
  private final int id;

  /*
   * The Counter's Queue.
   */
  private final Queue<Customer> queue;

  /**
   * Constructor for Counter.
   *
   * @param id The Id of the Counter.
   * @param queueLength The length of the counter queue.
   */
  public Counter(int id, int queueLength) {
    this.customer = null;
    this.id = id;
    this.queue = new Queue<Customer>(queueLength);
  }

  /**
   * Return the string representation this Counter.
   *
   * @return A string representation of the Counter Id.
   */
  @Override
  public String toString() {
    return "S" + this.id + " " + this.queue.toString();
  }

  /**
   * Set this counter to unavailable.
   *
   * @param c The customer to be served.
   * @return true if successful, false if failed.
   **/
  public boolean serveCustomer(Customer c) {
    if (this.isAvailable()) {
      this.customer = c;
      return true;
    }
    return false;
  }

  /** Set this counter to available. */
  public void finishService() {
    this.customer = null;
  }

  /**
   * Check if the counter is available.
   *
   * @return true if available, false if unavailable.
   */
  public boolean isAvailable() {
    return this.customer == null;
  }

  /**
   * Check if the counter queue is full.
   *
   * @return true if queue is full, false if not full.
   */
  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  /**
   * Return the Id of this Counter.
   *
   * @return the Id of this Counter.
   */
  public int getId() {
    return this.id;
  }

  public int queueLength() {
    return this.queue.length();
  }

  /**
   * Compare this event with a given event e.
   *
   * @param c The other counter to compare to.
   * @return 1 if this counter queue is longer;
   *         0 if they have the same length;
   *         -1 if this counter queue is shorter.
   */
  @Override
  public int compareTo(Counter c) {
    //     You should implement compareTo in such a way that counters.min() returns the counter
    // that a customer should join (unless all the counter queues have reached maximum length).
    int thisQueueLength = this.queue.length();
    int otherQueueLength = c.queueLength();
    if (thisQueueLength > otherQueueLength) {
      return 1;
    } else if (thisQueueLength < otherQueueLength) {
      return -1;
    }
    return 0;
  }

  /**
   * Enqueues the customer.
   *
   * @param c The customer that will join the counter queue.
   * @return true if success, false if fail (queue full).
   */
  public boolean joinQueue(Customer c) {
    return this.queue.enq(c);
  }

  public Customer deQueue() {
    return this.queue.deq();
  }
}
