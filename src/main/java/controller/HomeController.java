package controller;

import DAO.DatabaseRead;
import Model.Content;
import Model.UserList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by faisaljarkass on 22-10-2016.
 */
public class HomeController extends HttpServlet {

    private static Logger logger = Logger.getLogger(HomeController.class.getName());


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        logger.log(Level.INFO, "doPost start...");
        logger.log(Level.INFO, "Username: " + username);
        logger.log(Level.INFO, "Password: " + password);
        logger.log(Level.INFO, "Checkbox: " + request.getParameter("rememberMe"));

        UserList userList = new UserList();

        String result = userList.validateUser(username,password);

        if (result.equals("isAdmin")) {

            try {
                List<Content> content = new DatabaseRead().getAllContent();
                session.setAttribute("content", content);
            }
            catch (SQLException se){
                se.printStackTrace();
            }

            RequestDispatcher rd = request.getRequestDispatcher("/loginSuccessAdmin.jsp");
            rd.forward(request, response);

        }else if (result.equals("isUser")){
            RequestDispatcher rd = request.getRequestDispatcher("/loginSuccess.jsp");
            rd.forward(request, response);

            try {
                List<Content> content = new DatabaseRead().getAllContent();
                session.setAttribute("content", content);
            }
            catch (SQLException se){
                se.printStackTrace();
            }

        } else if (result.equals("noUser")) {
            logger.log(Level.INFO, "creds invalid");
            request.setAttribute("errorMessage", "true");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
