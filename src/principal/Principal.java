package principal;

import banco.PessoaDAO;
import dados.Hospital;
import saida.TelaPrincipal;

public class Principal {

	public static void main(String[] args) {
		// Inicia objeto de GRUPO
		Hospital hospital = new Hospital();

		// Seta dados da memória ROM na RAM
		hospital.setPessoasFromDB(PessoaDAO.getPessoasFromDB());

		// Inicia frontend SWING, que dispara ações do sistema
		TelaPrincipal telaPrincipal = new TelaPrincipal(hospital);
	}
}
