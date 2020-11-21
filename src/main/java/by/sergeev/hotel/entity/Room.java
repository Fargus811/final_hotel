package by.sergeev.hotel.entity;

import by.sergeev.hotel.entity.enums.RoomGrade;

import java.io.Serializable;
import java.math.BigDecimal;

public class Room implements Serializable {

    private long id;
    private String name;
    private int numberOfRooms;
    private int maxPersons;
    private BigDecimal cost;
    private boolean hasWifi;
    private boolean hasTV;
    private boolean hasBathroom;
    private int numberOfBeds;
    private String description;
    private String photoPath;
    private RoomGrade roomGrade;

    public Room() {
    }

    public Room(long id, String name, int numberOfRooms, int maxPersons, BigDecimal cost, boolean hasWifi,
                boolean hasTV, boolean hasBathroom, int numberOfBeds, String description, String photoPath, RoomGrade roomGrade) {
        this.id = id;
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.maxPersons = maxPersons;
        this.cost = cost;
        this.hasWifi = hasWifi;
        this.hasTV = hasTV;
        this.hasBathroom = hasBathroom;
        this.numberOfBeds = numberOfBeds;
        this.description = description;
        this.photoPath = photoPath;
        this.roomGrade = roomGrade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isHasBathroom() {
        return hasBathroom;
    }

    public void setHasBathroom(boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public RoomGrade getRoomGrade() {
        return roomGrade;
    }

    public void setRoomGrade(RoomGrade roomGrade) {
        this.roomGrade = roomGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (numberOfRooms != room.numberOfRooms) return false;
        if (maxPersons != room.maxPersons) return false;
        if (hasWifi != room.hasWifi) return false;
        if (hasTV != room.hasTV) return false;
        if (hasBathroom != room.hasBathroom) return false;
        if (numberOfBeds != room.numberOfBeds) return false;
        if (name != null ? !name.equals(room.name) : room.name != null) return false;
        if (cost != null ? !cost.equals(room.cost) : room.cost != null) return false;
        if (description != null ? !description.equals(room.description) : room.description != null) return false;
        if (photoPath != null ? !photoPath.equals(room.photoPath) : room.photoPath != null) return false;
        return roomGrade == room.roomGrade;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + numberOfRooms;
        result = 31 * result + maxPersons;
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (hasWifi ? 1 : 0);
        result = 31 * result + (hasTV ? 1 : 0);
        result = 31 * result + (hasBathroom ? 1 : 0);
        result = 31 * result + numberOfBeds;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
        result = 31 * result + (roomGrade != null ? roomGrade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", numberOfRooms=").append(numberOfRooms);
        sb.append(", maxPersons=").append(maxPersons);
        sb.append(", cost=").append(cost);
        sb.append(", hasWifi=").append(hasWifi);
        sb.append(", hasTV=").append(hasTV);
        sb.append(", hasBathroom=").append(hasBathroom);
        sb.append(", numberOfBeds=").append(numberOfBeds);
        sb.append(", description='").append(description).append('\'');
        sb.append(", photoPath='").append(photoPath).append('\'');
        sb.append(", roomGrade='").append(roomGrade).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
