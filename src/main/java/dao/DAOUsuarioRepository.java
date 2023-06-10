package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {

		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin obj) throws Exception {

		if (obj.isNovo()) {

			String sql = "INSERT INTO model_login(login, senha,nome, email, sexo, cep, logradouro, bairro, localidade, uf, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement preparaSql = connection.prepareStatement(sql);
			preparaSql.setString(1, obj.getLogin());
			preparaSql.setString(2, obj.getSenha());
			preparaSql.setString(3, obj.getNome());
			preparaSql.setString(4, obj.getEmail());
			preparaSql.setString(5, obj.getSexo());
			preparaSql.setString(6, obj.getCep());
			preparaSql.setString(7, obj.getLogradouro());
			preparaSql.setString(8, obj.getBairro());
			preparaSql.setString(9, obj.getLocalidade());
			preparaSql.setString(10, obj.getUf());
			preparaSql.setString(11, obj.getNumero());

			preparaSql.execute();

			connection.commit();
		} else {
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, sexo = ?, cep = ?, logradouro = ?, bairro = ?, localidade = ?, uf = ? , numero = ? WHERE id = "
					+ obj.getId() + ";";
			PreparedStatement preparaSql = connection.prepareStatement(sql);
			preparaSql.setString(1, obj.getLogin());
			preparaSql.setString(2, obj.getSenha());
			preparaSql.setString(3, obj.getNome());
			preparaSql.setString(4, obj.getEmail());
			preparaSql.setString(5, obj.getSexo());
			preparaSql.setString(6, obj.getCep());
			preparaSql.setString(7, obj.getLogradouro());
			preparaSql.setString(8, obj.getBairro());
			preparaSql.setString(9, obj.getLocalidade());
			preparaSql.setString(10, obj.getUf());
			preparaSql.setString(11, obj.getNumero());
			preparaSql.executeUpdate();

			connection.commit();
		}
		return this.consultaUsuario(obj.getLogin());

	}

	public List<ModelLogin> consultaUsuarioListPaginada(Integer offset) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where useradmin is false order by nome offset " + offset + "5";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { /* percorrer as linhas de resultado do SQL */

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));

			// modelLogin.setSenha(resultado.getString("senha"));

			retorno.add(modelLogin);
		}

		return retorno;
	}
	

	public List<ModelLogin> consultaUsuarioList() throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where useradmin is false limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { /* percorrer as linhas de resultado do SQL */

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));

			// modelLogin.setSenha(resultado.getString("senha"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public List<ModelLogin> consultaUsuarioList(String nome) throws SQLException {
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where  upper(nome) like upper(?) and useradmin is false ";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, "%" + nome + "%");

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSexo(resultado.getString("sexo"));
			// modelLogin.setSenha(resultado.getString("senha"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public ModelLogin consultaUsuario(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper(login) = upper(?) and useradmin is false limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		ResultSet resultado = statement.executeQuery();
		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}

		return modelLogin;
	}

	public ModelLogin consultaUsuarioID(String id) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where id = ? and useradmin is false";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}

		return modelLogin;
	}
	
	public ModelLogin consultaUsuarioID(Long id) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where id = ? and useradmin is false";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}

		return modelLogin;
	}
	


	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper(?)";

		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, login);

		ResultSet resultado = statement.executeQuery();

		resultado.next();
		return resultado.getBoolean("existe");

	}

	public void deletarUser(String idUser) throws Exception {
		String sql = "DELETE FROM model_login WHERE id = ? ";

		PreparedStatement preparaSql = connection.prepareStatement(sql);
		preparaSql.setLong(1, Long.parseLong(idUser));
		preparaSql.executeUpdate();

		connection.commit();

	}

}
