package by.sergeev.hotel.command.definer;

import by.sergeev.hotel.command.Command;
import by.sergeev.hotel.command.impl.ChangeLanguageCommand;
import by.sergeev.hotel.command.impl.LoginCommand;
import by.sergeev.hotel.command.impl.RegistrationCommand;
import by.sergeev.hotel.command.impl.ShowAllRoomsCommandImpl;

public enum CommandType {

    SHOW_ALL_ROOMS {
        {
            command = new ShowAllRoomsCommandImpl();
        }
    },
    REGISTRATION {
        {
            command = new RegistrationCommand();
        }
    },
    LOGIN {
        {
            command = new LoginCommand();
        }
    },
    LANGUAGE {
        {
            command = new ChangeLanguageCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }
}
