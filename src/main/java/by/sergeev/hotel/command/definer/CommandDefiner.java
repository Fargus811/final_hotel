package by.sergeev.hotel.command.definer;

import by.sergeev.hotel.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandDefiner {

        private static final Logger LOGGER = LogManager.getLogger();

        private static final String COMMAND_PARAM = "command";

        public Command define(HttpServletRequest request) {
            String command = request.getParameter(COMMAND_PARAM);
            LOGGER.info("Command: " + command);
            CommandType currentEnum = CommandType.valueOf(command.toUpperCase());
            Command curCommand = currentEnum.getCommand();
            return curCommand;
        }
}
