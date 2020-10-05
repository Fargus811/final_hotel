package by.sergeev.hotel.entity.enums;

public enum Role {

    USER(0), ADMIN(1);

    private int id;

    Role(int id) {
        this.id = id;
    }

    public static Role getRole(int id) {
        return (id == 1) ? ADMIN : USER;
    }

    public Integer getId() {
        return id;
    }
}
