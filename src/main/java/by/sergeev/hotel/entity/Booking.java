package by.sergeev.hotel.entity;

import by.sergeev.hotel.entity.enums.BookingStatus;
import by.sergeev.hotel.entity.enums.RoomGrade;

import java.math.BigDecimal;

/**
 * The type Booking.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class Booking implements Entity {

    private long id;
    private String startDate;
    private String endDate;
    private BigDecimal cost;
    private int maxPersons;
    private int numberOfBeds;
    private int numberOfRooms;
    private RoomGrade roomGrade;
    private boolean hasTV;
    private boolean hasWifi;
    private boolean hasBathroom;
    private long userId;
    private Room room;
    private BookingStatus bookingStatus;

    /**
     * Instantiates a new Booking.
     *
     * @param id            the id
     * @param startDate     the start date
     * @param endDate       the end date
     * @param cost          the cost
     * @param maxPersons    the max persons
     * @param numberOfBeds  the number of beds
     * @param roomGrade     the room grade
     * @param hasTV         the has tv
     * @param hasWifi       the has wifi
     * @param hasBathroom   the has bathroom
     * @param userId        the user id
     * @param room          the room
     * @param bookingStatus the booking status
     * @param numberOfRooms the number of rooms
     */
    public Booking(long id, String startDate, String endDate, BigDecimal cost, int maxPersons, int numberOfBeds,
                   RoomGrade roomGrade, boolean hasTV, boolean hasWifi, boolean hasBathroom, int userId, Room room,
                   BookingStatus bookingStatus, int numberOfRooms) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.maxPersons = maxPersons;
        this.numberOfBeds = numberOfBeds;
        this.roomGrade = roomGrade;
        this.hasTV = hasTV;
        this.hasWifi = hasWifi;
        this.hasBathroom = hasBathroom;
        this.userId = userId;
        this.room = room;
        this.bookingStatus = bookingStatus;
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Instantiates a new Booking.
     */
    public Booking() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Gets max persons.
     *
     * @return the max persons
     */
    public int getMaxPersons() {
        return maxPersons;
    }

    /**
     * Sets max persons.
     *
     * @param maxPersons the max persons
     */
    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    /**
     * Gets number of beds.
     *
     * @return the number of beds
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets number of beds.
     *
     * @param numberOfBeds the number of beds
     */
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * Gets room grade.
     *
     * @return the room grade
     */
    public RoomGrade getRoomGrade() {
        return roomGrade;
    }

    /**
     * Sets room grade.
     *
     * @param roomGrade the room grade
     */
    public void setRoomGrade(RoomGrade roomGrade) {
        this.roomGrade = roomGrade;
    }

    /**
     * Is has tv boolean.
     *
     * @return the boolean
     */
    public boolean isHasTV() {
        return hasTV;
    }

    /**
     * Sets has tv.
     *
     * @param hasTV the has tv
     */
    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    /**
     * Is has wifi boolean.
     *
     * @return the boolean
     */
    public boolean isHasWifi() {
        return hasWifi;
    }

    /**
     * Sets has wifi.
     *
     * @param hasWifi the has wifi
     */
    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    /**
     * Is has bathroom boolean.
     *
     * @return the boolean
     */
    public boolean isHasBathroom() {
        return hasBathroom;
    }

    /**
     * Sets has bathroom.
     *
     * @param hasBathroom the has bathroom
     */
    public void setHasBathroom(boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets room.
     *
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets room.
     *
     * @param room the room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Gets booking status.
     *
     * @return the booking status
     */
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    /**
     * Sets booking status.
     *
     * @param bookingStatus the booking status
     */
    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    /**
     * Gets number of rooms.
     *
     * @return the number of rooms
     */
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Sets number of rooms.
     *
     * @param numberOfRooms the number of rooms
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;

        if (id != booking.id) return false;
        if (maxPersons != booking.maxPersons) return false;
        if (numberOfBeds != booking.numberOfBeds) return false;
        if (numberOfRooms != booking.numberOfRooms) return false;
        if (hasTV != booking.hasTV) return false;
        if (hasWifi != booking.hasWifi) return false;
        if (hasBathroom != booking.hasBathroom) return false;
        if (userId != booking.userId) return false;
        if (startDate != null ? !startDate.equals(booking.startDate) : booking.startDate != null) return false;
        if (endDate != null ? !endDate.equals(booking.endDate) : booking.endDate != null) return false;
        if (cost != null ? !cost.equals(booking.cost) : booking.cost != null) return false;
        if (roomGrade != booking.roomGrade) return false;
        if (room != null ? !room.equals(booking.room) : booking.room != null) return false;
        return bookingStatus == booking.bookingStatus;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + maxPersons;
        result = 31 * result + numberOfBeds;
        result = 31 * result + numberOfRooms;
        result = 31 * result + (roomGrade != null ? roomGrade.hashCode() : 0);
        result = 31 * result + (hasTV ? 1 : 0);
        result = 31 * result + (hasWifi ? 1 : 0);
        result = 31 * result + (hasBathroom ? 1 : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (bookingStatus != null ? bookingStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Booking{");
        sb.append("id=").append(id);
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", cost=").append(cost);
        sb.append(", maxPersons=").append(maxPersons);
        sb.append(", numberOfBeds=").append(numberOfBeds);
        sb.append(", numberOfRooms=").append(numberOfRooms);
        sb.append(", roomGrade=").append(roomGrade);
        sb.append(", hasTV=").append(hasTV);
        sb.append(", hasWifi=").append(hasWifi);
        sb.append(", hasBathroom=").append(hasBathroom);
        sb.append(", userId=").append(userId);
        sb.append(", room=").append(room);
        sb.append(", bookingStatus=").append(bookingStatus);
        sb.append('}');
        return sb.toString();
    }
}
