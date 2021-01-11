package saida;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dados.Hospital;
import dados.PessoaTableModel;
import validacao.Valida;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarSaudavel extends JFrame {

	private JPanel contentPane, panel;
	private JLabel lblIdade, lblSexo, lblNome;
	private JTextField nome;
	private ButtonGroup gpButton;
	private JRadioButton rdbtnFeminino, rdbtnMasculino;
	private JComboBox<Integer> comboBox;
	private JButton btnCadastrar;
	private GroupLayout gl_panel;
	// Variaveis de validação (só cadastra se estiverem "true")
	private Boolean nomeValido, sexoValido, idadeValida;

	public TelaCadastrarSaudavel(Hospital hospital, PessoaTableModel pessoaTableModel, JLabel qtdDadosRecuperados) {
		setTitle("Cadastrar Saudável");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1045, 462);
		setLocation(0, 565);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		lblNome = new JLabel("Nome:");
		lblSexo = new JLabel("Sexo:");
		lblIdade = new JLabel("Idade:");

		nome = new JTextField();
		nome.setColumns(10);

		gpButton = new ButtonGroup();

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addMouseListener(new MouseAdapter() {
			// Criado para validar ao clicar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnFeminino.setSelected(false);
				setNomeValido(Valida.nome(nome.getText().trim()));
			}
		});

		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.addMouseListener(new MouseAdapter() {
			// Criado para validar ao clicar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnMasculino.setSelected(false);
				setNomeValido(Valida.nome(nome.getText().trim()));
				setNomeValido(Valida.nome(nome.getText().trim()));
			}
		});

		gpButton.add(rdbtnMasculino);
		gpButton.add(rdbtnFeminino);

		comboBox = new JComboBox<Integer>();
		// Popula comboBox
		for (int i = 0; i <= 130; i++) {
			comboBox.addItem(i);
		}

		comboBox.addMouseListener(new MouseAdapter() {
			// Criado para validar ao clicar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setNomeValido(Valida.nome(nome.getText().trim()));
				setSexoValido(Valida.sexo(rdbtnMasculino, rdbtnFeminino));
			}
		});

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addMouseListener(new MouseAdapter() {
			// Dispara criação de objeto, se todos campos válidos
			@Override
			public void mouseClicked(MouseEvent a) {
				setNomeValido(Valida.nome(nome.getText().trim()));
				setSexoValido(Valida.sexo(rdbtnMasculino, rdbtnFeminino));
				setIdadeValida(Valida.idade(comboBox.getSelectedIndex()));
				if (isNomeValido() && isSexoValido() && isIdadeValida()) {
					Valida.cadastraPessoa(hospital, pessoaTableModel, nome.getText().trim(),
							Valida.selecaoSexo(rdbtnMasculino, rdbtnFeminino), null, comboBox.getSelectedIndex());
					// Reseta os inputs
					nome.setText("");
					rdbtnMasculino.setSelected(false);
					rdbtnFeminino.setSelected(false);
					gpButton.clearSelection();
					comboBox.setSelectedIndex(0);
					// Seta número de dados recuperados
					String n = String.valueOf(pessoaTableModel.getRowCount());
					qtdDadosRecuperados.setText(n + " Dados recuperados");
					qtdDadosRecuperados.setForeground(Color.RED);
					qtdDadosRecuperados.setVisible(true);
				}
			}
		});

		// Organiza os componentes swing no layout GroupModel
		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(245)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblIdade).addGroup(gl_panel
						.createSequentialGroup()
						.addGroup(gl_panel
								.createParallelGroup(Alignment.LEADING).addComponent(lblNome).addComponent(lblSexo))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(nome, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(Alignment.LEADING,
												gl_panel.createSequentialGroup().addComponent(rdbtnMasculino).addGap(18)
														.addComponent(rdbtnFeminino))
										.addComponent(btnCadastrar, Alignment.LEADING)))))
				.addContainerGap(338, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(42)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNome).addComponent(
								nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblSexo)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnMasculino)
										.addComponent(rdbtnFeminino)))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblIdade).addComponent(
								comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addGap(31).addComponent(btnCadastrar).addContainerGap(202, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
	}

	public boolean isNomeValido() {
		return nomeValido;
	}

	public boolean isSexoValido() {
		return sexoValido;
	}

	public boolean isIdadeValida() {
		return idadeValida;
	}

	public void setNomeValido(boolean nomeValido) {
		this.nomeValido = nomeValido;
	}

	public void setSexoValido(boolean sexoValido) {
		this.sexoValido = sexoValido;
	}

	public void setIdadeValida(boolean idadeValida) {
		this.idadeValida = idadeValida;
	}
}
