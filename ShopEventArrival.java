/**
 * TODO
 *
 * @author David
 * @version CS2030S AY22/23 Semester 1
 */
class ShopEventArrival extends ShopEvent {
    /**
     * The service time of the customer associated
     * this event. This field matters only if the
     * event type is ARRIVAL or SERVICE_BEGIN.
     */
    private double serviceTime;

    public ShopEventArrival(double time, int customerId, double serviceTime, boolean[] available) {
        super(time, customerId, available);
        this.serviceTime = serviceTime;
    }

    public ShopEventArrival(double time, int customerId, double serviceTime) {
        super(time, customerId);
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(": Customer %d arrives", this.getCustomerID());
    }

    @Override
    public Event[] simulate() {
        int counter = -1;
        for (int i = 0; i < this.available.length; i += 1) {
            if (this.available[i]) {
                counter = i;
                break;
            }
        }
        if (counter == -1) {
            // If no such counter can be found, the customer
            // should depart.
            return new Event[] {
                    new ShopEventDeparture(this.getTime(), this.getCustomerID())
            };
        } else {
            // Else, the customer should go the the first
            // available counter and get served.
            return new Event[] {
                    new ShopEventServiceBegin(this.getTime(), this.getCustomerID(),
                            this.serviceTime, counter, this.available)
            };
        }
    }
}
