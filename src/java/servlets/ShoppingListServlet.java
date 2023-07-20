package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fortune Agbone
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String action = request.getParameter("action");

        //logout
        if (action != null && action.equals("logout")) {
            //Invalidate the session to reset the application
            session.invalidate();
            username = null;
            request.setAttribute("message","You have successfully logged out.");
        }
        
        if (username == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        
        
        if (action.equals("register")) {
            //retreive the username fromt the request
            String username = request.getParameter("username");
            if (username != null && !username.equals("")) {
                //Store the username in the session
                session.setAttribute("username", username);

                //create an ArrayList to store Shopping List Items
                ArrayList<String> shoppingList = new ArrayList<>();

                //add shopping list to the session object
                session.setAttribute("shoppingList", shoppingList);
                
                //redirect to shoppingList url
                //session contains username, therefore user is registered
                //a redirect will make a new GET request, and send the user to the shopping list
                //because they are logged in
                response.sendRedirect("ShoppingList");
                return;
            }else {
                /* User did not enter a valid username,
                take them to the register page with error message
                */
                String message = "Please enter a username.";
                request.setAttribute("message", message);
                //this will stay in the doPost, 
                //and load a JSP to be displayed
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
                return;
            }

        } else if (action.equals("add")) {
            //Retrieve the item from the request
            String item = request.getParameter("item");

            //Retrieve the shopping lsit fromt the session
            ArrayList<String> shoppingList = (ArrayList<String>) session.getAttribute("shoppingList");

            //Add the item to the shopping list
            shoppingList.add(item);
            
            // save the shopping list back to the session
            session.setAttribute("shoppingList", shoppingList);
            
            //redirect to shoppingList url
            //session contains username, therefore user is registered
            //a redirect will make a new GET request, and send the user to the shopping list
            //because they are logged in
            response.sendRedirect("ShoppingList");
        } else if (action.equals("delete")) {
            //Retrieve the index of the item to be deleted
            //int index = Integer.parseInt(request.getParameter("index"));
            
            // get the value of the item to be deleted
            String itemToDelete = request.getParameter("itemToDelete");

            //Retrieve the shopping list from the session
            ArrayList<String> shoppingList = (ArrayList<String>) session.getAttribute("shoppingList");

            //Retrieve the item from the the shopping list
            shoppingList.remove(itemToDelete);
            
            // save the shopping list back to the session
            session.setAttribute("shoppingList", shoppingList);
            
            //Redirect to ShoppingList
            response.sendRedirect("ShoppingList");
        }
    }
}
