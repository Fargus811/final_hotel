package by.sergeev.hotel.entity;

public enum Role {

    USER(0), ADMIN(1);

    private int id;

    Role(int id) {
        this.id = id;
    }

    public static Role getRole(int bit) {
        return  (bit == 1) ? ADMIN : USER;
    }

    public Integer getId() {
        return id;
    }
}
