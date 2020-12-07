package by.sergeev.hotel.controller;

import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import by.sergeev.hotel.controller.command.room.show.ShowRoomToUpdateInfoCommand;
import by.sergeev.hotel.exception.CommandException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * The type Upload image servlet.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
@WebServlet(urlPatterns = "/upload_image_controller/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class UploadImageServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UploadImageServlet.class);
    private static final String UPLOAD_LOCATION = "/Users/mac/Downloads/hotel1/src/main/webapp/resources/images";
    private static final String FILE_PARAMETER = "file";
    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String INLINE = "inline; filename=\"";
    private static final String SEPARATOR = "\"";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        uploadImage(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getImage(request, response);
    }

    private void getImage(HttpServletRequest request, HttpServletResponse response) {
        try {
            String filename = URLDecoder.decode(request.getPathInfo().substring(1), ENCODING);
            File file = new File(UPLOAD_LOCATION, filename);
            response.setHeader(CONTENT_TYPE, getServletContext().getMimeType(filename));
            response.setHeader(CONTENT_LENGTH, String.valueOf(file.length()));
            response.setHeader(CONTENT_DISPOSITION, INLINE + file.getName() + SEPARATOR);
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (Exception e) {
            LOGGER.error("Can't get image", e);
        }

    }

    private void uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String fileName = request.getPart(FILE_PARAMETER).getSubmittedFileName();
        Path path = Paths.get(UPLOAD_LOCATION);
        Path fullPath = Paths.get(path + File.separator + fileName);
        if (Files.notExists(path)) {
            Files.createDirectory(path);
        }
        if (Files.notExists(fullPath)) {
            List<Part> parts = request.getParts().stream().filter(part -> FILE_PARAMETER.equals(part.getName()))
                    .collect(Collectors.toList());
            parts.forEach(part -> {
                try {
                    part.write(fullPath.toString());
                    session.setAttribute(PageParameter.DOWNLOAD_STATUS, fileName);
                } catch (IOException e) {
                    LOGGER.log(Level.ERROR, "FileName " + fileName, e);
                }
            });
        }
        if (Objects.isNull(request.getParameter(PageParameter.UPDATE))) {
            request.getRequestDispatcher(PagePath.CREATE_ROOM).forward(request, response);
        } else {
            forwardToUpdateForm(request, response);
        }
    }

    private void forwardToUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            new ShowRoomToUpdateInfoCommand().execute(request);
        } catch (CommandException e) {
            LOGGER.error("Upload command failed", e);
            response.sendRedirect(PagePath.ERROR);
        }
        request.getRequestDispatcher(PagePath.UPDATE_ROOM_INFO).forward(request, response);
    }
}
