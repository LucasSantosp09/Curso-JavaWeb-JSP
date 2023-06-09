package servelets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@MultipartConfig
@WebServlet(urlPatterns = { "/ServeletUsuarioController"})
public class ServeletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServeletUsuarioController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				
				daoUsuarioRepository.deletarUser(idUser);
				
				request.setAttribute("msg", "Excluído com Sucesso !");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			}
			
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUser(idUser);
				
				response.getWriter().write("Excluído com Sucesso");
			}
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca);
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json);
			}
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id);
				
				request.setAttribute("msg", "Usuário em Edição");

				request.setAttribute("modolLogin", modelLogin);

				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}
			
			 else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				 
				 List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList();
				 
				 request.setAttribute("msg", "Usuários carregados");
			     request.setAttribute("modelLogins", modelLogins);
				 request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				 
			 }
			else {
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação Realizada com Sucesso !";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String sexo = request.getParameter("sexo");
			String uf = request.getParameter("uf");
			String cep = request.getParameter("cep");
			String localidade = request.getParameter("localidade");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String numero = request.getParameter("numero");
			
			
			
			

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setSexo(sexo);
			modelLogin.setCep(cep);
			modelLogin.setLocalidade(localidade);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setBairro(bairro);
			modelLogin.setUf(uf);
			modelLogin.setNumero(numero);
			
			
		
			
			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe usuário com o mesmo login, informe outro login";
			} else {
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			}

			request.setAttribute("msg", msg);

			request.setAttribute("modolLogin", modelLogin);

			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
