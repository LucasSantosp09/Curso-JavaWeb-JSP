package servelets;

import java.io.IOException;

import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@MultipartConfig
@WebServlet(urlPatterns = { "/ServeletTelefoneController"})
public class ServeletTelefoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public ServeletTelefoneController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String iduser = request.getParameter("iduser");
		
		try {
			
			
			ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(iduser);
			
			request.setAttribute("usuario", modelLogin);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
	}

}
