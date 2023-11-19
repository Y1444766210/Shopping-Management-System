package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.smsystem.entity.*;

public class ULogin {

	protected Shell shl_Login;
	private Text text;
	private Text text_username;
	private Text text_password;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ULogin window = new ULogin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shl_Login.open();
		shl_Login.layout();
		shl_Login.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_Login.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_Login.getSize().y/2);
		while (!shl_Login.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_Login = new Shell();
		shl_Login.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_Login.setSize(900, 500);
		shl_Login.setText("Login");
		
		text = new Text(shl_Login, SWT.CENTER);
		text.setBackground(SWTResourceManager.getColor(255, 255, 255));
		text.setForeground(SWTResourceManager.getColor(0, 0, 64));
		text.setFont(SWTResourceManager.getFont("宋体", 15, SWT.NORMAL));
		text.setText("商城系统");
		text.setBounds(248, 51, 300, 32);
		
		Label label = new Label(shl_Login, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label.setBounds(209, 117, 76, 20);
		label.setText("用户名：");
		
		Label label_1 = new Label(shl_Login, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_1.setBounds(209, 162, 76, 20);
		label_1.setText("密    码：");
		
		text_username = new Text(shl_Login, SWT.BORDER);
		text_username.setBounds(291, 114, 237, 26);
		
		text_password = new Text(shl_Login, SWT.BORDER | SWT.PASSWORD);
		text_password.setBounds(291, 159, 237, 26);
		
		Label lbl_tips = new Label(shl_Login, SWT.NONE);
		lbl_tips.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips.setBounds(571, 85, 185, 14);
		
		Button button = new Button(shl_Login, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String acc = text_username.getText();
				String psw = text_password.getText();
				try {
					if(DBM.getAuth(acc, psw))
					{
						SMS_USER user = new SMS_USER();
						String sql = "SELECT * FROM SMS_USER WHERE USER_ID ='"+acc+"';";
						user.setLogname(DBM.getUName(sql));
						shl_Login.dispose();
						Main window = new Main();
						window.open();
					}
					else
					{
						lbl_tips.setText("账号或密码有误，请重新输入");
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
		}});
		button.setBounds(292, 210, 98, 30);
		button.setText("登陆");
		
		Button button_1 = new Button(shl_Login, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_Login.dispose();
				Enroll window = new Enroll();
				window.open();
			}
		});
		button_1.setBounds(430, 210, 98, 30);
		button_1.setText("注册");
		
		Button button_2 = new Button(shl_Login, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_Login.dispose();
				Findpsw1 window = new Findpsw1();
				window.open();
			}
		});
		button_2.setForeground(SWTResourceManager.getColor(0, 0, 255));
		button_2.setBounds(571, 162, 98, 30);
		button_2.setText("忘记密码？");
		
		Button button_3 = new Button(shl_Login, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_Login.dispose();
				BLogin window = new BLogin();
				window.open();
			}
		});
		button_3.setBounds(571, 114, 98, 30);
		button_3.setText("商家登陆->");

		
		

	}
}
