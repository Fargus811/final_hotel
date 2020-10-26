package by.sergeev.hotel.controller.command;

import by.sergeev.hotel.controller.command.booking.ChangeBookingStatusCommand;
import by.sergeev.hotel.controller.command.booking.CreateBookingCommand;
import by.sergeev.hotel.controller.command.booking.ShowAllUserBookingsCommand;
import by.sergeev.hotel.controller.command.room.ShowAllRoomsCommand;
import by.sergeev.hotel.controller.command.user.edit.*;
import by.sergeev.hotel.controller.command.user.show.ShowMyProfileCommand;

public enum CommandType {

    SHOW_ALL_ROOMS(new ShowAllRoomsCommand()),
    SHOW_USER_BOOKINGS(new ShowAllUserBookingsCommand()),
    SHOW_MY_PROFILE(new ShowMyProfileCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    LANGUAGE(new ChangeLanguageCommand()),
    ADD_BALANCE(new AddUserBalanceCommand()),
    CREATE_BOOKING(new CreateBookingCommand()),
    CHANGE_BOOKING_STATUS(new ChangeBookingStatusCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
