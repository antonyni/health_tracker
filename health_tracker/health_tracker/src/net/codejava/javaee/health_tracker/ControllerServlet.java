package net.codejava.javaee.health_tracker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FoodDAO foodDAO;
	private ExerciseDAO exerciseDAO;
	private SleepDAO sleepDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		foodDAO = new FoodDAO(jdbcURL, jdbcUsername, jdbcPassword);
		exerciseDAO = new ExerciseDAO(jdbcURL, jdbcUsername, jdbcPassword);
		sleepDAO = new SleepDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			//case "/initialize":
				//initDB(request, response);
				//break;
			case "/newFood":
				showNewFormFood(request, response);
				break;
			case "/insertFood":
				insertFood(request, response);
				break;
			case "/deleteFood":
				deleteFood(request, response);
				break;
			case "/editFood":
				showEditFormFood(request, response);
				break;
			case "/updateFood":
				updateFood(request, response);
				break;
			case "/newExercise":
				showNewFormExercise(request, response);
				break;
			case "/insertExercise":
				insertExercise(request, response);
				break;
			case "/deleteExercise":
				deleteExercise(request, response);
				break;
			case "/editExercise":
				showEditFormExercise(request, response);
				break;
			case "/updateExercise":
				updateExercise(request, response);
				break;
			case "/listExercise":
				listExercise(request,response);
				break;
			case "/newSleep":
				showNewFormSleep(request, response);
				break;
			case "/insertSleep":
				insertSleep(request, response);
				break;
			case "/deleteSleep":
				deleteSleep(request, response);
				break;
			case "/editSleep":
				showEditFormSleep(request, response);
				break;
			case "/updateSleep":
				updateSleep(request, response);
				break;
			case "/listSleep":
				listSleep(request,response);
				break;
			default:
				listFood(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/*private void initiDB(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		    foodDAO.initDB();
			RequestDispatcher dispatcher = request.getRequestDispatcher("FoodList.jsp");
			dispatcher.forward(request, response);
	}*/
	private void listFood(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Food> listFood = foodDAO.listAllFoods();
		request.setAttribute("listFood", listFood);
		RequestDispatcher dispatcher = request.getRequestDispatcher("FoodList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewFormFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("FoodForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditFormFood(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Food existingFood = foodDAO.getFood(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("FoodForm.jsp");
		request.setAttribute("food", existingFood);
		dispatcher.forward(request, response);

	}

	private void insertFood(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String date = request.getParameter("date");
		String healthy = request.getParameter("healthy");
		String name = request.getParameter("name");

		Food newFood = new Food(date,healthy,name);
		foodDAO.insertFood(newFood);
		response.sendRedirect("listFood");
	}

	private void updateFood(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String date = request.getParameter("date");
		String healthy = request.getParameter("healthy");
		String name = request.getParameter("name");

		Food food = new Food(id,date,healthy,name);
		foodDAO.updateFood(food);
		response.sendRedirect("listFood");
	}

	private void deleteFood(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Food food = new Food(id);
		foodDAO.deleteFood(food);
		response.sendRedirect("listFood");

	}
	private void listExercise(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Exercise> listExercise = exerciseDAO.listAllExercises();
		request.setAttribute("listExercise", listExercise);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ExerciseList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewFormExercise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("ExerciseForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditFormExercise(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Exercise existingExercise = exerciseDAO.getExercise(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ExerciseForm.jsp");
		request.setAttribute("exercise", existingExercise);
		dispatcher.forward(request, response);

	}

	private void insertExercise(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String date = request.getParameter("date");
		int mood = Integer.parseInt(request.getParameter("mood"));
		String bodyPart = request.getParameter("bodyPart");

		Exercise newExercise = new Exercise(date,mood,bodyPart);
		exerciseDAO.insertExercise(newExercise);
		response.sendRedirect("listExercise");
	}

	private void updateExercise(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String date = request.getParameter("date");
		int mood = Integer.parseInt(request.getParameter("mood"));
		String bodyPart = request.getParameter("bodyPart");

		Exercise exercise = new Exercise(id,date,mood,bodyPart);
		exerciseDAO.updateExercise(exercise);
		response.sendRedirect("listExercise");
	}

	private void deleteExercise(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Exercise exercise = new Exercise(id);
		exerciseDAO.deleteExercise(exercise);
		response.sendRedirect("listExercise");

	}
	private void listSleep(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Sleep> listSleep = sleepDAO.listAllSleeps();
		request.setAttribute("listSleep", listSleep);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SleepList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewFormSleep(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("SleepForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditFormSleep(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Sleep existingSleep = sleepDAO.getSleep(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SleepForm.jsp");
		request.setAttribute("sleep", existingSleep);
		dispatcher.forward(request, response);

	}

	private void insertSleep(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String date = request.getParameter("date");
		int mood = Integer.parseInt(request.getParameter("mood"));
		int rest = Integer.parseInt(request.getParameter("rest"));
		int how_long_hours = Integer.parseInt(request.getParameter("how_long_hours"));

		Sleep newSleep = new Sleep(date,mood,rest,how_long_hours);
		sleepDAO.insertSleep(newSleep);
		response.sendRedirect("listSleep");
	}

	private void updateSleep(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String date = request.getParameter("date");
		int mood = Integer.parseInt(request.getParameter("mood"));
		int rest = Integer.parseInt(request.getParameter("rest"));
		int how_long_hours = Integer.parseInt(request.getParameter("how_long_hours"));
		Sleep sleep = new Sleep(id,date,mood,rest,how_long_hours);
		sleepDAO.updateSleep(sleep);
		response.sendRedirect("listSleep");
	}

	private void deleteSleep(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Sleep sleep = new Sleep(id);
		sleepDAO.deleteSleep(sleep);
		response.sendRedirect("listSleep");

	}
}
