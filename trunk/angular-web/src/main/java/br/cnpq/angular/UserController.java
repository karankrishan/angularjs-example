package br.cnpq.angular;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.cnpq.model.User;
import br.cnpq.servico.UserServiceLocal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserServiceLocal userLocal;

	List<User> users = new ArrayList<User>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response) {
		String metodo = request.getParameter("metodo");

		if (metodo.equals("save")) {
			save(request, response);
		}
		if (metodo.equals("list")) {
			list(request, response);
		}
		if (metodo.equals("delete")) {
			delete(request, response);
		}

	}

	public void save(HttpServletRequest request, HttpServletResponse response) {

		try {
			String json = request.getParameter("user");

			GsonBuilder builder = new GsonBuilder();
			Gson g = builder.create();
			User user = g.fromJson(json, User.class);

			if (user.getId() != null && user.getId() == 0) {
				user.setId(null);
			}

			userLocal.save(user);

			list(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response) {
		try {

			List<User> users = userLocal.listAll();
			StringBuilder retorno = new StringBuilder();

			JSONArray array = new JSONArray();
			for (User user : users) {
				Gson gson = new Gson();
				retorno.append(gson.toJson(user) + ",");

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", user.getId());
				jsonObject.put("nome", user.getNome());
				jsonObject.put("sobreNome", user.getSobreNome());
				jsonObject.put("idade", user.getIdade());

				array.put(jsonObject);

			}

			// Devolve o json na response
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(array);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {

			String json = request.getParameter("user");
			GsonBuilder builder = new GsonBuilder();
			Gson g = builder.create();
			User user = g.fromJson(json, User.class);

			userLocal.delete(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
