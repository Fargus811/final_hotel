package by.sergeev.hotel.entity;

public class RoomPhoto {
    private int id;
    private String path_to_photo;
    private Room room;

    public RoomPhoto() {
    }

    public RoomPhoto(int id, String path_to_photo, Room room) {
        this.id = id;
        this.path_to_photo = path_to_photo;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath_to_photo() {
        return path_to_photo;
    }

    public void setPath_to_photo(String path_to_photo) {
        this.path_to_photo = path_to_photo;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomPhoto)) return false;

        RoomPhoto roomPhoto = (RoomPhoto) o;

        if (id != roomPhoto.id) return false;
        if (path_to_photo != null ? !path_to_photo.equals(roomPhoto.path_to_photo) : roomPhoto.path_to_photo != null)
            return false;
        return room != null ? room.equals(roomPhoto.room) : roomPhoto.room == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (path_to_photo != null ? path_to_photo.hashCode() : 0);
        result = 31 * result + (room != null ? room.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoomPhoto{");
        sb.append("id=").append(id);
        sb.append(", path_to_photo='").append(path_to_photo).append('\'');
        sb.append(", room=").append(room);
        sb.append('}');
        return sb.toString();
    }
}
