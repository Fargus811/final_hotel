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

/**
 * The enum Command type.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public enum CommandType {

    /**
     * The Pass to login.
     */
    PASS_TO_LOGIN(new PassingToLoginCommand()),
    /**
     * The Pass to registration.
     */
    PASS_TO_REGISTRATION(new PassingToRegistrationCommand()),
    /**
     * The Pass to create booking.
     */
    PASS_TO_CREATE_BOOKING(new PassingToCreateBookingCommand()),
    /**
     * The Pass to create room.
     */
    PASS_TO_CREATE_ROOM(new PassingToCreateRoom()),
    /**
     * The Show all rooms.
     */
    SHOW_ALL_ROOMS(new ShowAllRoomsCommand()),
    /**
     * The Show all bookings.
     */
    SHOW_ALL_BOOKINGS(new ShowAllBookingsCommand()),
    /**
     * The Show all users.
     */
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    /**
     * The Show user bookings.
     */
    SHOW_USER_BOOKINGS(new ShowUserBookingsCommand()),
    /**
     * The Show my profile.
     */
    SHOW_MY_PROFILE(new ShowMyProfileCommand()),
    /**
     * The Registration.
     */
    REGISTRATION(new RegistrationCommand()),
    /**
     * The Activate account.
     */
    ACTIVATE_ACCOUNT(new ActivateAccountAfterRegistrationCommand()),
    /**
     * The Login.
     */
    LOGIN(new LoginCommand()),
    /**
     * The Logout.
     */
    LOGOUT(new LogoutCommand()),
    /**
     * The Update user info.
     */
    UPDATE_USER_INFO(new UpdateUserInformationCommand()),
    /**
     * The Update user password.
     */
    UPDATE_USER_PASSWORD(new UpdateUserPasswordCommand()),
    /**
     * The Change account status.
     */
    CHANGE_ACCOUNT_STATUS(new ChangeAccountStatusCommand()),
    /**
     * The Change language.
     */
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    /**
     * The Add balance.
     */
    ADD_BALANCE(new AddUserBalanceCommand()),
    /**
     * The Pay for booking.
     */
    PAY_FOR_BOOKING(new PayForBookingCommand()),
    /**
     * The Create booking.
     */
    CREATE_BOOKING(new CreateBookingCommand()),
    /**
     * The Create room.
     */
    CREATE_ROOM(new CreateRoomCommand()),
    /**
     * The Change booking status.
     */
    CHANGE_BOOKING_STATUS(new ChangeBookingStatusCommand()),
    /**
     * The Show profile settings.
     */
    SHOW_PROFILE_SETTINGS(new ShowProfileSettingsCommand()),
    /**
     * The Delete room from booking.
     */
    DELETE_ROOM_FROM_BOOKING(new DeleteRoomFromBookingCommand()),
    /**
     * The See details of booking.
     */
    SEE_DETAILS_OF_BOOKING(new SeeDetailsOfBookingCommand()),
    /**
     * The Show free room by condition.
     */
    SHOW_FREE_ROOM_BY_CONDITION(new ShowFreeRoomsByConditionCommand()),
    /**
     * The Show room to update info.
     */
    SHOW_ROOM_TO_UPDATE_INFO(new ShowRoomToUpdateInfoCommand()),
    /**
     * The Update room info.
     */
    UPDATE_ROOM_INFO(new UpdateRoomInfoCommand()),
    /**
     * The Delete room.
     */
    DELETE_ROOM(new DeleteRoomCommand()),
    /**
     * The Add room to booking.
     */
    ADD_ROOM_TO_BOOKING(new AddRoomToBookingCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    /**
     * Gets command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

}
