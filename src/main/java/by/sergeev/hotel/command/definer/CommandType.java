package by.sergeev.hotel.command.definer;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.command.impl.ShowAllRoomsCommandImpl;

public enum CommandType {

    SHOW_ALL_ROOMS {
        {
            command = new ShowAllRoomsCommandImpl();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }
}
