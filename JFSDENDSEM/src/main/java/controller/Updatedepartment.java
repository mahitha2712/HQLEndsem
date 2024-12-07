package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Department;
import model.DepartmentManager;

/**
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/Updatedepartment")
public class Updatedepartment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Updatedepartment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int deptId = Integer.parseInt(request.getParameter("Id"));
        String name = request.getParameter("name");
        String location = request.getParameter("location");

        response.setContentType("text/html");
        String message;
        try {
            DepartmentManager manager = new DepartmentManager();
            message = manager.updateDepartment(deptId, name, location);
        } catch (Exception e) {
            message = "Error: " + e.getMessage();
        }

        PrintWriter out = response.getWriter();
        out.println(message);

        RequestDispatcher rd = request.getRequestDispatcher("update.html");
        rd.include(request, response);
    }

}
