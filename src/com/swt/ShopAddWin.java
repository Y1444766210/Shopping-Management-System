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
public class ShopAddWin {

	protected Shell shl_shopAddWin;
	private Text txt_id;
	private Text txt_name;
	private Text txt_value;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ShopAddWin window = new ShopAddWin();
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
		shl_shopAddWin.open();
		shl_shopAddWin.layout();
		shl_shopAddWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_shopAddWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_shopAddWin.getSize().y/2);
		
		Label lbl_id = new Label(shl_shopAddWin, SWT.NONE);
		lbl_id.setBounds(51, 49, 105, 20);
		lbl_id.setText("上架商品编号：");
		
		txt_id = new Text(shl_shopAddWin, SWT.BORDER);
		txt_id.setBounds(174, 49, 168, 26);
		
		Label lbl_name = new Label(shl_shopAddWin, SWT.NONE);
		lbl_name.setText("上架商品名称：");
		lbl_name.setBounds(51, 97, 105, 20);
		
		txt_name = new Text(shl_shopAddWin, SWT.BORDER);
		txt_name.setBounds(174, 97, 168, 26);
		
		Label lbl_value = new Label(shl_shopAddWin, SWT.NONE);
		lbl_value.setText("上架商品价格：");
		lbl_value.setBounds(51, 142, 105, 20);
		
		txt_value = new Text(shl_shopAddWin, SWT.BORDER);
		txt_value.setBounds(174, 142, 168, 26);
		
		Button btn_confirm = new Button(shl_shopAddWin, SWT.NONE);
		btn_confirm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Connection ct = null;
				ResultSet rs = null;
				Statement st = null;
				try {
					Keeper keeper = new Keeper();
					String kname = keeper.getLogKeeper();
					String id = txt_id.getText();
					String name = txt_name.getText();
					String value = txt_value.getText();
					ct = DBM.getcon();
					st = ct.createStatement();
					st.executeUpdate("insert into SMS_PRODUCTS(PDT_ID,PDT_NAME,PDT_PRICE,PDT_KEEPER) values('"+id+"','"+name+"','"+value+"','"+kname+"')");
				}catch(Exception e1) {
					e1.printStackTrace();
				}finally {
					MessageBox mb = new MessageBox(shl_shopAddWin,SWT.NONE);
					mb.setText("提示");
					mb.setMessage("上架成功！");
					//打开提示框
					mb.open();
					shl_shopAddWin.close();
					DBM.getclose(ct, null, rs);
				}
			}
		});
		btn_confirm.setBounds(160, 199, 98, 30);
		btn_confirm.setText("确认上架");
		while (!shl_shopAddWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_shopAddWin = new Shell();
		shl_shopAddWin.setSize(450, 300);
		shl_shopAddWin.setText("上架商品");

	}

}

