package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import com.DAO.DataSource;
import com.DAO.UsuarioDAO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;

public class TelaLogin {

	static JFrame frmLogin;
	private JTextField textFielLogin;
	private JLabel lblLogin;
	private JLabel lblSenha;
	static TelaLogin window;
	private JPasswordField passwordFieldSenha;
	private JLabel lblAcessoAoSistema;
	private JButton btnEntrar;
	public static final int SCREEN_POSITION_X = 500;
	public static final int SCREEN_POSITION_Y = 250;

	// INICIA A APLICAÇÃO
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					window = new TelaLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// FUNÇÃO QUE FECHA A JANELA
	public void CloseWindow() {
		frmLogin.dispose();
	}

	// CRIA E INICIA A APLICAÇÃO
	public TelaLogin() {
		initialize();
	}

	// INICIALIZA OS COMPONENTES DO FRAME (TELA)
	private void initialize() {
		textFielLogin = new JTextField();
		textFielLogin.setColumns(10);
		textFielLogin.setBounds(147, 106, 166, 20);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(224, 168, 89, 23);

		lblLogin = new JLabel("Login");
		lblLogin.setBounds(101, 109, 46, 14);

		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(101, 140, 46, 14);

		lblAcessoAoSistema = new JLabel("Acesso ao Sistema");
		lblAcessoAoSistema.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		lblAcessoAoSistema.setBounds(147, 50, 196, 27);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(147, 137, 166, 20);

		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(SCREEN_POSITION_X, SCREEN_POSITION_Y, 450, 300);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setResizable(false);
		frmLogin.getContentPane().add(btnEntrar);
		frmLogin.getContentPane().add(lblLogin);
		frmLogin.getContentPane().add(lblSenha);
		frmLogin.getContentPane().add(lblAcessoAoSistema);
		frmLogin.getContentPane().add(passwordFieldSenha);
		frmLogin.getContentPane().add(textFielLogin);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataSource datasource = new DataSource();
				UsuarioDAO dao = new UsuarioDAO(datasource);
				String login = textFielLogin.getText();
				int senha = Integer.parseInt(String.valueOf(passwordFieldSenha.getPassword()));
				if (dao.checkLogin(login, senha)) {
					int tipo = dao.checkTipo(login, senha);
					System.out.println(tipo);
					JOptionPane.showMessageDialog(frmLogin, "Bem vindo", "Menssagem", 2);
					if (tipo == 1) {
						TelaAdmin admin = new TelaAdmin();
						admin.run();
					}
					if (tipo == 2) {
						TelaUser user = new TelaUser();
						user.run();
					} else {

					}
					CloseWindow();
				} else {
					JOptionPane.showMessageDialog(frmLogin, "Erro ao fazer Login", "ERRO!", 0);
				}
			}
		});
	}
}