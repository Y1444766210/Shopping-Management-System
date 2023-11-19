package com.swt;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.smsystem.entity.*;
public class ListManagementWin {

	protected Shell shl_listManagementWin;
	private Text txt_name;
	private Text txt_belong;
	private Text txt_condition;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListManagementWin window = new ListManagementWin();
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
		shl_listManagementWin.open();
		shl_listManagementWin.layout();
		shl_listManagementWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_listManagementWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_listManagementWin.getSize().y/2);
		
		Label lbl_name = new Label(shl_listManagementWin, SWT.NONE);
		lbl_name.setBounds(60, 37, 135, 20);
		lbl_name.setText("想要更改的货物名：");
		
		txt_name = new Text(shl_listManagementWin, SWT.BORDER);
		txt_name.setBounds(201, 34, 155, 26);
		
		Label lbl_belong = new Label(shl_listManagementWin, SWT.NONE);
		lbl_belong.setBounds(60, 90, 99, 20);
		lbl_belong.setText("货物所属用户：");
		
		txt_belong = new Text(shl_listManagementWin, SWT.BORDER);
		txt_belong.setBounds(201, 90, 155, 26);
		
		Label lbl_condition = new Label(shl_listManagementWin, SWT.NONE);
		lbl_condition.setBounds(60, 146, 99, 20);
		lbl_condition.setText("状态更改为：");
		
		txt_condition = new Text(shl_listManagementWin, SWT.BORDER);
		txt_condition.setBounds(201, 143, 155, 26);
		
		Button btn_confirm = new Button(shl_listManagementWin, SWT.NONE);
		btn_confirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Connection ct = null;
				ResultSet rs = null;
				Statement st = null;
				try {
					Keeper keeper = new Keeper();
					String kname = keeper.getLogKeeper();
					String gname = txt_name.getText();
					String cdt = txt_condition.getText();
					String uname = txt_belong.getText();
					ct = DBM.getcon();
					st = ct.createStatement();
					st.executeUpdate("update SMS_LIST set LIST_CONDITION = '"+cdt+"' where LIST_NAME = '"+gname+"' and LIST_BELONG = '"+uname+"' and LIST_KEEPER = '"+kname+"'");
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {
					MessageBox mb = new MessageBox(shl_listManagementWin,SWT.NONE);
					mb.setText("提示");
					mb.setMessage("修改成功！");
					//打开提示框
					mb.open();
					shl_listManagementWin.close();
					DBM.getclose(ct, null, rs);
				}
			}
		});
		btn_confirm.setBounds(149, 207, 98, 30);
		btn_confirm.setText("确认更改");
		while (!shl_listManagementWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_listManagementWin = new Shell();
		shl_listManagementWin.setSize(450, 300);
		shl_listManagementWin.setText("管理订单");

	}
}

