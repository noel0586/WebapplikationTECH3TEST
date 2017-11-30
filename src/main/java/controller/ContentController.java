package controller;

import DAO.DatabaseRead;
import DAO.DatabaseWrite;
import Model.Content;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by faisaljarkass on 29-10-2016.
 */
public class ContentController extends HttpServlet {

    private static Logger logger = Logger.getLogger(ContentController.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newContent = request.getParameter("newContent");

        logger.log(Level.INFO, "doPost start...");
        logger.log(Level.INFO, newContent);

        //TODO: Persist newly added content
        try {
            new DatabaseWrite().addContent(newContent);
        }
        catch (SQLException se){
            se.printStackTrace();
        }

        logger.log(Level.INFO, "alt godt");

        HttpSession session = request.getSession();

        //TODO: Fetch all content from datastore
        List<Content> content = new ArrayList<>();

        try {
            content = new DatabaseRead().getAllContent();
        }
        catch (SQLException se){
            se.printStackTrace();
        }


        session.setAttribute("content", content);
        String redirect = response.encodeRedirectURL(request.getContextPath() + "/loginSuccessAdmin.jsp");
        response.sendRedirect(redirect);
    }
}
