class Counter {
  private boolean available;
  private final int id;

  public Counter(int id) {
    this.available = true;
    this.id = id;
  }

  @Override
  public String toString() {
    return "S" + this.id;
  }

  public int getId() {
    return this.id;
  }

  public void setUnavailable() {
    this.available = false;
  }

  public void setAvailable() {
    this.available = true;
  }

  public boolean isAvailable() {
    return this.available;
  }
}
