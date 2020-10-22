package by.sergeev.hotel.entity.enums;

public enum RoomGrade {

    ЭЛИТНЫЙ(1), СТАНДАРТНЫЙ(2), ЭКОНОМ(3), ПРЕМИУМ(4);

    private int id;

    RoomGrade(int id) {
        this.id = id;
    }

    public static RoomGrade getRoomGrade(int id) {
        if (id == 1) {
            return ЭЛИТНЫЙ ;
        } else if (id == 2) {
            return СТАНДАРТНЫЙ;
        } else if (id == 3 ){
            return ЭКОНОМ;
        }else {
            return ПРЕМИУМ;
        }
    }

    public int getId() {
        return id;
    }

}
