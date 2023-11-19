package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;


import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.smsystem.entity.*;
public class BLogin {

	protected Shell shl_Blogin;
	private Text txt_id;
	private Text txt_psw;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BLogin window = new BLogin();
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
		shl_Blogin.open();
		shl_Blogin.layout();
		shl_Blogin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_Blogin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_Blogin.getSize().y/2);
		while (!shl_Blogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_Blogin = new Shell();
		shl_Blogin.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_Blogin.setSize(928, 503);
		shl_Blogin.setText("SWT Application");
		
		Label lbl_title = new Label(shl_Blogin, SWT.NONE);
		lbl_title.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20, SWT.NORMAL));
		lbl_title.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_title.setAlignment(SWT.CENTER);
		lbl_title.setBounds(214, 41, 435, 60);
		lbl_title.setText("商城系统（商家模块）");
		
		Label lbl_id = new Label(shl_Blogin, SWT.NONE);
		lbl_id.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_id.setBounds(213, 136, 99, 20);
		lbl_id.setText("请输入账号：");
		
		Label lbl_psw = new Label(shl_Blogin, SWT.NONE);
		lbl_psw.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_psw.setBounds(214, 179, 105, 20);
		lbl_psw.setText("请输入密码：");
		
		txt_id = new Text(shl_Blogin, SWT.BORDER);
		txt_id.setBounds(332, 133, 287, 26);
		
		txt_psw = new Text(shl_Blogin, SWT.BORDER | SWT.PASSWORD);
		txt_psw.setBounds(332, 179, 287, 26);
		
		Label lbl_tips = new Label(shl_Blogin, SWT.NONE);
		lbl_tips.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips.setBounds(655, 136, 216, 20);
		
		Button btn_login = new Button(shl_Blogin, SWT.NONE);
		btn_login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String acc = txt_id.getText();
				String psw = txt_psw.getText();
				try {
					if(DBM.getBAuth(acc, psw))
					{
						String sql = "SELECT * FROM SMS_BUSINESS WHERE BSS_ID ='"+acc+"';";
						Keeper keeper = new Keeper();
						keeper.setLogKeeper(DBM.getUName(sql));
						shl_Blogin.dispose();
						//切入商家页面
						ShopHomepageWin window = new ShopHomepageWin();
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
			}
		});
		btn_login.setBounds(332, 246, 98, 30);
		btn_login.setText("登陆");
		
		Button btn_enroll = new Button(shl_Blogin, SWT.NONE);
		btn_enroll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_Blogin.dispose();
				BEnroll window = new BEnroll();
				window.open();
			}
		});
		btn_enroll.setBounds(521, 246, 98, 30);
		btn_enroll.setText("注册");
		
		Button button = new Button(shl_Blogin, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_Blogin.dispose();
				ULogin window = new ULogin();
				window.open();
			}
		});
		button.setBounds(84, 55, 98, 30);
		button.setText("<普通用户");
		
	

	}
}
