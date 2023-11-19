package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import com.smsystem.entity.*;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
public class Shopping {

	protected Shell shl_shopping;
	private Label lbl_title;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Shopping window = new Shopping();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws ClassNotFoundException 
	 */
	public void open() throws ClassNotFoundException {
		Display display = Display.getDefault();
		createContents();
		shl_shopping.open();
		shl_shopping.layout();
		shl_shopping.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_shopping.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_shopping.getSize().y/2);
		while (!shl_shopping.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws ClassNotFoundException 
	 */
	protected void createContents() throws ClassNotFoundException {
		shl_shopping = new Shell();
		shl_shopping.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_shopping.setSize(655, 402);
		shl_shopping.setText("SWT Application");
		
		lbl_title = new Label(shl_shopping, SWT.NONE);
		lbl_title.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_title.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 20, SWT.BOLD | SWT.ITALIC));
		lbl_title.setBounds(67, 53, 292, 50);
		SMS_PRODUCTS pd = new SMS_PRODUCTS();
		String sql1 = "SELECT * FROM SMS_PRODUCTS WHERE PDT_ID = '"+pd.getLogid()+"';";
		lbl_title.setText(DBM.getName(sql1));
		
		Label lbl_ID = new Label(shl_shopping, SWT.NONE);
		lbl_ID.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_ID.setBounds(162, 23, 67, 24);
		String sql3 = "SELECT * FROM SMS_PRODUCTS WHERE PDT_ID = '"+pd.getLogid()+"';";
		lbl_ID.setText(DBM.getID(sql3));
		
		Label lbl_price = new Label(shl_shopping, SWT.NONE);
		lbl_price.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_price.setBounds(426, 218, 53, 31);
		String sql2 = "SELECT * FROM SMS_PRODUCTS WHERE PDT_ID = '"+pd.getLogid()+"';";
		lbl_price.setText(DBM.getPrice(sql2));
		
		Label lbl_business = new Label(shl_shopping, SWT.NONE);
		lbl_business.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_business.setBounds(261, 23, 171, 20);
		String sql4 = "SELECT * FROM SMS_PRODUCTS WHERE PDT_ID = '"+pd.getLogid()+"';";
		lbl_business.setText(DBM.getKeeper(sql4));
		
		Label lbl_tips = new Label(shl_shopping, SWT.NONE);
		lbl_tips.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips.setBounds(373, 306, 137, 20);
		
		Button button = new Button(shl_shopping, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SMS_USER user = new SMS_USER();
				String[] temp = {lbl_ID.getText(),lbl_title.getText(),user.getLogname(),lbl_price.getText(),lbl_business.getText()};
				try {
					lbl_tips.setText(DBM.addSHcart(temp));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(373, 265, 80, 32);
		button.setText("加入购物车");
		
		Label lbl_price_1 = new Label(shl_shopping, SWT.NONE);
		lbl_price_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_price_1.setBounds(373, 218, 47, 24);
		lbl_price_1.setText("价格：");
		
		
		Label lbl_ID_1 = new Label(shl_shopping, SWT.NONE);
		lbl_ID_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_ID_1.setBounds(67, 23, 74, 24);
		lbl_ID_1.setText("商品编号：");
		
		
		Composite composite = new Composite(shl_shopping, SWT.NONE);
		composite.setBounds(70, 109, 252, 140);
		
		Composite composite_1 = new Composite(shl_shopping, SWT.NONE);
		composite_1.setBounds(69, 255, 253, 32);
		
		Button btn_back = new Button(shl_shopping, SWT.NONE);
		btn_back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_shopping.dispose();
				Main window = new Main();
				window.open();
			}
		});
		btn_back.setBounds(500, 18, 98, 30);
		btn_back.setText("<返回首页");
		
		
	

	}
}
