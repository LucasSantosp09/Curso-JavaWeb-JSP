package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection; 
	
	public DAOUsuarioRepository() {
		
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario (ModelLogin obj) throws Exception {

			String sql = "INSERT INTO model_login(login, senha,nome, email) VALUES (?, ?, ?, ?);";
			PreparedStatement preparaSql = connection.prepareStatement(sql);
			preparaSql.setString(1, obj.getLogin());
			preparaSql.setString(2, obj.getSenha());
			preparaSql.setString(3, obj.getNome());
			preparaSql.setString(4, obj.getEmail());	
			
			preparaSql.execute();
	
			connection.commit();
			
			return this.consultaUsuario(obj.getLogin());
		
	}
	
	public ModelLogin consultaUsuario(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		
		String sql = "select * from model_login where upper(login) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet resultado = statement.executeQuery();
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
		}
		
		return modelLogin;
	}
	
	public boolean validarLogin (String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?)";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, login);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		return resultado.getBoolean("existe");
		
	}
	
}
