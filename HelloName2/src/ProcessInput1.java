
import java.io.IOException;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.Statement;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessInput1")

public class ProcessInput1 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProcessInput1() {

		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		{

			try

			{

				response.setContentType("text/html");

				PrintWriter out = response.getWriter();

				try

				{

					Class.forName("com.mysql.jdbc.Driver").newInstance();

					String jdbcUrl = "jdbc:mysql://localhpst:3306/mydb";

					String username = "root";

					String password = "qwerty";

					Connection connection = null;

					connection = DriverManager.getConnection(jdbcUrl, username, password);

					//statement
					Statement statement = connection.createStatement();

					String x = request.getParameter("EmployeeID");

					String y = request.getParameter("EmployeeNAME");

					String sql = "insert into Employee values('" + x + "','" + y + "')";

					//excecute statemetn
					statement.executeUpdate(sql);

					out.println("<h1> Record successfully inserted</h1>");

					RequestDispatcher rd = request.getRequestDispatcher("/form.html");

					rd.include(request, response);

				}

				catch (ClassNotFoundException | InstantiationException | IllegalAccessException cnfe)

				{

					out.println("class not found");

				}

			}

			catch (SQLException e)

			{

				throw new RuntimeException("Cannot connect the database!", e);

			}
		}

	}

}
