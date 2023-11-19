package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ShopDeleteWin {

	protected Shell shl_shopDelWin;
	private Text txt_id;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ShopDeleteWin window = new ShopDeleteWin();
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
		shl_shopDelWin.open();
		shl_shopDelWin.layout();
		shl_shopDelWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_shopDelWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_shopDelWin.getSize().y/2);
		
		Button btn_confirm = new Button(shl_shopDelWin, SWT.NONE);
		btn_confirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Connection ct = null;
				ResultSet rs = null;
				Statement st = null;
				try {
					String id = txt_id.getText();
					ct = DBM.getcon();
					st = ct.createStatement();
					st.executeUpdate("delete from SMS_PRODUCTS where PDT_ID = '"+id+"'");
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {
					MessageBox mb = new MessageBox(shl_shopDelWin,SWT.NONE);
					mb.setText("提示");
					mb.setMessage("下架成功！");
					//打开提示框
					mb.open();
					shl_shopDelWin.close();
					DBM.getclose(ct, null, rs);
				}
			}
		});
		btn_confirm.setBounds(155, 183, 98, 30);
		btn_confirm.setText("确认下架");
		
		Label lbl_id = new Label(shl_shopDelWin, SWT.NONE);
		lbl_id.setBounds(40, 96, 105, 20);
		lbl_id.setText("下架商品编号：");
		
		txt_id = new Text(shl_shopDelWin, SWT.BORDER);
		txt_id.setBounds(161, 96, 153, 26);
		while (!shl_shopDelWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_shopDelWin = new Shell();
		shl_shopDelWin.setSize(450, 300);
		shl_shopDelWin.setText("下架商品");

	}

}
