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
public class ShopHomepageWin {

	protected Shell shl_shopHomepageWin;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ShopHomepageWin window = new ShopHomepageWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void refreshTable(Table table) {
		table.removeAll();  //首先清空之前table里的数据。
		try {
			List<SMS_PRODUCTS> list = getList();
			TableItem item1 = null;
			for(SMS_PRODUCTS s : list) {
				item1 = new TableItem(table,SWT.NONE);
				item1.setText(new String[] {
						s.getID(),
						s.getName(),
						s.getPrice()
				});
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
	}
	public List<SMS_PRODUCTS> getList() {
		Connection ct = null;
		Statement st = null;
		ResultSet rs = null;
		List<SMS_PRODUCTS> list = null;
		try {
			ct = DBM.getcon();
			st = ct.createStatement();
			Keeper keeper = new Keeper();
			String kname = keeper.getLogKeeper();
			rs = st.executeQuery("select * from SMS_PRODUCTS where PDT_KEEPER = '"+kname+"'");
			list=new ArrayList<SMS_PRODUCTS>();
			while(rs.next()) {
				list.add(new SMS_PRODUCTS(rs.getString(1),rs.getString(2),rs.getString(3)));
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
		shl_shopHomepageWin.open();
		shl_shopHomepageWin.layout();
		shl_shopHomepageWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_shopHomepageWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_shopHomepageWin.getSize().y/2);
		
		table = new Table(shl_shopHomepageWin, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 553, 314);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblCol_id = new TableColumn(table, SWT.NONE);
		tblCol_id.setWidth(186);
		tblCol_id.setText("商品编号");
		
		TableColumn tblCol_name = new TableColumn(table, SWT.NONE);
		tblCol_name.setWidth(189);
		tblCol_name.setText("商品名字");
		
		TableColumn tblCol_value = new TableColumn(table, SWT.NONE);
		tblCol_value.setWidth(174);
		tblCol_value.setText("商品价格");
		

		List<SMS_PRODUCTS> list = getList();
		TableItem item = null;
		for(SMS_PRODUCTS s : list) {
			item = new TableItem(table,SWT.NONE);
			item.setText(new String[] {
					s.getID(),
					s.getName(),
					s.getPrice()
			});
		}
		
		while (!shl_shopHomepageWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_shopHomepageWin = new Shell();
		shl_shopHomepageWin.setSize(570, 384);
		shl_shopHomepageWin.setText("商家首页");
		
		Menu menu = new Menu(shl_shopHomepageWin, SWT.BAR);
		shl_shopHomepageWin.setMenuBar(menu);
		
		MenuItem meuItem_listDetail = new MenuItem(menu, SWT.NONE);
		meuItem_listDetail.setText("订单详情");
		
		MenuItem meuItem_add = new MenuItem(menu, SWT.NONE);
		meuItem_add.setText("上架商品");
		meuItem_add.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ShopAddWin saw = new ShopAddWin();
				saw.open();
			}
		});
		
		MenuItem meuItem_delete = new MenuItem(menu, SWT.NONE);
		meuItem_delete.setText("下架商品");
		meuItem_delete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ShopDeleteWin sdw = new ShopDeleteWin();
				sdw.open();
			}
		});
		
		MenuItem meuItem_refresh = new MenuItem(menu, SWT.NONE);
		meuItem_refresh.setText("刷新页面");
		
		MenuItem mnm_back = new MenuItem(menu, SWT.NONE);
		mnm_back.setText("退出登录");
		mnm_back.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shl_shopHomepageWin.dispose();
				BLogin window = new BLogin();
				window.open();
			}
		});
		
		meuItem_refresh.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				refreshTable(table);
			}
		});
		
		meuItem_listDetail.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				KeeperListWin klw = new KeeperListWin();
				shl_shopHomepageWin.close();
				klw.open();
			}
		});
	}
}
