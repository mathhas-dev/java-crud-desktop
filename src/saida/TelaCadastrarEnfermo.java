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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastrarEnfermo extends JFrame {

	private JPanel contentPane, panel;
	private JLabel lblNome, lblSexo, lblSituao;
	private JTextField nome;
	private ButtonGroup gpButtonSexo, gpButtonSituacao;
	private JRadioButton rdbtnMasculino, rdbtnFeminino, rdbtnCurado, rdbtnEmTratamento, rdbtnFalecido;
	private JButton btnCadastrar;
	private GroupLayout gl_panel;
	// Variaveis de validação (só cadastra se estiverem "true")
	private Boolean nomeValido, sexoValido, situacaoValida;

	public TelaCadastrarEnfermo(Hospital hospital, PessoaTableModel pessoaTableModel, JLabel qtdDadosRecuperados) {
		setTitle("Cadastrar Enfermo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1045, 462);
		setLocation(900, 565);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		lblNome = new JLabel("Nome:");
		lblSexo = new JLabel("Sexo:");
		lblSituao = new JLabel("Situação:");

		nome = new JTextField();
		nome.setColumns(10);

		gpButtonSexo = new ButtonGroup();

		rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.addMouseListener(new MouseAdapter() {
			// Criado para disparar o validar ao clicar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnFeminino.setSelected(false);
				setNomeValido(Valida.nome(nome.getText().trim()));
			}
		});

		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.addMouseListener(new MouseAdapter() {
			// Criado para disparar o validar ao clicar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnMasculino.setSelected(false);
				setNomeValido(Valida.nome(nome.getText().trim()));
			}
		});

		gpButtonSexo.add(rdbtnMasculino);
		gpButtonSexo.add(rdbtnFeminino);

		gpButtonSituacao = new ButtonGroup();

		rdbtnCurado = new JRadioButton("Curado");
		rdbtnCurado.addMouseListener(new MouseAdapter() {
			// Criado para disparar o validar ao clicar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnEmTratamento.setSelected(false);
				rdbtnFalecido.setSelected(false);
				setNomeValido(Valida.nome(nome.getText().trim()));
				setSexoValido(Valida.sexo(rdbtnMasculino, rdbtnFeminino));
			}
		});

		rdbtnEmTratamento = new JRadioButton("Em tratamento");
		rdbtnEmTratamento.addMouseListener(new MouseAdapter() {
			// Criado para disparar o validar ao clicar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnCurado.setSelected(false);
				rdbtnFalecido.setSelected(false);
				setNomeValido(Valida.nome(nome.getText().trim()));
				setSexoValido(Valida.sexo(rdbtnMasculino, rdbtnFeminino));
			}
		});

		rdbtnFalecido = new JRadioButton("Falecido");
		rdbtnFalecido.addMouseListener(new MouseAdapter() {
			// Criado para disparar o validar ao clicar
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnCurado.setSelected(false);
				rdbtnEmTratamento.setSelected(false);
				setNomeValido(Valida.nome(nome.getText().trim()));
				setSexoValido(Valida.sexo(rdbtnMasculino, rdbtnFeminino));
			}
		});

		gpButtonSituacao.add(rdbtnCurado);
		gpButtonSituacao.add(rdbtnEmTratamento);
		gpButtonSituacao.add(rdbtnFalecido);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addMouseListener(new MouseAdapter() {
			// Dispara criação de objeto, se todos campos válidos
			@Override
			public void mouseClicked(MouseEvent e) {
				setNomeValido(Valida.nome(nome.getText().trim()));
				setSexoValido(Valida.sexo(rdbtnMasculino, rdbtnFeminino));
				setSituacaoValida(Valida.situacao(rdbtnCurado, rdbtnEmTratamento, rdbtnFalecido));
				if (isNomeValido() && isSexoValido() && isSituacaoValida()) {
					Valida.cadastraPessoa(hospital, pessoaTableModel, nome.getText().trim(),
							Valida.selecaoSexo(rdbtnMasculino, rdbtnFeminino),
							Valida.selecaoSituacao(rdbtnCurado, rdbtnEmTratamento, rdbtnFalecido), null);
					// Reseta os dados
					nome.setText("");
					rdbtnMasculino.setSelected(false);
					rdbtnFeminino.setSelected(false);
					gpButtonSexo.clearSelection();
					rdbtnCurado.setSelected(false);
					rdbtnEmTratamento.setSelected(false);
					rdbtnFalecido.setSelected(false);
					gpButtonSituacao.clearSelection();
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
				.createSequentialGroup().addGap(274)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblSituao))
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblNome)
										.addComponent(lblSexo))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(nome, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup().addGap(6).addGroup(gl_panel
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(rdbtnMasculino).addComponent(rdbtnCurado))
														.addGap(12)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel.createSequentialGroup()
																		.addComponent(rdbtnEmTratamento).addGap(18)
																		.addComponent(rdbtnFalecido))
																.addComponent(rdbtnFeminino)))
												.addComponent(btnCadastrar))))))
				.addContainerGap(326, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(31)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNome).addComponent(
								nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblSexo)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnFeminino)
										.addComponent(rdbtnMasculino)))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblSituao)
								.addComponent(rdbtnEmTratamento).addComponent(rdbtnFalecido).addComponent(rdbtnCurado))
						.addGap(18).addComponent(btnCadastrar).addContainerGap(223, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

	}

	public boolean isNomeValido() {
		return nomeValido;
	}

	public boolean isSexoValido() {
		return sexoValido;
	}

	public boolean isSituacaoValida() {
		return situacaoValida;
	}

	public void setNomeValido(boolean nomeValido) {
		this.nomeValido = nomeValido;
	}

	public void setSexoValido(boolean sexoValido) {
		this.sexoValido = sexoValido;
	}

	public void setSituacaoValida(boolean situacaoValida) {
		this.situacaoValida = situacaoValida;
	}

}
