package by.sergeev.hotel.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Command definer.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public class CommandDefiner {

    private static final Logger LOGGER = LogManager.getLogger(CommandDefiner.class);

    /**
     * Define command.
     *
     * @param request the request
     * @return the command
     */
    public static Command define(HttpServletRequest request) {
        String command = request.getParameter(PageParameter.COMMAND);
        LOGGER.info("Command: " + command);
        CommandType currentEnum = CommandType.valueOf(command.toUpperCase());
        Command currentCommand = currentEnum.getCommand();
        return currentCommand;
    }


    private CommandDefiner() {
    }
}
