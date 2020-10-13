package by.sergeev.hotel.entity;

import java.io.Serializable;

public class Room implements Serializable {

    private int id;
    private String name;
    private int numberOfRooms;
    private int floor;
    private int maxPersons;
    private double cost;
    private boolean hasWifi;
    private boolean hasTV;
    private boolean hasBathroom;
    private int numberOfBeds;
    private String description;
    private String photoPath;
    private String grade;

    public Room() {
    }

    public Room(int id, String name, int numberOfRooms, int floor, int maxPersons, double cost, boolean hasWifi,
                boolean hasTV, boolean hasBathroom, int numberOfBeds, String description, String photoPath, String grade) {
        this.id = id;
        this.name = name;
        this.numberOfRooms = numberOfRooms;
        this.floor = floor;
        this.maxPersons = maxPersons;
        this.cost = cost;
        this.hasWifi = hasWifi;
        this.hasTV = hasTV;
        this.hasBathroom = hasBathroom;
        this.numberOfBeds = numberOfBeds;
        this.description = description;
        this.photoPath = photoPath;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (numberOfRooms != room.numberOfRooms) return false;
        if (floor != room.floor) return false;
        if (maxPersons != room.maxPersons) return false;
        if (Double.compare(room.cost, cost) != 0) return false;
        if (hasWifi != room.hasWifi) return false;
        if (hasTV != room.hasTV) return false;
        if (hasBathroom != room.hasBathroom) return false;
        if (numberOfBeds != room.numberOfBeds) return false;
        if (name != null ? !name.equals(room.name) : room.name != null) return false;
        if (description != null ? !description.equals(room.description) : room.description != null) return false;
        if (photoPath != null ? !photoPath.equals(room.photoPath) : room.photoPath != null) return false;
        return grade != null ? grade.equals(room.grade) : room.grade == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + numberOfRooms;
        result = 31 * result + floor;
        result = 31 * result + maxPersons;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (hasWifi ? 1 : 0);
        result = 31 * result + (hasTV ? 1 : 0);
        result = 31 * result + (hasBathroom ? 1 : 0);
        result = 31 * result + numberOfBeds;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", numberOfRooms=").append(numberOfRooms);
        sb.append(", floor=").append(floor);
        sb.append(", maxPersons=").append(maxPersons);
        sb.append(", cost=").append(cost);
        sb.append(", hasWifi=").append(hasWifi);
        sb.append(", hasTV=").append(hasTV);
        sb.append(", hasBathroom=").append(hasBathroom);
        sb.append(", numberOfBeds=").append(numberOfBeds);
        sb.append(", description='").append(description).append('\'');
        sb.append(", photoPath='").append(photoPath).append('\'');
        sb.append(", grade='").append(grade).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
