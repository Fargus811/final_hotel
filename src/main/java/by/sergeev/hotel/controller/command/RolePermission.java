package by.sergeev.hotel.controller.command;

import java.util.EnumSet;
import java.util.Set;

import static by.sergeev.hotel.controller.command.CommandType.*;

/**
 * The {@code RolePermission} enum represents role permission.
 *
 * @author Sergeev Daniil
 * @version 1.0
 */
public enum RolePermission {

    /**
     * Guest command role permission.
     */
    GUEST(EnumSet.of(PASS_TO_LOGIN,
            PASS_TO_REGISTRATION,
            LOGIN,
            REGISTRATION,
            SHOW_ALL_ROOMS,
            CHANGE_LANGUAGE,
            ACTIVATE_ACCOUNT)),

    /**
     * Client command role permission.
     */
    USER(EnumSet.of(SHOW_ALL_ROOMS,
            CHANGE_BOOKING_STATUS,
            CHANGE_ACCOUNT_STATUS,
            CREATE_BOOKING,
            PAY_FOR_BOOKING,
            SEE_DETAILS_OF_BOOKING,
            SHOW_USER_BOOKINGS,
            PASS_TO_CREATE_BOOKING,
            ADD_BALANCE,
            UPDATE_USER_INFO,
            UPDATE_USER_PASSWORD,
            SHOW_MY_PROFILE,
            SHOW_PROFILE_SETTINGS,
            CHANGE_LANGUAGE,
            LOGOUT)),

    /**
     * Admin command role permission.
     */
    ADMIN(EnumSet.of(SHOW_ALL_ROOMS,
            ADD_ROOM_TO_BOOKING,
            CHANGE_BOOKING_STATUS,
            SEE_DETAILS_OF_BOOKING,
            SHOW_ALL_BOOKINGS,
            SHOW_USER_BOOKINGS,
            PASS_TO_CREATE_ROOM,
            PASS_TO_LOGIN,
            CREATE_ROOM,
            DELETE_ROOM,
            UPDATE_ROOM_INFO,
            SHOW_FREE_ROOM_BY_CONDITION,
            SHOW_ROOM_TO_UPDATE_INFO,
            CHANGE_ACCOUNT_STATUS,
            CHANGE_LANGUAGE,
            LOGIN,
            UPDATE_USER_INFO,
            UPDATE_USER_PASSWORD,
            SHOW_ALL_USERS,
            SHOW_MY_PROFILE,
            SHOW_PROFILE_SETTINGS,
            LOGOUT));

    private Set<CommandType> roleCommands;

    RolePermission(EnumSet<CommandType> roleCommands) {
        this.roleCommands = roleCommands;
    }

    /**
     * Get role commands set.
     *
     * @return the set
     */
    public Set<CommandType> getRoleCommands() {
        return this.roleCommands;
    }
}
