package banco;

import java.sql.DriverManager;
import java.sql.Connection;

public class ConnectionFactory {
	// Atributos
	private static final String USERNAME = "root"; // nome do servidor
	private static final String PASSWORD = "root"; // senha do servidor
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/covid19?allowPublicKeyRetrieval=true&useSSL=false";

	// Métodos
	public static Connection criarConexaoComServidorMySql() throws Exception {
		// carrega classe pela maquina virtual JVM
		Class.forName("com.mysql.jdbc.Driver");

		// cria a conexão com o DB
		Connection conexao = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

		return conexao;
	}
}
