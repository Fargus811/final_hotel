package by.sergeev.hotel.entity;

import by.sergeev.hotel.entity.enums.RoomGrade;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Room.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class Room implements Entity {

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

    /**
     * Instantiates a new Room.
     */
    public Room() {
    }

    /**
     * Instantiates a new Room.
     *
     * @param id            the id
     * @param name          the name
     * @param numberOfRooms the number of rooms
     * @param maxPersons    the max persons
     * @param cost          the cost
     * @param hasWifi       the has wifi
     * @param hasTV         the has tv
     * @param hasBathroom   the has bathroom
     * @param numberOfBeds  the number of beds
     * @param description   the description
     * @param photoPath     the photo path
     * @param roomGrade     the room grade
     */
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets photo path.
     *
     * @return the photo path
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * Sets photo path.
     *
     * @param photoPath the photo path
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
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
