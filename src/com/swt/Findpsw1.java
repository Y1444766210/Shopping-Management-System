package com.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.smsystem.entity.*;
public class Findpsw1 {

	protected Shell shl_fdpsw1;
	private Text txt_phone;
	private Text txt_verify;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Findpsw1 window = new Findpsw1();
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
		shl_fdpsw1.open();
		shl_fdpsw1.layout();
		shl_fdpsw1.setLocation(Display.getCurrent().getClientArea().width / 2 - shl_fdpsw1.getShell().getSize().x/2, Display.getCurrent()
				.getClientArea().height / 2 - shl_fdpsw1.getSize().y/2);
		while (!shl_fdpsw1.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shl_fdpsw1 = new Shell();
		shl_fdpsw1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shl_fdpsw1.setSize(855, 488);
		shl_fdpsw1.setText("Findpsw");
		
		Label label = new Label(shl_fdpsw1, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label.setBounds(178, 81, 120, 20);
		label.setText("请输入手机号码：");
		
		txt_phone = new Text(shl_fdpsw1, SWT.BORDER);
		txt_phone.setBounds(304, 81, 232, 26);
		
		Label label_1 = new Label(shl_fdpsw1, SWT.NONE);
		label_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_1.setBounds(178, 119, 120, 20);
		label_1.setText("请输入验证码：");
		
		txt_verify = new Text(shl_fdpsw1, SWT.BORDER);
		txt_verify.setBounds(304, 119, 232, 26);
		
		Label lbl_verify = new Label(shl_fdpsw1, SWT.NONE);
		lbl_verify.setFont(SWTResourceManager.getFont("楷体", 9, SWT.NORMAL));
		lbl_verify.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_verify.setBounds(571, 119, 126, 20);
		lbl_verify.setText("默认验证码：1111");
		
		Label lbl_tips = new Label(shl_fdpsw1, SWT.NONE);
		lbl_tips.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lbl_tips.setBounds(571, 81, 138, 20);
		
		Button button = new Button(shl_fdpsw1, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(txt_verify.getText().equals("1111"))
				{
					SMS_USER user = new SMS_USER();
					user.setphone(txt_phone.getText());
					shl_fdpsw1.dispose();
					Findpsw2 window = new Findpsw2();
					window.open();
				}
				else
				{
					lbl_tips.setText("验证码错误");
				}
			}
		});
		button.setBounds(194, 186, 98, 30);
		button.setText("找回密码");
		
		Button button_1 = new Button(shl_fdpsw1, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_phone.setText("");
				txt_verify.setText("");
			}
		});
		button_1.setBounds(346, 186, 98, 30);
		button_1.setText("重新输入");
		
		Button button_2 = new Button(shl_fdpsw1, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shl_fdpsw1.dispose();
				ULogin window = new ULogin();
				window.open();
			}
		});
		button_2.setBounds(492, 186, 98, 30);
		button_2.setText("返回登陆");
		
	

	}
}
