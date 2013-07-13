package com.thebitisland.campamentosdiaper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
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
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		context = getApplicationContext();

		user_field = (EditText) findViewById(R.id.user_field);
		password_field = (EditText) findViewById(R.id.password_field);
		login_button = (Button) findViewById(R.id.login_button);
		logo = (ImageView) findViewById(R.id.logo);

		/* Ñapa vFinal (Ojo con ActionBars, puede dar problemas) */
		final View activityRootView = (View) findViewById(android.R.id.content);
		activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					public void onGlobalLayout() {
						int heightDiff = activityRootView.getRootView()
								.getHeight() - activityRootView.getHeight();
						if (heightDiff > 100) { // if more than 100 pixels, its
												// probably a keyboard...
							logo.setVisibility(View.GONE);
						} else {
							logo.setVisibility(View.VISIBLE);
						}
					}
				});

		login_button.setOnClickListener(new OnClickListener() {
			public void onClick(android.view.View arg0) {
				String user = user_field.getText().toString();
				String pass = password_field.getText().toString();

				/*
				 * El usuario esta en un campamento? Se puede devolver en la
				 * misma query del login? Esto era para opci�n Alvaro,
				 * implementando Nico de momento
				 */
				camp = true;
				loginOK = true;

				if (loginOK == true && camp == true) {
					Intent login = new Intent(context, CampActivity.class);
					startActivity(login);
					finish();
				} else if (loginOK == true && camp == false) {
					Intent login = new Intent(context, HomeActivity.class);
					startActivity(login);
					finish();
				} else {
					CharSequence text = "Error en usuario/password!";
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
