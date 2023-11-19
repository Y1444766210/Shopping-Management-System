package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.smsystem.entity.*;
public class ChangeInfo {

	protected Shell shl_change;
	private Text txt_newname1;
	private Text txt_newname2;
	private Text txt_newpsw1;
	private Text txt_newpsw2;
	private Text txt_newppsw1;
	private Text txt_newppsw2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ChangeInfo window = new ChangeInfo();
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
		shl_change.open();
		shl_change.layout();
		shl_change.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_change.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_change.getSize().y/2);
		while (!shl_change.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_change = new Shell();
		shl_change.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_change.setSize(716, 442);
		shl_change.setText("ChangeInfo");
		
		Label lbl_tips1 = new Label(shl_change, SWT.NONE);
		lbl_tips1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips1.setBounds(280, 132, 270, 20);
		
		Label lbl_tips2 = new Label(shl_change, SWT.NONE);
		lbl_tips2.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips2.setBounds(32, 304, 266, 20);
		
		Label lbl_tips3 = new Label(shl_change, SWT.NONE);
		lbl_tips3.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips3.setBounds(364, 304, 318, 20);
		
		
		SMS_USER user = new SMS_USER();
		
		Button button = new Button(shl_change, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(txt_newname1.getText().equals(txt_newname2.getText()))
				{
					String sql1 = "UPDATE SMS_USER SET USER_NAME = '"+txt_newname1.getText()+"' WHERE USER_NAME = '"+user.getLogname()+"';";
					try {
						DBM.changepsw(sql1);
						lbl_tips1.setText("修改成功");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					lbl_tips1.setText("两次用户名不相同，请重新输入");
					txt_newname1.setText("");
					txt_newname2.setText("");
				}
			}
		});
		button.setBounds(452, 65, 98, 30);
		button.setText("修改用户名");
		
		Button button_1 = new Button(shl_change, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(txt_newpsw1.getText().equals(txt_newpsw2.getText()))
				{
					String sql2 = "UPDATE SMS_USER SET USER_PSW = '"+txt_newpsw1.getText()+"' WHERE USER_NAME = '"+user.getLogname()+"';";
					try {
						DBM.changepsw(sql2);
						lbl_tips2.setText("修改成功");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					lbl_tips2.setText("两次密码不相同，请重新输入");
					txt_newpsw1.setText("");
					txt_newpsw2.setText("");
				}
				
			}
		});
		button_1.setBounds(107, 337, 98, 30);
		button_1.setText("修改密码");
		
		Label label = new Label(shl_change, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label.setBounds(154, 39, 120, 20);
		label.setText("请输入新用户名：");
		
		txt_newname1 = new Text(shl_change, SWT.BORDER);
		txt_newname1.setBounds(280, 39, 156, 26);
		
		Label label_1 = new Label(shl_change, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_1.setBounds(154, 86, 120, 20);
		label_1.setText("确认新用户名：");
		
		txt_newname2 = new Text(shl_change, SWT.BORDER);
		txt_newname2.setBounds(280, 83, 156, 26);
		
		Label label_2 = new Label(shl_change, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(0, 175, 697, 2);
		
		Label label_3 = new Label(shl_change, SWT.NONE);
		label_3.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_3.setBounds(32, 202, 120, 20);
		label_3.setText("请输入新密码：");
		
		txt_newpsw1 = new Text(shl_change, SWT.BORDER);
		txt_newpsw1.setBounds(154, 202, 144, 26);
		
		Label label_4 = new Label(shl_change, SWT.NONE);
		label_4.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_4.setBounds(32, 268, 98, 20);
		label_4.setText("请确认新密码：");
		
		txt_newpsw2 = new Text(shl_change, SWT.BORDER);
		txt_newpsw2.setBounds(154, 262, 144, 26);
		
		Label label_5 = new Label(shl_change, SWT.SEPARATOR | SWT.VERTICAL);
		label_5.setBounds(334, 175, 2, 222);
		
		Label label_6 = new Label(shl_change, SWT.NONE);
		label_6.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_6.setBounds(364, 202, 135, 20);
		label_6.setText("请输入新支付密码：");
		
		txt_newppsw1 = new Text(shl_change, SWT.BORDER);
		txt_newppsw1.setBounds(505, 202, 177, 26);
		
		Label label_7 = new Label(shl_change, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_7.setBounds(364, 268, 135, 20);
		label_7.setText("请确认新支付密码：");
		
		txt_newppsw2 = new Text(shl_change, SWT.BORDER);
		txt_newppsw2.setBounds(505, 262, 177, 26);
		
		Button button_2 = new Button(shl_change, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(txt_newppsw1.getText().equals(txt_newppsw2.getText()))
				{
					String sql3 = "UPDATE SMS_USER SET USER_PPSW = '"+txt_newppsw1.getText()+"' WHERE USER_NAME = '"+user.getLogname()+"';";
					try {
						DBM.changepsw(sql3);
						lbl_tips3.setText("修改成功");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					lbl_tips3.setText("两次支付密码不相同，请重新输入");
					txt_newppsw1.setText("");
					txt_newppsw2.setText("");
				}
				
			}
		});
		button_2.setBounds(465, 337, 98, 30);
		button_2.setText("修改支付密码");
		
		Button button_3 = new Button(shl_change, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_change.dispose();
				Main window = new Main();
				window.open();
			}
		});
		button_3.setBounds(0, 0, 98, 30);
		button_3.setText("返回首页");
		
		

	}
}
