package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection; 
	
	public DAOUsuarioRepository() {
		
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void gravarUsuario (ModelLogin obj) throws SQLException {

			String sql = "INSERT INTO model_login(login, senha,nome, email) VALUES (?, ?, ?, ?);";
			PreparedStatement preparaSql = connection.prepareStatement(sql);
			preparaSql.setString(1, obj.getLogin());
			preparaSql.setString(2, obj.getSenha());
			preparaSql.setString(3, obj.getNome());
			preparaSql.setString(4, obj.getEmail());	
			
			preparaSql.execute();
	
			connection.commit();
		
	}
}
