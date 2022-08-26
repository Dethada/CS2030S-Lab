/**
 * TODO
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventServiceBegin extends ShopEvent {
    /**
     * The service time of the customer associated
     * this event. This field matters only if the
     * event type is ARRIVAL or SERVICE_BEGIN.
     */
    private double serviceTime;

    public ShopEventServiceBegin(double time, int customerId, double serviceTime, int counterId, boolean[] available) {
        super(time, customerId, counterId, available);
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": Customer %d service begin (by Counter %d)",
                this.getCustomerID(), this.counterId);
    }

    @Override
    public Event[] simulate() {
        // The current event is a service-begin event.
        // Mark the counter is unavailable, then schedule
        // a service-end event at the current time + service time.
        this.available[this.counterId] = false;
        double endTime = this.getTime() + this.serviceTime;
        return new Event[] {
                new ShopEventServiceEnd(endTime, this.getCustomerID(),
                        this.counterId, this.available)
        };
    }
}
