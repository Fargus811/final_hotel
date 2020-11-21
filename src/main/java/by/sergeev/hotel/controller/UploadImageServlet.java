package by.sergeev.hotel.controller;

import by.sergeev.hotel.controller.command.PageParameter;
import by.sergeev.hotel.controller.command.PagePath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/upload_image_controller/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class UploadImageServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(UploadImageServlet.class);
    private static final String UPLOAD_LOCATION = "/Users/mac/Downloads/hotel1/src/main/webapp/resources/images";
    private static final String FILE_PARAMETER = "file";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                    logger.log(Level.ERROR, "FileName " + fileName, e);
                }
            });
        }
        request.getRequestDispatcher(PagePath.CREATE_ROOM).forward(request, response);
    }
}