package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User> users = List.of(new User("Neptune", "neptune_morpheus@gmail.com"), new User("Pluto", "pluto_morpheus@gmail.com"));
		
		Gson gson = new Gson();
		String json = gson.toJson(users);
		System.out.println(json);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet method called in UserServlet.java");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		var name = request.getParameter("name");
		var password = request.getParameter("password");
		
		System.out.println("doPost method in UserServlet.java : form submitted with name = " + name + ", password: " + password);
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.print("<br/>"); // Ensures the output will be on a new line.
		pw.println("<html>Form submitted with name = " + name + ", password: " + password);
	}
}
