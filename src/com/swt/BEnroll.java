package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BEnroll {

	protected Shell shl_benroll;
	private Text txt_name;
	private Text txt_id;
	private Text txt_psw1;
	private Text txt_psw2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BEnroll window = new BEnroll();
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
		shl_benroll.open();
		shl_benroll.layout();
		shl_benroll.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_benroll.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_benroll.getSize().y/2);
		while (!shl_benroll.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_benroll = new Shell();
		shl_benroll.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_benroll.setSize(942, 483);
		shl_benroll.setText("BEnroll");
		
		Label label = new Label(shl_benroll, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label.setBounds(257, 96, 111, 20);
		label.setText("请输入店铺名：");
		
		Label label_1 = new Label(shl_benroll, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_1.setBounds(257, 140, 97, 20);
		label_1.setText("请输入账号：");
		
		Label label_2 = new Label(shl_benroll, SWT.NONE);
		label_2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_2.setBounds(257, 185, 97, 20);
		label_2.setText("请输入密码：");
		
		txt_name = new Text(shl_benroll, SWT.BORDER);
		txt_name.setBounds(385, 96, 207, 26);
		
		txt_id = new Text(shl_benroll, SWT.BORDER);
		txt_id.setBounds(385, 137, 207, 26);
		
		txt_psw1 = new Text(shl_benroll, SWT.BORDER | SWT.PASSWORD);
		txt_psw1.setBounds(385, 182, 207, 26);
		
		Label lbl_tips = new Label(shl_benroll, SWT.NONE);
		lbl_tips.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips.setBounds(663, 229, 184, 20);

		
		Button btn_enroll = new Button(shl_benroll, SWT.NONE);
		btn_enroll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!txt_psw1.getText().equals(txt_psw2.getText()))
				{
					lbl_tips.setText("两次密码不相同，请重新输入");
				}
				else
				{
					try {
						String[] temp = {txt_name.getText(),txt_id.getText(),txt_psw1.getText()};
						try {
							lbl_tips.setText(DBM.addBUser(temp));
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
		btn_enroll.setBounds(319, 277, 98, 30);
		btn_enroll.setText("确认注册");
		
		Button btn_clean = new Button(shl_benroll, SWT.NONE);
		btn_clean.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_name.setText("");
				txt_id.setText("");
				txt_psw1.setText("");
				txt_psw2.setText("");
			}
		});
		btn_clean.setBounds(439, 277, 98, 30);
		btn_clean.setText("重新输入");
		
		Label label_3 = new Label(shl_benroll, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_3.setBounds(257, 229, 120, 20);
		label_3.setText("请再次确认密码：");
		
		txt_psw2 = new Text(shl_benroll, SWT.BORDER | SWT.PASSWORD);
		txt_psw2.setBounds(386, 223, 206, 26);
		
		Button button = new Button(shl_benroll, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_benroll.dispose();
				BLogin window = new BLogin();
				window.open();
			}
		});
		button.setBounds(567, 277, 98, 30);
		button.setText("返回登陆");
		
	}
}
