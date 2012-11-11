package com.chenjun.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chenjun.R;
import com.chenjun.fund.model.LoginStatus;
import com.chenjun.fund.model.ModelFactory;
import com.chenjun.network.HttpDownloader;
import com.chenjun.network.InitFundReport;
import com.chenjun.network.NetWorkConfig;
import com.chenjun.utils.ThreadPoolUtils;

public class LoginActivity extends Activity{
	private static final int START_DOWNLOAD_MESSAGE = 1;
	private static final int FINISH_DOWNLOAD_MESSAGE = 2;
	private static final int NETWORK_EORROR_MESSAGE =3;
	
	private static final String USERNAME_AND_PASSWORD_SHARED_PREFERENCES = "Account";
	private static final String USERNAME_SHARED_PREFERENCES_KEY = "username";
	private static final String PASSWORD_SHARED_PREFERENCES_KEY = "password";
	
	private SharedPreferences sharedPreference;
	
	private ProgressDialog waitDialog;
	
	private Button loginButton;
	private Button resetButton;
	private Button registerButton;
	private EditText accountEditText;
	private EditText passwordEditText;
	private Handler refleshHandler;
	
	private String username;
	private String password;
	
	private LoginStatus loginStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		loginButton = (Button) findViewById(R.id.login_activity_loginBtn);
		resetButton = (Button) findViewById(R.id.login_activity_resetBtn);
		accountEditText = (EditText) findViewById(R.id.login_activity_Account_EditText);
		passwordEditText = (EditText) findViewById(R.id.login_activity_Password_EditText);
		
		loginButton.setOnClickListener(new LoginBtnLister());
		resetButton.setOnClickListener(new ResetBtnLister());
		
		refleshHandler = new RefleshHandler();
		
		getUsernameAndPassword();
	}
	
	private class LoginBtnLister implements OnClickListener{
		@Override
		public void onClick(View v) {
			v.setClickable(false);
			username = accountEditText.getEditableText().toString();
			password = passwordEditText.getEditableText().toString();
			username = username.trim();
			password = password.trim();
			
			ThreadPoolUtils.execute(new DownloadThread());
		}
	}
	
	private class ResetBtnLister implements OnClickListener{
		@Override
		public void onClick(View v) {
			accountEditText.getText().clear();
			passwordEditText.getText().clear();
			accountEditText.setFocusable(true);
			accountEditText.setFocusableInTouchMode(true);
			accountEditText.requestFocus();
		}
	}
	
	private class DownloadThread implements Runnable{
		@Override
		public void run() {
			
			String url = NetWorkConfig.getLoginUrl(username, password);
			
			refleshHandler.sendEmptyMessage(START_DOWNLOAD_MESSAGE);
			
			try{
				String jsonStr = HttpDownloader.downloadCompressedByte(url);
				loginStatus = ModelFactory.getLoginStatus(jsonStr);
				
				refleshHandler.sendEmptyMessage(FINISH_DOWNLOAD_MESSAGE);
			}
			catch(Exception ex){
				refleshHandler.sendEmptyMessage(NETWORK_EORROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
	
	private class RefleshHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case START_DOWNLOAD_MESSAGE:
				showWaitDialog();
				break;
				
			case FINISH_DOWNLOAD_MESSAGE:
				downloadFinish();
				break;
				
			case NETWORK_EORROR_MESSAGE:
				downloadError();
				break;
			
			default:
				break;
			}
		}
	}
	
	
	//显示等待对话框
	private void showWaitDialog(){
		if(waitDialog == null){
			waitDialog = new ProgressDialog(this);
			waitDialog.setMessage(getString(R.string.Globe_Login_ProgressDialog));
		}
		
		waitDialog.show();
	}
	
	//隐藏等待对话框
	private void hideWaitDialog(){
		if(waitDialog != null){
			waitDialog.hide();
		}
	}
	
	//数据请求成功
	private void downloadFinish(){
		hideWaitDialog();
		Toast.makeText(this, loginStatus.getExtraInfo(), Toast.LENGTH_SHORT).show();
		
		//登陆成功
		if(loginStatus.isSussess() == true){
			saveUsernameAndPassword();
			Intent intent = new Intent(this, MainWindowActivityGroup.class);
			InitFundReport.loginStatus = loginStatus;
//			System.out.println(loginStatus.getAccountId());
			this.startActivity(intent);
			this.finish();
		}
		
	
		loginButton.setClickable(true);
		
	}
	
	//保存账户密码
	private void saveUsernameAndPassword(){
		
		if(sharedPreference == null){
			sharedPreference = getSharedPreferences(USERNAME_AND_PASSWORD_SHARED_PREFERENCES, MODE_PRIVATE);
		}
		Editor editor = sharedPreference.edit();
		editor.putString(USERNAME_SHARED_PREFERENCES_KEY, username);
		editor.putString(PASSWORD_SHARED_PREFERENCES_KEY, password);
		editor.commit();
	}
	
	private void getUsernameAndPassword(){
		if(sharedPreference == null){
			sharedPreference = getSharedPreferences(USERNAME_AND_PASSWORD_SHARED_PREFERENCES, MODE_PRIVATE);
		}
		
		//缓存中没有账号和密码
		if(!sharedPreference.contains(USERNAME_SHARED_PREFERENCES_KEY) || !sharedPreference.contains(PASSWORD_SHARED_PREFERENCES_KEY)){
			return;
		}
		
		else{
			String username = sharedPreference.getString(USERNAME_SHARED_PREFERENCES_KEY, null);
			String password = sharedPreference.getString(PASSWORD_SHARED_PREFERENCES_KEY, null);
			accountEditText.setText(username);
			passwordEditText.setText(password);
		}
	}
	
	private void downloadError(){
		hideWaitDialog();
		Toast.makeText(this, this.getString(R.string.Globe_Network_Error), Toast.LENGTH_SHORT).show();
		
		loginButton.setClickable(true);
	}
}
