package by.sergeev.hotel.entity.enums;

public enum BookingStatus {
    IN_PROCESS(0), WAITING_FOR_USER(1), ACCEPTED(2);

    private int id;

    BookingStatus(int id) {
        this.id = id;
    }

    public static BookingStatus getBookingStatus(int id) {
        if (id == 0) {
            return IN_PROCESS;
        } else if (id == 1) {
            return WAITING_FOR_USER;
        } else {
            return ACCEPTED;
        }
    }

    public int getId() {
        return id;
    }


}
