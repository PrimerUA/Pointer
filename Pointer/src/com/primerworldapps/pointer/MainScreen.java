package com.primerworldapps.pointer;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.primerworldapps.pointer.auth.AuthorizationScreen;

public class MainScreen extends SherlockActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);

		initScreen();
		
		//���� �� �������������, ��
		startActivity(new Intent(MainScreen.this, AuthorizationScreen.class));
		
		//TODO: ����� ��������� ���� �����
	}

	private void initScreen() {
		
	}
}
