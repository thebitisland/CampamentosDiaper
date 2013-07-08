package com.thebitisland.campamentosdiaper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText user_field;
	EditText password_field;
	Button login_button;
	Boolean loginOK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final Context context = getApplicationContext();

		user_field = (EditText) findViewById(R.id.user_field);
		password_field = (EditText) findViewById(R.id.password_field);
		login_button = (Button) findViewById(R.id.login_button);

		login_button.setOnClickListener(new OnClickListener() {
			public void onClick(android.view.View arg0) {
				String user = user_field.getText().toString();
				String pass = password_field.getText().toString();
				Log.d("prueba", user);
				Log.d("prueba", pass);

				/* Falta el login en sí xD */
				loginOK = true;
				/* fin de lo que falta */

				if (loginOK) {
					Intent login = new Intent(context, HomeActivity.class);
					startActivity(login);
					finish();
				} else {
					CharSequence text = "Error en usuario/contraseña!";
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
