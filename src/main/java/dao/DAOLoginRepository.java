package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean validarAutenticacao (ModelLogin modelLogin) throws Exception{
		String sql = "select * from model_login where login = ? and senha = ? ";
		
		PreparedStatement statament = connection.prepareStatement(sql);
		
		statament.setString(1, modelLogin.getLogin());
		statament.setString(2, modelLogin.getSenha());
		
		ResultSet resulset = statament.executeQuery();
		
		
		if (resulset.next()) {
			return true;
		}else {
			
			return false;
		}
				
	}
}
