package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.smsystem.entity.SMS_USER;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UserCentre {

	protected Shell shl_centre;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UserCentre window = new UserCentre();
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
		shl_centre.open();
		shl_centre.layout();
		shl_centre.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_centre.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_centre.getSize().y/2);
		
		Button button = new Button(shl_centre, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_centre.dispose();
				ULogin window = new ULogin();
				window.open();
			}
		});
		button.setBounds(298, 265, 98, 30);
		button.setText("退出登录");
		while (!shl_centre.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_centre = new Shell();
		shl_centre.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_centre.setSize(860, 505);
		shl_centre.setText("UserCentre");
		
		Composite composite = new Composite(shl_centre, SWT.NONE);
		composite.setBounds(158, 59, 64, 64);
		
		Label lblNewLabel = new Label(shl_centre, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblNewLabel.setBounds(298, 59, 76, 20);
		lblNewLabel.setText("用户名：");
		
		Label lbl_name = new Label(shl_centre, SWT.NONE);
		lbl_name.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_name.setBounds(407, 59, 198, 20);
		SMS_USER user = new SMS_USER();
		lbl_name.setText(user.getLogname());
		
		Button button = new Button(shl_centre, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_centre.dispose();
				ShoppingCartWin window = new ShoppingCartWin();
				window.open();
			}
		});
		button.setBounds(298, 154, 98, 30);
		button.setText("我的书库");
		
		Button button_1 = new Button(shl_centre, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_centre.dispose();
				UserListWin window = new UserListWin();
				window.open();
			}
		});
		button_1.setBounds(423, 154, 98, 30);
		button_1.setText("我的订单");
		
		Button btnNewButton = new Button(shl_centre, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_centre.dispose();
				Main window = new Main();
				window.open();
			}
		});
		btnNewButton.setBounds(298, 207, 98, 30);
		btnNewButton.setText("返回首页");
		
		Button button_2 = new Button(shl_centre, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_centre.dispose();
				ChangeInfo window = new ChangeInfo();
				window.open();
			}
		});
		button_2.setBounds(423, 207, 98, 30);
		button_2.setText("修改信息");

	}
}
