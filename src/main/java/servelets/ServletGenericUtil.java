package servelets;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.DAOUsuarioRepository;

public class ServletGenericUtil implements Serializable{


	private static final long serialVersionUID = 1L;

	

	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	

	
	public Long getUserLogado(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultaUsuario(usuarioLogado).getId();
	}
}
