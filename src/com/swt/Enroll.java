package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Enroll {

	protected Shell shl_Enroll;
	private Text txt_id;
	private Text txt_psw1;
	private Text txt_psw2;
	private Text txt_name;
	private Text txt_phone;
	private Text txt_ppsw;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Enroll window = new Enroll();
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
		shl_Enroll.open();
		shl_Enroll.layout();
		shl_Enroll.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_Enroll.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_Enroll.getSize().y/2);
		while (!shl_Enroll.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_Enroll = new Shell();
		shl_Enroll.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_Enroll.setSize(900, 500);
		shl_Enroll.setText("注册界面");
		
		Label lbl_id = new Label(shl_Enroll, SWT.NONE);
		lbl_id.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_id.setBounds(228, 63, 106, 20);
		lbl_id.setText("请输入账号：");
		
		Label lbl_psw1 = new Label(shl_Enroll, SWT.NONE);
		lbl_psw1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_psw1.setBounds(228, 98, 106, 20);
		lbl_psw1.setText("请输入密码：");
		
		Label lbl_psw2 = new Label(shl_Enroll, SWT.NONE);
		lbl_psw2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_psw2.setBounds(228, 134, 106, 20);
		lbl_psw2.setText("再次确认密码：");
		
		txt_id = new Text(shl_Enroll, SWT.BORDER);
		txt_id.setBounds(351, 60, 220, 26);
		
		txt_psw1 = new Text(shl_Enroll, SWT.BORDER | SWT.PASSWORD);
		txt_psw1.setBounds(351, 95, 220, 26);
		
		txt_psw2 = new Text(shl_Enroll, SWT.BORDER | SWT.PASSWORD);
		txt_psw2.setBounds(351, 131, 220, 26);
		
		Label lbl_tips = new Label(shl_Enroll, SWT.NONE);
		lbl_tips.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips.setBounds(610, 69, 237, 36);
		
		Button button = new Button(shl_Enroll, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!txt_psw1.getText().equals(txt_psw2.getText()))
				{
					lbl_tips.setText("两次密码不相同，请重新输入");
				}
				else
				{
					try {
						String[] temp = {txt_name.getText(),txt_id.getText(),txt_psw1.getText(),txt_phone.getText(),txt_ppsw.getText()};
						try {
							lbl_tips.setText(DBM.addUser(temp));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button.setBounds(326, 271, 98, 30);
		button.setText("确认注册");
		
		Button button_1 = new Button(shl_Enroll, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_name.setText("");
				txt_id.setText("");
				txt_psw1.setText("");
				txt_psw2.setText("");
				txt_phone.setText("");
				txt_ppsw.setText("");
				
			}
		});
		button_1.setBounds(458, 271, 98, 30);
		button_1.setText("重新输入");
		
		Button btnNewButton = new Button(shl_Enroll, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_psw1.setEchoChar((char)0);
				txt_psw2.setEchoChar((char)0);
			}
		});
		btnNewButton.setBounds(590, 134, 98, 30);
		btnNewButton.setText("显示密码");
		
		Label lbl_name = new Label(shl_Enroll, SWT.NONE);
		lbl_name.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_name.setBounds(228, 29, 106, 28);
		lbl_name.setText("请输入用户名：");
		
		txt_name = new Text(shl_Enroll, SWT.BORDER);
		txt_name.setBounds(351, 26, 220, 26);
		
		txt_phone = new Text(shl_Enroll, SWT.BORDER);
		txt_phone.setBounds(351, 168, 220, 26);
		
		Label lbl_phone = new Label(shl_Enroll, SWT.NONE);
		lbl_phone.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_phone.setBounds(228, 171, 106, 26);
		lbl_phone.setText("请输入手机号：");
		
		Button btn_recover = new Button(shl_Enroll, SWT.NONE);
		btn_recover.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_Enroll.dispose();
				ULogin window = new ULogin();
				window.open();
			}
		});
		btn_recover.setBounds(590, 271, 98, 30);
		btn_recover.setText("返回登陆");
		
		Label lbl_ppsw = new Label(shl_Enroll, SWT.NONE);
		lbl_ppsw.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_ppsw.setBounds(228, 203, 117, 20);
		lbl_ppsw.setText("请输入支付密码：");
		
		txt_ppsw = new Text(shl_Enroll, SWT.BORDER);
		txt_ppsw.setBounds(351, 200, 220, 26);
		



}
}
