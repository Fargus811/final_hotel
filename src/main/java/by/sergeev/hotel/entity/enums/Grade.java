package by.sergeev.hotel.entity.enums;

public enum Grade {

    ELITE(0), STANDART(1), ECONOMY(2);
    private int id;

    Grade(int id) {
        this.id = id;
    }

    public static Grade getGrade(int id) {
        if (id == 0) {
            return ELITE;
        } else if (id == 1) {
            return STANDART;
        } else {
            return ECONOMY;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
