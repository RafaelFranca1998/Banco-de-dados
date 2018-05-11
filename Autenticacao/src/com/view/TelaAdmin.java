package com.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.DAO.DataSource;
import com.DAO.UsuarioDAO;
import com.model.Admin;
import com.model.Usuario;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class TelaAdmin {
	TelaLogin window;
	private JFrame frmAdmin;
	private JLabel lblJanelaAdmin;
	private JTable jTable1;
	private JTextField textFieldSenha;
	private JTextField textFieldLogin;
	private JTextField textFieldCofirmPassw;
	private JRadioButton rdbtnAdministrador;
	private JRadioButton rdbtnUsuario;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private Admin admin;
	private JSeparator separator;
	private JButton btnRemoverUsurio;
	private JLabel lblLogin;
	private JLabel lblSenha;
	private JLabel lblConfirmeASenha;
	private JButton btnInserirUsurio;
	private JButton btnLogout;
	private DefaultTableModel modelo;

	public void CloseWindow() {
		frmAdmin.dispose();
	}

	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			TelaAdmin window = new TelaAdmin();
			window.frmAdmin.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public TelaAdmin() {
		initialize();
	}

	public void readJtable() {
		modelo = (DefaultTableModel) jTable1.getModel();
		DataSource ds = new DataSource();
		UsuarioDAO U = new UsuarioDAO(ds);

		for (Usuario user : U.ListarLogin()) {
			modelo.addRow(new Object[] { user.getId(), user.getLogin(), user.getSenha(), user.getTipo(), });
		}
	}

	public void clearJtable() {
		modelo = (DefaultTableModel) jTable1.getModel();
		modelo.setNumRows(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		lblLogin = new JLabel("Login");
		lblLogin.setBounds(36, 80, 46, 14);

		textFieldCofirmPassw = new JTextField();
		textFieldCofirmPassw.setBounds(36, 186, 179, 20);
		textFieldCofirmPassw.setColumns(10);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(36, 126, 46, 14);

		lblConfirmeASenha = new JLabel("Confirme a Senha");
		lblConfirmeASenha.setBounds(36, 173, 110, 14);

		rdbtnUsuario = new JRadioButton("Usu\u00E1rio");
		rdbtnUsuario.setBounds(36, 258, 109, 23);
		rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.setBounds(36, 232, 109, 23);

		textFieldSenha = new JTextField();
		textFieldSenha.setBounds(36, 142, 179, 20);
		textFieldSenha.setColumns(10);

		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(36, 95, 176, 20);
		textFieldLogin.setColumns(10);

		btnInserirUsurio = new JButton("Inserir Usu\u00E1rio");
		btnInserirUsurio.setBounds(88, 375, 138, 23);

		jTable1 = new JTable();

		javax.swing.JScrollPane jScrollPane1 = new JScrollPane(jTable1);
		jScrollPane1.setSize(290, 268);
		jScrollPane1.setLocation(313, 48);

		lblJanelaAdmin = new JLabel("Janela Admin");
		lblJanelaAdmin.setBounds(115, 15, 167, 23);
		lblJanelaAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));

		btnRemoverUsurio = new JButton("Remover Usu\u00E1rio");
		btnRemoverUsurio.setBounds(399, 352, 138, 23);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(552, 19, 89, 23);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(284, 48, 24, 379);

		separator_1 = new JSeparator();
		separator_1.setBounds(20, 47, 266, 8);

		separator_2 = new JSeparator();
		separator_2.setBounds(20, 425, 266, 2);

		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(20, 48, 6, 379);
		frmAdmin = new JFrame();
		frmAdmin.getContentPane().setLayout(null);
		frmAdmin.getContentPane().add(lblConfirmeASenha);
		frmAdmin.getContentPane().add(textFieldLogin);
		frmAdmin.getContentPane().add(textFieldSenha);
		frmAdmin.getContentPane().add(btnRemoverUsurio);
		frmAdmin.getContentPane().add(lblJanelaAdmin);
		frmAdmin.getContentPane().add(separator);
		frmAdmin.getContentPane().add(rdbtnUsuario);
		frmAdmin.getContentPane().add(rdbtnAdministrador);
		frmAdmin.getContentPane().add(btnLogout);
		frmAdmin.getContentPane().add(lblLogin);
		frmAdmin.getContentPane().add(textFieldCofirmPassw);
		frmAdmin.getContentPane().add(lblSenha);
		frmAdmin.getContentPane().add(separator_1);
		frmAdmin.getContentPane().add(separator_2);
		frmAdmin.getContentPane().add(separator_3);
		frmAdmin.getContentPane().add(btnInserirUsurio);
		frmAdmin.getContentPane().add(jScrollPane1);
		frmAdmin.setBounds(TelaLogin.SCREEN_POSITION_X, TelaLogin.SCREEN_POSITION_Y, 667, 499);
		frmAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Login", "Senha", "tipo" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] canEdit = new boolean[] { false, false, true, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(jTable1);
		
		btnLogout.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					TelaLogin Tela = new TelaLogin(); 
					Tela.window = new TelaLogin();
					window.frmLogin.setVisible(true);
					CloseWindow();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		readJtable();
		rdbtnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAdministrador.setSelected(false);
			}
		});
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnUsuario.setSelected(false);
			}
		});
		btnInserirUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin = new Admin();
				if (admin.comparePasswrd(textFieldSenha.getText(), textFieldCofirmPassw.getText()) == true) {
					JOptionPane.showMessageDialog(frmAdmin, "As Senhas não Conferem");
				} else {
					Usuario U = new Usuario();
					DataSource ds = new DataSource();
					UsuarioDAO user = new UsuarioDAO(ds);
					U.setLogin(textFieldLogin.getText());
					U.setSenha(Integer.parseInt(textFieldSenha.getText()));
					if (rdbtnAdministrador.isSelected()) {
						U.setTipo(1);
					} else {
						U.setTipo(2);
					}
					user.create(U);
					textFieldLogin.setText("");
					textFieldSenha.setText("");
					textFieldCofirmPassw.setText("");
					rdbtnAdministrador.setSelected(false);
					rdbtnUsuario.setSelected(false);
					clearJtable();
					readJtable();
				}
			}
		});
		btnRemoverUsurio.addActionListener(new ActionListener() {
			private JOptionPane pane;
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				DataSource ds = new DataSource();
				Usuario user = new Usuario();
				UsuarioDAO U = new UsuarioDAO(ds);
				Object coluna = jTable1.getValueAt(jTable1.getSelectedRow(), 0);
				int id = Integer.parseInt(coluna.toString());
				if (id == 1| id == 2) {
					JOptionPane.showMessageDialog(jTable1, "Impossivel Remover o Usuário Mestre", "ERRO!", 0);
				} else {
					if (pane.showConfirmDialog(jTable1,
							"Esta Ação não poderá ser desfeita! \n Deseja remover o Usuário da Lista?", "Atenção!",
							pane.YES_NO_OPTION) == 0) {
						System.out.println("Aqui o id" + id + pane.YES_OPTION);
						user.setId(Integer.parseInt(coluna.toString()));
						U.delete(user);
						clearJtable();
						readJtable();
					}
				}
			}
		});
	}
}
