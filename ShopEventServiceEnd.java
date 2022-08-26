
/**
 * TODO
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceEnd extends ShopEvent {
    public ShopEventServiceEnd(double time, int customerId,int counterId, boolean[] available) {
        super(time, customerId, counterId, available);
    }

    @Override
    public String toString() {
      return super.toString() + String.format(": Customer %d service done (by Counter %d)",
          this.getCustomerID(), this.counterId);
    }

    @Override
    public Event[] simulate() {
      // The current event is a service-end event.
      // Mark the counter is available, then schedule
      // a departure event at the current time.
      this.available[this.counterId] = true;
      return new Event[] {
        new ShopEventDeparture(this.getTime(), this.getCustomerID()),
      };
    }
}
