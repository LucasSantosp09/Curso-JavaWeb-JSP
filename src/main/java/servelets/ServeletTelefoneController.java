package servelets;

import java.io.IOException;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

@MultipartConfig
@WebServlet(urlPatterns = { "/ServeletTelefoneController"})
public class ServeletTelefoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
	
	public ServeletTelefoneController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String iduser = request.getParameter("iduser");
		
		try {
			
			
			ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(iduser);
			
			request.setAttribute("modelLogin", modelLogin);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
			

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setNumero(numero);
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioID(Long.parseLong(usuario_pai_id)));
			
			daoTelefoneRepository.gravaTelefone(modelTelefone);
			
			request.setAttribute("msg", "Salvo com sucesso !");

			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
		}catch (Exception e ) {
			e.printStackTrace();
		}
		

		
		
	}

}
