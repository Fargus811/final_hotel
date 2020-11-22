package by.sergeev.hotel.controller.command;

import by.sergeev.hotel.controller.command.booking.edit.AddRoomToBookingCommand;
import by.sergeev.hotel.controller.command.booking.show.CalculateBookingCostCommand;
import by.sergeev.hotel.controller.command.booking.edit.ChangeBookingStatusCommand;
import by.sergeev.hotel.controller.command.booking.edit.CreateBookingCommand;
import by.sergeev.hotel.controller.command.booking.show.ShowAllBookingsCommand;
import by.sergeev.hotel.controller.command.booking.show.ShowUserBookingsCommand;
import by.sergeev.hotel.controller.command.room.edit.DeleteRoomCommand;
import by.sergeev.hotel.controller.command.room.edit.UpdateRoomImageCommand;
import by.sergeev.hotel.controller.command.room.edit.UpdateRoomInfoCommand;
import by.sergeev.hotel.controller.command.room.show.ShowAllRoomsCommand;
import by.sergeev.hotel.controller.command.room.show.ShowFreeRoomByConditionCommand;
import by.sergeev.hotel.controller.command.room.edit.CreateRoomCommand;
import by.sergeev.hotel.controller.command.room.show.ShowRoomToUpdateInfoCommand;
import by.sergeev.hotel.controller.command.room.show.ShowRoomToUpdatePhotoCommand;
import by.sergeev.hotel.controller.command.user.edit.*;
import by.sergeev.hotel.controller.command.user.show.ShowAllUsersCommand;
import by.sergeev.hotel.controller.command.user.show.ShowMyProfileCommand;
import by.sergeev.hotel.controller.command.user.show.ShowProfileSettingsCommand;

public enum CommandType {

    SHOW_ALL_ROOMS(new ShowAllRoomsCommand()),
    SHOW_ALL_BOOKINGS(new ShowAllBookingsCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    SHOW_USER_BOOKINGS(new ShowUserBookingsCommand()),
    SHOW_MY_PROFILE(new ShowMyProfileCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    UPDATE_USER_INFO(new UpdateUserInformationCommand()),
    UPDATE_USER_PASSWORD(new UpdateUserPasswordCommand()),
    DELETE_ACCOUNT_BY_USER(new DeleteAccountCommand()),
    CHANGE_ACCOUNT_STATUS(new ChangeAccountStatusCommand()),
    LANGUAGE(new ChangeLanguageCommand()),
    ADD_BALANCE(new AddUserBalanceCommand()),
    CREATE_BOOKING(new CreateBookingCommand()),
    CREATE_ROOM(new CreateRoomCommand()),
    CHANGE_BOOKING_STATUS(new ChangeBookingStatusCommand()),
    SHOW_PROFILE_SETTINGS(new ShowProfileSettingsCommand()),
    CALCULATE_BOOKING_COST(new CalculateBookingCostCommand()),
    SHOW_FREE_ROOM_BY_CONDITION(new ShowFreeRoomByConditionCommand()),
    SHOW_ROOM_TO_UPDATE_INFO(new ShowRoomToUpdateInfoCommand()),
    SHOW_ROOM_TO_UPDATE_PHOTO(new ShowRoomToUpdatePhotoCommand()),
    UPDATE_ROOM_IMAGE(new UpdateRoomImageCommand()),
    UPDATE_ROOM_INFO(new UpdateRoomInfoCommand()),
    DELETE_ROOM(new DeleteRoomCommand()),
    ADD_ROOM_TO_BOOKING(new AddRoomToBookingCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
