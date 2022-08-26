/**
 * This class encapsulates an event in the shop
 * simulation. Your task is to replace this
 * class with new classes, following proper OOP principles.
 *
 * @author David
 * @version CS2030S AY21/22 Semester 2
 */
abstract class ShopEvent extends Event {
    /**
     * The id of a customer associated with this event.
     * First customer has id 0. Next is 1, 2, etc.
     */
    private int customerId;

    /**
     * An array to indicate if a service counter is
     * available. available[i] is true if and only
     * if service counter i is available to serve.
     */
    public boolean[] available;

    /**
     * The id of the counter associated with this event.
     * This field only matters if the event type if
     * SERVICE_BEGIN or SERVICE_END.
     */
    public int counterId;

    /**
     * Constructor for a shop event.
     *
     * @param time        The time this event occurs.
     * @param customerId  The customer associated with this
     *                    event.
     * @param serviceTime The time this customer takes
     *                    for service.
     * @param counterId   The id of the counter associated with
     *                    this event.
     * @param available   The indicator of which counter is
     *                    available.
     */
    public ShopEvent(double time, int customerId,
            int counterId, boolean[] available) {
        this(time, customerId);
        this.available = available;
        this.counterId = counterId;
    }

    /**
     * Constructor for a shop event.
     *
     * @param time        The time this event occurs.
     * @param customerId  The customer associated with this
     *                    event.
     * @param serviceTime The time this customer takes
     *                    for service.
     * @param available   The indicator of which counter is
     *                    available.
     */
    public ShopEvent(double time, int customerId,
            boolean[] available) {
        this(time, customerId);
        this.available = available;
    }

    /**
     * Constructor for a shop event.
     *
     * @param time       The time this event occurs.
     * @param customerId The customer associated with this
     *                   event.
     */
    public ShopEvent(double time, int customerId) {
        super(time);
        this.customerId = customerId;
    }

    public int getCustomerID() {
        return this.customerId;
    }
}
