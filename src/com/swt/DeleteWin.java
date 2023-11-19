package com.swt;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.smsystem.entity.*;
public class DeleteWin {

	protected Shell shl_DeleteWin;
	private Text txt_name;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DeleteWin window = new DeleteWin();
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
		shl_DeleteWin.open();
		shl_DeleteWin.layout();
		shl_DeleteWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_DeleteWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_DeleteWin.getSize().y/2);
		while (!shl_DeleteWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_DeleteWin = new Shell();
		shl_DeleteWin.setSize(458, 306);
		shl_DeleteWin.setText("删除商品");
		
		Label lbl_name = new Label(shl_DeleteWin, SWT.NONE);
		lbl_name.setBounds(53, 93, 117, 20);
		lbl_name.setText("不想要的商品名：");
		
		txt_name = new Text(shl_DeleteWin, SWT.BORDER);
		txt_name.setBounds(202, 90, 153, 26);
		
		Button btn_ = new Button(shl_DeleteWin, SWT.NONE);
		btn_.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Connection ct = null;
				ResultSet rs = null;
				Statement st = null;
				try {
					SMS_USER user = new SMS_USER();
					String logName = user.getLogname();
					String name = txt_name.getText();
					ct = DBM.getcon();
					st = ct.createStatement();
					st.executeUpdate("delete from SMS_SHCART where CAR_BELONG = '"+logName+"' and CAR_NAME = '"+name+"'");
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {
					DBM.getclose(ct, null, rs);
					shl_DeleteWin.close();
				}
			}
		});
		btn_.setBounds(156, 165, 125, 45);
		btn_.setText("确认删除");

	}
}
