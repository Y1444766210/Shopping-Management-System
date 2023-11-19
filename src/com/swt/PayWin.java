package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.smsystem.entity.*;
public class PayWin {

	protected Shell shl_payWin;
	private Text txt_name;
	private Label lbl_payPwd;
	private Text txt_payPwd;
	private Label lbl_tips;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PayWin window = new PayWin();
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
		shl_payWin.open();
		shl_payWin.layout();
		shl_payWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_payWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_payWin.getSize().y/2);
		
		Label lbl_name = new Label(shl_payWin, SWT.NONE);
		lbl_name.setBounds(56, 89, 168, 20);
		lbl_name.setText("请输入您想要的商品名：");
		
		txt_name = new Text(shl_payWin, SWT.BORDER);
		txt_name.setBounds(230, 86, 168, 26);
		
		lbl_tips = new Label(shl_payWin, SWT.NONE);
		lbl_tips.setBounds(230, 173, 168, 20);
		
		Button btn_pay = new Button(shl_payWin, SWT.NONE);
		btn_pay.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String goodName = txt_name.getText();
				SMS_USER user = new SMS_USER();
				String logName = user.getLogname();
				Connection ct = null;
				ResultSet rs = null;
				ResultSet rs1 = null;
				Statement st = null;
				Statement st1 = null;
				Statement st2 = null;
				Statement st3 = null;
				try {
					ct = DBM.getcon();
					st = ct.createStatement();
					st1 = ct.createStatement();
					st2 = ct.createStatement();
					st3 = ct.createStatement();
					rs = st.executeQuery("select * from SMS_SHCART where CAR_NAME = '"+goodName+"' and CAR_BELONG = '"+logName+"'");
					rs1 = st3.executeQuery("select * from SMS_USER where USER_NAME = '"+logName+"'");

					while(rs1.next()) {
						if(rs1.getString(5).equals(txt_payPwd.getText())) {
							MessageBox mb = new MessageBox(shl_payWin,SWT.NONE);
							mb.setText("支付提示");
							mb.setMessage("支付成功！");
							//打开提示框
							mb.open();
							shl_payWin.close();
						}
						else
						{
							lbl_tips.setText("支付密码错误");
						}
					}
					while(rs.next()) {
						String name = rs.getString(2);
						String belong = rs.getString(3);
						String condition = "未收货";
						String keeper = rs.getString(5);
						st1.executeUpdate("insert into SMS_LIST (LIST_NAME,LIST_CONDITION,LIST_BELONG,LIST_KEEPER) values('"+name+"','"+condition+"','"+belong+"','"+keeper+"')");
						st2.executeUpdate("delete from SMS_SHCART where CAR_NAME = '"+name+"' and CAR_BELONG = '"+belong+"'");
					}
					
					}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btn_pay.setBounds(149, 212, 133, 37);
		btn_pay.setText("确认购买");
		
		lbl_payPwd = new Label(shl_payWin, SWT.NONE);
		lbl_payPwd.setBounds(56, 144, 168, 20);
		lbl_payPwd.setText("请输入您的支付密码：");
		
		txt_payPwd = new Text(shl_payWin, SWT.BORDER|SWT.PASSWORD);
		txt_payPwd.setBounds(230, 141, 168, 26);
		

		while (!shl_payWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_payWin = new Shell();
		shl_payWin.setSize(485, 332);
		shl_payWin.setText("支付界面 ");

	}
}
