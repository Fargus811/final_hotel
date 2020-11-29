package by.sergeev.hotel.controller.command;

import by.sergeev.hotel.controller.command.booking.edit.*;
import by.sergeev.hotel.controller.command.booking.show.SeeDetailsOfBookingCommand;
import by.sergeev.hotel.controller.command.booking.show.ShowAllBookingsCommand;
import by.sergeev.hotel.controller.command.booking.show.ShowUserBookingsCommand;
import by.sergeev.hotel.controller.command.passing.PassingToCreateBookingCommand;
import by.sergeev.hotel.controller.command.passing.PassingToCreateRoom;
import by.sergeev.hotel.controller.command.passing.PassingToLoginCommand;
import by.sergeev.hotel.controller.command.passing.PassingToRegistrationCommand;
import by.sergeev.hotel.controller.command.room.edit.DeleteRoomCommand;
import by.sergeev.hotel.controller.command.room.edit.UpdateRoomInfoCommand;
import by.sergeev.hotel.controller.command.room.show.ShowAllRoomsCommand;
import by.sergeev.hotel.controller.command.room.show.ShowFreeRoomsByConditionCommand;
import by.sergeev.hotel.controller.command.room.edit.CreateRoomCommand;
import by.sergeev.hotel.controller.command.room.show.ShowRoomToUpdateInfoCommand;
import by.sergeev.hotel.controller.command.user.edit.*;
import by.sergeev.hotel.controller.command.user.show.ShowAllUsersCommand;
import by.sergeev.hotel.controller.command.user.show.ShowMyProfileCommand;
import by.sergeev.hotel.controller.command.user.show.ShowProfileSettingsCommand;

public enum CommandType {

    PASS_TO_LOGIN(new PassingToLoginCommand()),
    PASS_TO_REGISTRATION(new PassingToRegistrationCommand()),
    PASS_TO_CREATE_BOOKING(new PassingToCreateBookingCommand()),
    PASS_TO_CREATE_ROOM(new PassingToCreateRoom()),
    SHOW_ALL_ROOMS(new ShowAllRoomsCommand()),
    SHOW_ALL_BOOKINGS(new ShowAllBookingsCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    SHOW_USER_BOOKINGS(new ShowUserBookingsCommand()),
    SHOW_MY_PROFILE(new ShowMyProfileCommand()),
    REGISTRATION(new RegistrationCommand()),
    ACTIVATE_ACCOUNT(new ActivateAccountAfterRegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    UPDATE_USER_INFO(new UpdateUserInformationCommand()),
    UPDATE_USER_PASSWORD(new UpdateUserPasswordCommand()),
    CHANGE_ACCOUNT_STATUS(new ChangeAccountStatusCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    ADD_BALANCE(new AddUserBalanceCommand()),
    PAY_FOR_BOOKING(new PayForBookingCommand()),
    CREATE_BOOKING(new CreateBookingCommand()),
    CREATE_ROOM(new CreateRoomCommand()),
    CHANGE_BOOKING_STATUS(new ChangeBookingStatusCommand()),
    SHOW_PROFILE_SETTINGS(new ShowProfileSettingsCommand()),
    DELETE_ROOM_FROM_BOOKING(new DeleteRoomFromBookingCommand()),
    SEE_DETAILS_OF_BOOKING(new SeeDetailsOfBookingCommand()),
    SHOW_FREE_ROOM_BY_CONDITION(new ShowFreeRoomsByConditionCommand()),
    SHOW_ROOM_TO_UPDATE_INFO(new ShowRoomToUpdateInfoCommand()),
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
