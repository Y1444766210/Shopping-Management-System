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
public class KeeperListWin {

	protected Shell shl_keeperListWin;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			KeeperListWin window = new KeeperListWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void refreshTable(Table table) {
		table.removeAll();  //首先清空之前table里的数据。
		try {
			List<KeeperList> list = getList();
			TableItem item1 = null;
			for(KeeperList s : list) {
				item1 = new TableItem(table,SWT.NONE);
				item1.setText(new String[] {
						s.getName(),
						s.getCondition(),
						s.getBelong()
				});
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
	}
	public List<KeeperList> getList() {
		Connection ct = null;
		Statement st = null;
		ResultSet rs = null;
		List<KeeperList> list = null;
		try {
			ct = DBM.getcon();
			st = ct.createStatement();
			Keeper keeper = new Keeper();
			String kname = keeper.getLogKeeper();
			rs = st.executeQuery("select * from SMS_LIST where LIST_KEEPER = '"+kname+"'");
			list=new ArrayList<KeeperList>();
			while(rs.next()) {
				list.add(new KeeperList(rs.getString(1),rs.getString(2),rs.getString(3)));
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
		shl_keeperListWin.open();
		shl_keeperListWin.layout();
		shl_keeperListWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_keeperListWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_keeperListWin.getSize().y/2);
		
		Menu menu = new Menu(shl_keeperListWin, SWT.BAR);
		shl_keeperListWin.setMenuBar(menu);
		
		MenuItem meuItem_listManagement = new MenuItem(menu, SWT.NONE);
		meuItem_listManagement.setText("管理订单");
		meuItem_listManagement.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ListManagementWin lmw = new ListManagementWin();
				lmw.open();
			}
			
		});
		
		MenuItem meuItem_refresh = new MenuItem(menu, SWT.NONE);
		meuItem_refresh.setText("刷新页面");
		meuItem_refresh.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refreshTable(table);
			}
		});
		
		MenuItem meuItem_back = new MenuItem(menu, SWT.NONE);
		meuItem_back.setText("返回店铺首页");
		meuItem_back.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shl_keeperListWin.close();
				ShopHomepageWin shw = new ShopHomepageWin();
				shw.open();
			}
		});
		
		table = new Table(shl_keeperListWin, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 553, 314);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblCol_name = new TableColumn(table, SWT.NONE);
		tblCol_name.setWidth(188);
		tblCol_name.setText("商品名称");
		
		TableColumn tblCol_condition = new TableColumn(table, SWT.NONE);
		tblCol_condition.setWidth(188);
		tblCol_condition.setText("商品状态");
		
		TableColumn tblCol_belong = new TableColumn(table, SWT.NONE);
		tblCol_belong.setWidth(190);
		tblCol_belong.setText("商品所属用户");
		
		List<KeeperList> list = getList();
		TableItem item = null;
		for(KeeperList s : list) {
			item = new TableItem(table,SWT.NONE);
			item.setText(new String[] {
					s.getName(),
					s.getCondition(),
					s.getBelong()
			});
		}
		
		while (!shl_keeperListWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_keeperListWin = new Shell();
		shl_keeperListWin.setSize(570, 384);
		shl_keeperListWin.setText("商家管理");

	}
}