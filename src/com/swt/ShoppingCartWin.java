package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.smsystem.entity.*;

public class ShoppingCartWin {

	protected Shell shl_ShoppingCartWin;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ShoppingCartWin window = new ShoppingCartWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public List<ShoppingCart> getList() {
		Connection ct = null;
		Statement st = null;
		ResultSet rs = null;
		List<ShoppingCart> list = null;
		try {
			ct = DBM.getcon();
			st = ct.createStatement();
			SMS_USER user = new SMS_USER();
			String uname = user.getLogname();
			rs = st.executeQuery("select * from SMS_SHCART where CAR_BELONG = '"+uname+"'");
			list=new ArrayList<ShoppingCart>();
			while(rs.next()) {
				list.add(new ShoppingCart(rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5)));
			}
	}catch(Exception e){
		 e.printStackTrace();
	}finally {
		DBM.getclose(ct, null, rs);
	}
		return list;
	}

	public void refreshTable(Table table) {
		table.removeAll();  //首先清空之前table里的数据。
		try {
			List<ShoppingCart> list = getList();
			TableItem item1 = null;
			for(ShoppingCart s : list) {
				item1 = new TableItem(table,SWT.NONE);
				item1.setText(new String[] {
						s.getId(),
						s.getName(),
						s.getBelong(),
						String.valueOf(s.getValue()),
				});
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}	
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shl_ShoppingCartWin.open();
		shl_ShoppingCartWin.layout();
		shl_ShoppingCartWin.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_ShoppingCartWin.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_ShoppingCartWin.getSize().y/2);
		
		Menu menu = new Menu(shl_ShoppingCartWin, SWT.BAR);
		shl_ShoppingCartWin.setMenuBar(menu);
		
		MenuItem casMeu_edit = new MenuItem(menu, SWT.CASCADE);
		casMeu_edit.setText("编辑购物车");
		
		Menu meu_All = new Menu(casMeu_edit);
		casMeu_edit.setMenu(meu_All);
		
		MenuItem meuItem_delete = new MenuItem(meu_All, SWT.NONE);
		meuItem_delete.setText("删除商品");
		meuItem_delete.addSelectionListener((SelectionListener) new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DeleteWin dlw = new DeleteWin();
				dlw.open();
			}
		});
		
		MenuItem meuItem_pay = new MenuItem(meu_All, SWT.NONE);
		meuItem_pay.setText("购买商品");
		meuItem_pay.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				PayWin pw = new PayWin();
				pw.open();
			}
		});
		
		MenuItem meu_refresh = new MenuItem(menu, SWT.NONE);
		meu_refresh.setText("刷新购物车");
		
		MenuItem meuItem_back = new MenuItem(menu, SWT.NONE);
		meuItem_back.setText("返回首页");
		meuItem_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Main scw = new Main();
				shl_ShoppingCartWin.dispose();
				scw.open();
			}
		});
			
		table = new Table(shl_ShoppingCartWin, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 0, 637, 395);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tbCol_id = new TableColumn(table, SWT.NONE);
		tbCol_id.setWidth(152);
		tbCol_id.setText("商品编号");
		
		TableColumn tbCol_name = new TableColumn(table, SWT.NONE);
		tbCol_name.setWidth(163);
		tbCol_name.setText("商品名");
		
		TableColumn tbCol_value = new TableColumn(table, SWT.NONE);
		tbCol_value.setWidth(155);
		tbCol_value.setText("价格");
		
		TableColumn tbCol_belong = new TableColumn(table, SWT.NONE);
		tbCol_belong.setWidth(172);
		tbCol_belong.setText("商家");
		
		List<ShoppingCart> list = getList();
		TableItem item = null;
		for(ShoppingCart s : list) {
			item = new TableItem(table,SWT.NONE);
			item.setText(new String[] {
					s.getId(),
					s.getName(),
					s.getBelong(),
					String.valueOf(s.getValue())
			});
		}
		meu_refresh.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
					refreshTable(table);
				}
		});
		while (!shl_ShoppingCartWin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_ShoppingCartWin = new Shell();
		shl_ShoppingCartWin.setSize(654, 463);
		shl_ShoppingCartWin.setText("购物车");
		shl_ShoppingCartWin.setLayout(null);
		
	}
}