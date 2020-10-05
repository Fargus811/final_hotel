package by.sergeev.hotel.command.definer;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.command.impl.HelloCommand;
import by.sergeev.hotel.command.impl.ShowAllRoomsCommandImpl;

public enum CommandType {

    SHOW_WELCOME_PAGE {
        {
            command = new ShowAllRoomsCommandImpl();
        }
    },
    HELLO{
        {
            command = new HelloCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }
}
