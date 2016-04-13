package projects.chatovich.servlets;

import projects.chatovich.servlets.DAO.UserDAO;
import projects.chatovich.servlets.JD03_02.DB_it_academy.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 */
public class FilterAuth implements Filter {


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(true);
        if (session == null) {
            resp.sendRedirect("/chatovich/login.html");
        }
        else {

            Object attr = session.getAttribute("auth");
            if (attr==(Boolean)true) {
                filterChain.doFilter(request,response);
                return;
            }
            else{

                Cookie[] myCookies = req.getCookies();
                String cookieName = "login";
                String cookiePassword = "password";
                String login="";
                String password="";

                for (Cookie cookie : myCookies) {
                    if (cookieName.equals(cookie.getName())){
                        login = cookie.getValue();
                    }
                    if (cookiePassword.equals(cookie.getName())){
                        password = cookie.getValue();
                    }
                }

                UserDAO userDAO = new UserDAO();
                HashMap<Integer, User> users = new HashMap<>();
                try {
                    users = userDAO.getAll("where login = '"+login+"' and password = '"+password+"';");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (users.size()==0) {
                    resp.sendRedirect("/chatovich/login.html");
                }
                else {
                    filterChain.doFilter(request,response);
                    return;
                }
            }
        }
        /*Cookie[] myCookies = req.getCookies();
        String cookieName = "login";
        String cookiePassword = "password";
        String login="";
        String password="";

        PrintWriter out = resp.getWriter();
        for (Cookie myCookie : myCookies) {
            out.println(myCookie.getName()+" - "+myCookie.getValue());
        }*/

        /*for (Cookie cookie : myCookies) {
            if (cookieName.equals(cookie.getName())){
                login = cookie.getValue();
            }
            if (cookiePassword.equals(cookie.getName())){
                password = cookie.getValue();
            }
        }

        UserDAO userDAO = new UserDAO();
        HashMap<Integer, User> users = new HashMap<>();
        try {
            users = userDAO.getAll("where login = '"+login+"' and password = '"+password+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users.size()==0) {
            resp.sendRedirect("/chatovich/login.html");
        }
        else {
            filterChain.doFilter(request,response);
            return;
        }*/

    }

    public FilterAuth() {
        super();
    }


}
