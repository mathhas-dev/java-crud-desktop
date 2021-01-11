package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import dados.Contaminado;
import dados.Pessoa;
import dados.Saudavel;

public class PessoaDAO {
	public static void salvaPessoaDB(String nome, Character sexo, Character situacao, Integer idade) {
		// Atributos
		String sql = "INSERT INTO pessoa(nome, genero, saude, idade) VALUES(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pdst = null;

		// Métodos
		try {
			// cria a conexao com o DB
			conn = ConnectionFactory.criarConexaoComServidorMySql();

			// Conecta o preparedStatement com o servidor
			pdst = conn.prepareStatement(sql);

			// Prepara os dados no objeto de inserção no DB
			pdst.setString(1, nome);
			pdst.setString(2, String.valueOf(sexo));

			if (situacao != null)
				pdst.setString(3, String.valueOf(situacao));
			else
				pdst.setNull(3, Types.OTHER);

			if (idade != null)
				pdst.setInt(4, idade);
			else
				pdst.setNull(4, Types.INTEGER);

			// executa os comandos de inserção
			pdst.execute();

		} catch (Exception e) {
			// Caso haja erro, retorna o log
			e.printStackTrace();
		} finally {
			// fecha as conexoes com o DB...
			try {
				// ...se elas estiverem abertas
				if (pdst != null)
					pdst.close();

				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static List<Pessoa> getPessoasFromDB() {
		// Atributos
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM pessoa";
		Connection conn = null;
		PreparedStatement pdst = null;
		ResultSet rSet = null;

		// Métodos
		try {
			// cria conexao com DB
			conn = ConnectionFactory.criarConexaoComServidorMySql();

			// conecta o pdst com DB e indica ação
			pdst = conn.prepareStatement(sql);

			// Faz a leitura do DB e armazena no objeto rSet
			rSet = pdst.executeQuery();

			// Equanto houver objetos no rSet salva no arrayList
			while (rSet.next()) {
				Pessoa pessoa;
				if (rSet.getString("saude") == null)
					pessoa = new Saudavel(rSet.getInt("idPessoa"), rSet.getString("nome"),
							rSet.getString("genero").charAt(0), rSet.getInt("idade"));
				else
					pessoa = new Contaminado(rSet.getInt("idPessoa"), rSet.getString("nome"),
							rSet.getString("genero").charAt(0), rSet.getString("saude").charAt(0));

				// atribui o objeto instanciado no arrayList
				pessoas.add(pessoa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rSet != null)
					rSet.close();

				if (pdst != null)
					pdst.close();

				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pessoas;
	}

	public static Pessoa getPessoaDB(String nome, Character sexo, Character situacao, Integer idade) {
		// Atributos
		String sql = "SELECT * FROM pessoa WHERE nome=?";
		Connection conn = null;
		PreparedStatement pdst = null;
		ResultSet rSet = null;
		Pessoa pessoa = null;

		// Métodos
		try {
			// cria conexao com DB
			conn = ConnectionFactory.criarConexaoComServidorMySql();

			// conecta o pdst com DB e indica ação
			pdst = conn.prepareStatement(sql);

			// Prepara os dados no objeto de inserção no DB
			pdst.setString(1, nome);

			// Faz a leitura do DB e armazena no objeto rSet
			rSet = pdst.executeQuery();

			while (rSet.next()) {
				if (rSet.getString("saude") == null)
					pessoa = new Saudavel(rSet.getInt("idPessoa"), rSet.getString("nome"),
							rSet.getString("genero").charAt(0), rSet.getInt("idade"));
				else
					pessoa = new Contaminado(rSet.getInt("idPessoa"), rSet.getString("nome"),
							rSet.getString("genero").charAt(0), rSet.getString("saude").charAt(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rSet != null)
					rSet.close();

				if (pdst != null)
					pdst.close();

				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pessoa;
	}
}
