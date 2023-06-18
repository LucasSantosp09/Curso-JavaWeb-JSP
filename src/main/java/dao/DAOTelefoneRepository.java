package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DAOTelefoneRepository {

	private Connection connection;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	
	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();

	}

	public List<ModelTelefone> listFone(Long idUserPai) throws Exception{
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
		
		String sql = "select * from telefone where usuario_pai_id = ? ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery();
		
		 while (rs.next()){
			 ModelTelefone modelTelefone = new ModelTelefone();
			 modelTelefone.setId(rs.getLong("id"));
			 modelTelefone.setNumero(rs.getString("numero"));
			 modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioID(rs.getLong("usuario_pai_id")));
			 retorno.add(modelTelefone);
		 }
		
		return retorno;
	}
	
	public void gravaTelefone ( ModelTelefone modelTelefone ) throws Exception {
		String sql = "insert into telefone (numero, usuario_pai_id) values ( ?, ? )";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelTelefone.getNumero());
		statement.setLong(2, modelTelefone.getUsuario_pai_id().getId());

		statement.execute();
		connection.commit();
		
	}
	
	public void deleteFone (Long id) throws Exception{
		String sql = "delete from telefone where id = ? ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, id);

		statement.executeUpdate();
		
		connection.commit();
	}
	
}
