package by.sergeev.hotel.entity;

import by.sergeev.hotel.entity.enums.BookingStatus;

import java.util.Date;

public class Booking {

    private int id;
    private Date start_date;
    private Date end_date;
    private int cost;
    private int max_persons;
    private int number_of_beds;
    private String grade;
    private boolean has_TV;
    private boolean has_Wifi;
    private boolean has_bathroom;
    private User user;
    private Room room;
    private BookingStatus bookingStatus;

    public Booking() {
    }

    public Booking(int id, Date start_date, Date end_date, int cost, int max_persons, int number_of_beds, String grade,
                   boolean has_TV, boolean has_Wifi, boolean has_bathroom, User user, Room room, BookingStatus bookingStatus) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.cost = cost;
        this.max_persons = max_persons;
        this.number_of_beds = number_of_beds;
        this.grade = grade;
        this.has_TV = has_TV;
        this.has_Wifi = has_Wifi;
        this.has_bathroom = has_bathroom;
        this.user = user;
        this.room = room;
        this.bookingStatus = bookingStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMax_persons() {
        return max_persons;
    }

    public void setMax_persons(int max_persons) {
        this.max_persons = max_persons;
    }

    public int getNumber_of_beds() {
        return number_of_beds;
    }

    public void setNumber_of_beds(int number_of_beds) {
        this.number_of_beds = number_of_beds;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isHas_TV() {
        return has_TV;
    }

    public void setHas_TV(boolean has_TV) {
        this.has_TV = has_TV;
    }

    public boolean isHas_Wifi() {
        return has_Wifi;
    }

    public void setHas_Wifi(boolean has_Wifi) {
        this.has_Wifi = has_Wifi;
    }

    public boolean isHas_bathroom() {
        return has_bathroom;
    }

    public void setHas_bathroom(boolean has_bathroom) {
        this.has_bathroom = has_bathroom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;

        Booking booking = (Booking) o;

        if (id != booking.id) return false;
        if (cost != booking.cost) return false;
        if (max_persons != booking.max_persons) return false;
        if (number_of_beds != booking.number_of_beds) return false;
        if (has_TV != booking.has_TV) return false;
        if (has_Wifi != booking.has_Wifi) return false;
        if (has_bathroom != booking.has_bathroom) return false;
        if (start_date != null ? !start_date.equals(booking.start_date) : booking.start_date != null) return false;
        if (end_date != null ? !end_date.equals(booking.end_date) : booking.end_date != null) return false;
        if (grade != null ? !grade.equals(booking.grade) : booking.grade != null) return false;
        if (user != null ? !user.equals(booking.user) : booking.user != null) return false;
        if (room != null ? !room.equals(booking.room) : booking.room != null) return false;
        return bookingStatus == booking.bookingStatus;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (start_date != null ? start_date.hashCode() : 0);
        result = 31 * result + (end_date != null ? end_date.hashCode() : 0);
        result = 31 * result + cost;
        result = 31 * result + max_persons;
        result = 31 * result + number_of_beds;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (has_TV ? 1 : 0);
        result = 31 * result + (has_Wifi ? 1 : 0);
        result = 31 * result + (has_bathroom ? 1 : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (bookingStatus != null ? bookingStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Booking{");
        sb.append("id=").append(id);
        sb.append(", start_date=").append(start_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", cost=").append(cost);
        sb.append(", max_persons=").append(max_persons);
        sb.append(", number_of_beds=").append(number_of_beds);
        sb.append(", grade='").append(grade).append('\'');
        sb.append(", has_TV=").append(has_TV);
        sb.append(", has_Wifi=").append(has_Wifi);
        sb.append(", has_bathroom=").append(has_bathroom);
        sb.append(", user=").append(user);
        sb.append(", room=").append(room);
        sb.append(", bookingStatus=").append(bookingStatus);
        sb.append('}');
        return sb.toString();
    }
}
