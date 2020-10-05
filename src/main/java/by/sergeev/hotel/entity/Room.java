package by.sergeev.hotel.entity;

import by.sergeev.hotel.entity.enums.Grade;

public class Room {

    private int id;
    private String name;
    private int number_of_rooms;
    private int floor;
    private Grade grade;
    private int max_persons;
    private boolean has_TV;
    private boolean has_Wifi;
    private boolean has_bathroom;

    public Room() {
    }

    public Room(int id, String name, int number_of_rooms, int floor, Grade grade, int max_persons, boolean has_TV, boolean has_Wifi, boolean has_bathroom) {
        this.id = id;
        this.name = name;
        this.number_of_rooms = number_of_rooms;
        this.floor = floor;
        this.grade = grade;
        this.max_persons = max_persons;
        this.has_TV = has_TV;
        this.has_Wifi = has_Wifi;
        this.has_bathroom = has_bathroom;
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

    public int getNumber_of_rooms() {
        return number_of_rooms;
    }

    public void setNumber_of_rooms(int number_of_rooms) {
        this.number_of_rooms = number_of_rooms;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public int getMax_persons() {
        return max_persons;
    }

    public void setMax_persons(int max_persons) {
        this.max_persons = max_persons;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (number_of_rooms != room.number_of_rooms) return false;
        if (floor != room.floor) return false;
        if (max_persons != room.max_persons) return false;
        if (has_TV != room.has_TV) return false;
        if (has_Wifi != room.has_Wifi) return false;
        if (has_bathroom != room.has_bathroom) return false;
        if (name != null ? !name.equals(room.name) : room.name != null) return false;
        return grade == room.grade;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + number_of_rooms;
        result = 31 * result + floor;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + max_persons;
        result = 31 * result + (has_TV ? 1 : 0);
        result = 31 * result + (has_Wifi ? 1 : 0);
        result = 31 * result + (has_bathroom ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", number_of_rooms=").append(number_of_rooms);
        sb.append(", floor=").append(floor);
        sb.append(", grade=").append(grade);
        sb.append(", max_persons=").append(max_persons);
        sb.append(", has_TV=").append(has_TV);
        sb.append(", has_Wifi=").append(has_Wifi);
        sb.append(", has_bathroom=").append(has_bathroom);
        sb.append('}');
        return sb.toString();
    }
}
