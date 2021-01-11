package validacao;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import banco.PessoaDAO;
import dados.*;

public class Valida {
	private static final int MINIMO = 1;
	private static final int MAXIMO = 130;

	public static boolean nome(String nome) {
		int MINIMOPERMITIDO = 3, MAXIMOPERMITIDO = 90;
		if (nome.length() <= MINIMOPERMITIDO || nome.length() > MAXIMOPERMITIDO) {
			JOptionPane.showMessageDialog(null,
					"Insira um nome entre 3 e 90 caractéres para continuar com o cadastros!");
			return false;
		}

		return true;
	}

	public static boolean sexo(JRadioButton rdbtnMasculino, JRadioButton rdbtnFeminino) {
		char sexo = selecaoSexo(rdbtnMasculino, rdbtnFeminino);

		if (sexo != 'M' && sexo != 'F') {
			JOptionPane.showMessageDialog(null,
					"Insira 'M' para 'Masculino' ou 'F' para 'Feminino' para continuar com o cadastros! ");
			return false;
		}

		return true;
	}

	public static Character selecaoSexo(JRadioButton rdbtnMasculino, JRadioButton rdbtnFeminino) {
		char sexo = 0;

		if (rdbtnMasculino.isSelected())
			sexo = 'M';
		if (rdbtnFeminino.isSelected())
			sexo = 'F';

		return sexo;
	}

	public static boolean idade(int idade) {
		if (idade < MINIMO - 1 || idade > MAXIMO) {
			JOptionPane.showMessageDialog(null, "Insira idades entre 0 e 130 anos para continuar com os cadastros!");
			return false;
		}
		return true;
	}

	public static boolean situacao(JRadioButton rdbtnCurado, JRadioButton rdbtnEmTratamento,
			JRadioButton rdbtnFalecido) {
		char situacao = selecaoSituacao(rdbtnCurado, rdbtnEmTratamento, rdbtnFalecido);

		if (situacao != 'C' && situacao != 'E' && situacao != 'F') {
			JOptionPane.showMessageDialog(null,
					"Insira 'C' para 'Curado', 'E' para 'Em tratamento' ou 'F' para 'Falecido para continuar com o cadastros! ");
			return false;
		}

		return true;
	}

	public static Character selecaoSituacao(JRadioButton rdbtnCurado, JRadioButton rdbtnEmTratamento,
			JRadioButton rdbtnFalecido) {
		char situacao = 0;

		if (rdbtnCurado.isSelected())
			situacao = 'C';
		if (rdbtnEmTratamento.isSelected())
			situacao = 'E';
		if (rdbtnFalecido.isSelected())
			situacao = 'F';

		return situacao;
	}

	// Valida o ID inserido na busca
	public static int inteiro(String num) {
		int n = 0;
		try {
			n = Integer.parseInt(num);
			if (n < MINIMO) {
				JOptionPane.showMessageDialog(null, "Digite apenas números maiores que 0!");
				return -1;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Digite apenas números!");
			return -1;
		}
		return n;
	}

	public static Hospital cadastraPessoa(Hospital hospital, PessoaTableModel pessoaTableModel, String nome,
			Character sexo, Character situacao, Integer idade) {
		// Cadastra apenas se o objeto não for duplicado
		if (!hospital.objetoDuplicado(nome, sexo, situacao, idade)) {
			// Salva no banco
			PessoaDAO.salvaPessoaDB(nome, sexo, situacao, idade);
			// Instancia Pessoa
			Pessoa p = PessoaDAO.getPessoaDB(nome, sexo, situacao, idade);
			// Seta nova pessoa na memoria
			hospital.setPessoa(p);
			// Seta nova pessoa na tabela
			pessoaTableModel.setItem(p);
		} else
			JOptionPane.showMessageDialog(null, "Já existe esta Pessoa no banco de dados!\n");
		return hospital;
	}

}
