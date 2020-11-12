package by.sergeev.hotel.entity;

import by.sergeev.hotel.entity.enums.BookingStatus;
import by.sergeev.hotel.entity.enums.RoomGrade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Booking implements Serializable {
    //TODO BigDecimal
    private int id;
    private String startDate;
    private String endDate;
    private BigDecimal cost;
    private int maxPersons;
    private int numberOfBeds;
    private int numberOfRooms;
    private int floor;
    private RoomGrade roomGrade;
    private boolean hasTV;
    private boolean hasWifi;
    private boolean hasBathroom;
    private int userId;
    private Room room;
    private BookingStatus bookingStatus;

    public Booking(int id, String startDate, String endDate, BigDecimal cost, int maxPersons, int numberOfBeds,
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

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public RoomGrade getRoomGrade() {
        return roomGrade;
    }

    public void setRoomGrade(RoomGrade roomGrade) {
        this.roomGrade = roomGrade;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public boolean isHasBathroom() {
        return hasBathroom;
    }

    public void setHasBathroom(boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
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
        if (floor != booking.floor) return false;
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
        int result = id;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + maxPersons;
        result = 31 * result + numberOfBeds;
        result = 31 * result + numberOfRooms;
        result = 31 * result + floor;
        result = 31 * result + (roomGrade != null ? roomGrade.hashCode() : 0);
        result = 31 * result + (hasTV ? 1 : 0);
        result = 31 * result + (hasWifi ? 1 : 0);
        result = 31 * result + (hasBathroom ? 1 : 0);
        result = 31 * result + userId;
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
        sb.append(", floor=").append(floor);
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
