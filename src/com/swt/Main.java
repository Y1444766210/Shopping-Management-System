package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import com.smsystem.entity.*;


public class Main {

	protected Shell shl_main;
	private Text text_search;
	private static Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
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
		shl_main.open();
		shl_main.layout();
		shl_main.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_main.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_main.getSize().y/2);
		while (!shl_main.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_main = new Shell();
		shl_main.setToolTipText("");
		shl_main.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_main.setSize(652, 468);
		shl_main.setText("Main");
		
		text_search = new Text(shl_main, SWT.BORDER);
		text_search.setBounds(53, 45, 355, 26);
		
		Label lbl_tips = new Label(shl_main, SWT.NONE);
		lbl_tips.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips.setBounds(53, 10, 263, 29);
		
		
		
		Button btn_search = new Button(shl_main, SWT.NONE);
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					lbl_tips.setText("");
					table.removeAll();
					String[][] x = DBM.getProduct(text_search.getText());
					int count = 0;
					if(x[count][1]==null)
					{
						lbl_tips.setText("未找到相关商品，请重新输入");
					}
					while(x[count][1]!= null)
					{
						TableItem tableItem = new TableItem(table, SWT.NONE);	//每循环一次，就新建一行
						tableItem.setText(new String[]{x[count][0],x[count][1],x[count][2],x[count][3]}); 
						count++;
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("2");
					e1.printStackTrace();
				}
				

			}
		});
		btn_search.setBounds(444, 39, 98, 30);
		btn_search.setText("搜索");
		
		Menu menu = new Menu(shl_main, SWT.BAR);
		shl_main.setMenuBar(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
		menuItem.setText("导航栏");
		
		Menu menu_1 = new Menu(menuItem);
		menuItem.setMenu(menu_1);
		
		MenuItem menuItem_1 = new MenuItem(menu_1, SWT.NONE);
		menuItem_1.setText("首页");
		
		MenuItem menuItem_2 = new MenuItem(menu_1, SWT.NONE);
		menuItem_2.setText("购物车");
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ShoppingCartWin window = new ShoppingCartWin();
				shl_main.dispose();
				window.open();
			}
		});
		
		MenuItem mum_list = new MenuItem(menu_1, SWT.NONE);
		mum_list.setText("订单");
		mum_list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserListWin window = new UserListWin();
				shl_main.dispose();
				window.open();
			}
		});
		
		MenuItem menuItem_4 = new MenuItem(menu_1, SWT.NONE);
		menuItem_4.setText("个人中心");
		menuItem_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_main.dispose();
				UserCentre window = new UserCentre();
				window.open();
			}
		});
		
		
		table = new Table(shl_main, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(53, 93, 489, 309);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addListener(SWT.MouseDoubleClick, new Listener(){
			 
			public void handleEvent(Event event) {
			TableItem itemList =table.getItem(table.getSelectionIndex());
			/*
			你可以通过这个下标来取得选中的行的数据了.例如:取得所选行的第一个列属性
			*/
			SMS_PRODUCTS pd = new SMS_PRODUCTS();
			pd.setLogid(itemList.getText());
			shl_main.dispose();
			Shopping window = new Shopping();
			try {
				window.open();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			                
			}});
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("商品编号");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("商品名称");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("商品价格");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("商家");
		
		Connection ct = DBM.getcon();
		Statement st = null;
		try {
			st = ct.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = st.executeQuery("SELECT * FROM SMS_PRODUCTS");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(rs.next())
			{
				TableItem tableItem = new TableItem(table, SWT.NONE);	//每循环一次，就新建一行
				tableItem.setText(new String[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)}); 
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
