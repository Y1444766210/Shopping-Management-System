package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import com.smsystem.entity.*;
import org.eclipse.swt.widgets.Combo;
public class UserListWin {

	protected Shell shl_userListWin;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UserListWin window = new UserListWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<UserList> getList() {
		Connection ct = null;
		Statement st = null;
		ResultSet rs = null;
		List<UserList> list = null;
		try {
			ct = DBM.getcon();
			st = ct.createStatement();
			SMS_USER user = new SMS_USER();
			String uname = user.getLogname();
			rs = st.executeQuery("select * from SMS_LIST where LIST_BELONG = '"+uname+"'");
			list=new ArrayList<UserList>();
			while(rs.next()) {
				list.add(new UserList(rs.getString(1),rs.getString(2),rs.getString(4)));
			}
	}catch(Exception e){
		 e.printStackTrace();
	}finally {
		DBM.getclose(ct, null, rs);
	}
		return list;
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shl_userListWin.open();
		shl_userListWin.layout();
		shl_userListWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_userListWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_userListWin.getSize().y/2);
		
		Menu menu = new Menu(shl_userListWin, SWT.BAR);
		shl_userListWin.setMenuBar(menu);
		
		MenuItem meu_back = new MenuItem(menu, SWT.NONE);
		meu_back.setText("返回首页");
		meu_back.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
					Main sct = new Main();
					shl_userListWin.close();
					sct.open();
				}
		});
		while (!shl_userListWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_userListWin = new Shell();
		shl_userListWin.setSize(583, 398);
		shl_userListWin.setText("订单");
		
		table = new Table(shl_userListWin, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 566, 327);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblCol_name = new TableColumn(table, SWT.NONE);
		tblCol_name.setWidth(145);
		tblCol_name.setText("快递名称");
		
		TableColumn tblCol_conditon = new TableColumn(table, SWT.NONE);
		tblCol_conditon.setWidth(147);
		tblCol_conditon.setText("快递状态");
		
		TableColumn tblCol_keeper = new TableColumn(table, SWT.NONE);
		tblCol_keeper.setWidth(145);
		tblCol_keeper.setText("快递商家");
		
		List<UserList> list = getList();
		TableItem item = null;
		for(UserList ul : list) {
			item = new TableItem(table,SWT.NONE);
			item.setText(new String[] {
					ul.getName(),
					ul.getCondition(),
					ul.getKeeper(),
			});
		}
	}
}
