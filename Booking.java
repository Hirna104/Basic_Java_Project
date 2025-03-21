
class Booking {
    // private instance variables
    private String bookingName;
    private int nightsStay;
    
    // constants
    // private static final int MAX_BOOKINGS = 10;
    // private static final int MIN_NIGHTS = 1;
    private static final int DISCOUNT_1 = 7;
    private static final double DISCOUNT_1_VALUE = 0.10;
    private static final int DISCOUNT_2 = 10;
    private static final double DISCOUNT_2_VALUE = 0.15;
    private static final double ONE_NIGHT_CHARGE = 89.95;
    private static final double CLEANING_CHARGE = 20;

    /**
     * Default constructor
     */
    Booking() {
        bookingName = "";
        nightsStay = 0;
    }

    /**
     * Parameterised constructor
     * @param bookingName The name of the booking
     * @param nightsStay The length of the booking
     */
    public Booking(String bookingName, int nightsStay) {
        this.bookingName = bookingName;
        this.nightsStay = nightsStay;
    }

    /**
     * Setter for the booking name
     * @param bookingName The name of the booking
     */
    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    /**
     * Setter for the length of the booking
     * @param nightsStay The length of the booking
     */
    public void setNightsStay(int nightsStay) {
        this.nightsStay = nightsStay;
    }

    /**
     * Getter for the booking name
     * @return The name of the booking
     */
    public String getBookingName() {
        return bookingName;
    }

    /**
     * Getter for the length of the booking
     * @return The length of the booking
     */
    public int getNightsStay() {
        return nightsStay;
    }

    /**
     * Calculates the charge for the booking
     * @return The charge for the booking
     */
    public double calculateCharge() {
        double discount = 0.00;

        if (nightsStay > DISCOUNT_2) {
            discount = (ONE_NIGHT_CHARGE * DISCOUNT_2_VALUE) * nightsStay;
        } else if (nightsStay > DISCOUNT_1) {
            discount = (ONE_NIGHT_CHARGE * DISCOUNT_1_VALUE) * nightsStay;
        }

        return (ONE_NIGHT_CHARGE * nightsStay) + CLEANING_CHARGE - discount;
    }

    /**
     * Overridden toString method
     * @return The formatted booking record
     */
    @Override
    public String toString() {
        return String.format("%-30s%-11d$%1.2f\n", bookingName, nightsStay, calculateCharge()); 
    }

}