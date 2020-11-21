package by.sergeev.hotel.controller.command;

public class PageParameter {

    //USER COMMAND
    public static final String USER = "user";
    public static final String USER_ID = "userId";
    public static final String ACCOUNT_STATUS = "accountStatusId";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String SESSION_USER = "sessionUser";
    public static final String AMOUNT = "amount";
    public static final String USERS = "users";
    public static final String DELETE = "DELETE";
    public static final String OLD_PASSWORD = "oldPassword";
    public static final String NEW_PASSWORD = "newPassword";

    //BOOKING COMMAND
    public static final String BOOKINGS = "bookings";
    public static final String BOOKING_STATUS = "bookingStatus";
    public static final String BOOKING_ID = "bookingId";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String COST = "cost";
    public static final String MAX_PERSONS = "maxPersons";
    public static final String NUMBER_OF_BEDS = "numberOfBeds";
    public static final String NUMBER_OF_ROOMS = "numberOfRooms";
    public static final String GRADE_ID = "gradeId";
    public static final String HAS_WIFI = "hasWifi";
    public static final String HAS_TV = "hasTV";
    public static final String HAS_BATHROOM = "hasBathroom";
    //ROOM COMMAND
    public static final String ROOMS = "rooms";
    public static final String ROOM = "room";
    public static final String ROOM_NAME = "roomName";
    public static final String ROOM_DESCRIPTION = "roomDescription";
    public static final String ROOM_COST = "roomCost";
    public static final String ROOM_ID = "roomId";
    //LOCALE
    public static final String LOCALE = "locale";
    public static final String VALUE_OF_LOCALE = "ru";
    public static final String LOCALE_PARAM = "lang";
    //ERROR
    public static final String ERROR = "error";
    public static final String ERROR_INFO = "errorInfo";
    public static final String ERROR_BAN = "errorBan";
    public static final String ERROR_EMAIL = "errorEmail";
    public static final String ERROR_PARAMETERS = "errorPass";
    //IMAGE CONTROLLER
    public static final String DOWNLOAD_STATUS = "imageStatus";

    private PageParameter(){
    }
}
