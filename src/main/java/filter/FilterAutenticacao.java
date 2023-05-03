package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"}) /*Interceptas todas as requisições que vierem do projeto ou mapemaento*/
public class FilterAutenticacao extends HttpFilter implements Filter {
       

    public FilterAutenticacao() {
        super();
       
    }

    /*Encerra os processos quando o servidor é parado*/
    /*Mataria os processo de conexão com o banco*/
	public void destroy() {
		
	}

    /*Intercepta as requisicoes e as respostas do sistema*/
	/*Tudo que fizer no sistema vai fazer por aqui*/
	/*Validação de autenticação*/
	/*Dar commit e rollback de transações do banco*/
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath(); /* URL QUE ESTÁ SENDO UTILIZADA*/
		
		/*validar se está logado se não redirecionar para tela de login*/
		
		if (usuarioLogado == null || (usuarioLogado != null && usuarioLogado.isEmpty()) && !urlParaAutenticar.contains("/principal/ServeletLogin")) {
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor, realizae o login !");
			redireciona.forward(request, response);
			return; /*Para a execução redireciona para o login*/
		}else {
			chain.doFilter(request, response);
		}
		

	}


	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	/*Iniciar conexãp com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
