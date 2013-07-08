package com.thebitisland.campamentosdiaper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText user_field;
	EditText password_field;
	Button login_button;
	Boolean loginOK, camp;
	ImageView logo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final Context context = getApplicationContext();

		user_field = (EditText) findViewById(R.id.user_field);
		password_field = (EditText) findViewById(R.id.password_field);
		login_button = (Button) findViewById(R.id.login_button);
		logo = (ImageView) findViewById(R.id.logo);
		
		/* El logo desaparece cuando introducimos usuario/contrase�a */
		/*user_field.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				if(arg1){
					logo.setVisibility(View.GONE);
				}else{
					logo.setVisibility(View.VISIBLE);
				}
			}
		});
		password_field.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				if(arg1){
					logo.setVisibility(View.GONE);
				}else{
					logo.setVisibility(View.VISIBLE);
				}
			}
		});/*
		/* Problemas: El logo no reaparece */
		
		login_button.setOnClickListener(new OnClickListener() {
			public void onClick(android.view.View arg0) {
				String user = user_field.getText().toString();
				String pass = password_field.getText().toString();
				Log.d("prueba", user);
				Log.d("prueba", pass);

				/* Falta el login en s� xD */
				loginOK = true;

				/* El usuario est� en un campamento? Se puede devolver en la misma query del login? */
				camp = true;
				
				/* fin de lo que falta */

				if (loginOK == true && camp == true) {
					Intent login = new Intent(context, HomeActivity.class);
					startActivity(login);
					finish();
				} else if (loginOK == true && camp == false) {
					/*Intent login = new Intent(context, CampsActivity.class);
					startActivity(login);
					finish();*/
				} else {
					CharSequence text = "Error en usuario/contrase�a!";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.show();
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
