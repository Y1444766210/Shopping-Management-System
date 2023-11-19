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
import com.smsystem.entity.*;
public class Findpsw2 {

	protected Shell shl_fdpsw2;
	private Text txt_newpsw1;
	private Text txt_newpsw2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Findpsw2 window = new Findpsw2();
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
		shl_fdpsw2.open();
		shl_fdpsw2.layout();
		shl_fdpsw2.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_fdpsw2.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_fdpsw2.getSize().y/2);
		while (!shl_fdpsw2.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_fdpsw2 = new Shell();
		shl_fdpsw2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_fdpsw2.setSize(1024, 473);
		shl_fdpsw2.setText("Findpsw2");
		
		Label lblNewLabel = new Label(shl_fdpsw2, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel.setBounds(250, 71, 114, 20);
		lblNewLabel.setText("请输入新密码：");
		
		Label label = new Label(shl_fdpsw2, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label.setBounds(250, 112, 114, 20);
		label.setText("请再次确认密码：");
		
		txt_newpsw1 = new Text(shl_fdpsw2, SWT.BORDER);
		txt_newpsw1.setBounds(393, 71, 270, 26);
		
		txt_newpsw2 = new Text(shl_fdpsw2, SWT.BORDER);
		txt_newpsw2.setBounds(393, 112, 270, 26);
		
		Label lbl_tips = new Label(shl_fdpsw2, SWT.NONE);
		lbl_tips.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips.setBounds(739, 112, 206, 20);

		
		Button button = new Button(shl_fdpsw2, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SMS_USER user = new SMS_USER();
				if(txt_newpsw1.getText().equals(txt_newpsw2.getText()))
				{
					try {
						String sql = "UPDATE SMS_USER SET USER_PSW = '"+txt_newpsw1.getText()+"' WHERE USER_PHONE = '"+user.getphone()+"';";
						DBM.changepsw(sql);
						lbl_tips.setText("修改成功");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					lbl_tips.setText("两次密码不同，请重新输入");
				}
				
			}
		});
		button.setBounds(312, 185, 98, 30);
		button.setText("确认修改");
		
		Button button_1 = new Button(shl_fdpsw2, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_newpsw1.setText("");
				txt_newpsw2.setText("");
			}
		});
		button_1.setBounds(450, 185, 98, 30);
		button_1.setText("重新输入");
		
		Button button_2 = new Button(shl_fdpsw2, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_fdpsw2.dispose();
				ULogin window = new ULogin();
				window.open();
			}
		});
		button_2.setBounds(582, 185, 98, 30);
		button_2.setText("返回登陆");
		
		
	}

}
