package by.sergeev.hotel.controller.command;

import by.sergeev.hotel.controller.command.booking.edit.AddRoomToBookingCommand;
import by.sergeev.hotel.controller.command.booking.edit.ChangeBookingStatusCommand;
import by.sergeev.hotel.controller.command.booking.edit.CreateBookingCommand;
import by.sergeev.hotel.controller.command.booking.show.ShowAllBookingsCommand;
import by.sergeev.hotel.controller.command.booking.show.ShowUserBookingsCommand;
import by.sergeev.hotel.controller.command.room.ShowAllRoomsCommand;
import by.sergeev.hotel.controller.command.room.ShowFreeRoomByConditionCommand;
import by.sergeev.hotel.controller.command.user.edit.*;
import by.sergeev.hotel.controller.command.user.show.ShowMyProfileCommand;
import by.sergeev.hotel.controller.command.user.show.ShowProfileSettingsCommand;

public enum CommandType {

    SHOW_ALL_ROOMS(new ShowAllRoomsCommand()),
    SHOW_ALL_BOOKINGS(new ShowAllBookingsCommand()),
    SHOW_USER_BOOKINGS(new ShowUserBookingsCommand()),
    SHOW_MY_PROFILE(new ShowMyProfileCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    LANGUAGE(new ChangeLanguageCommand()),
    ADD_BALANCE(new AddUserBalanceCommand()),
    CREATE_BOOKING(new CreateBookingCommand()),
    CHANGE_BOOKING_STATUS(new ChangeBookingStatusCommand()),
    SHOW_PROFILE_SETTINGS(new ShowProfileSettingsCommand()),
    SHOW_FREE_ROOM_BY_CONDITION(new ShowFreeRoomByConditionCommand()),
    ADD_ROOM_TO_BOOIKING(new AddRoomToBookingCommand());


    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
