package com.thebitisland.campamentosdiaper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
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

		/* ñapa v1 */
		/*
		 * user_field.setOnFocusChangeListener(new OnFocusChangeListener() {
		 * public void onFocusChange(View arg0, boolean arg1) { if(arg1){
		 * logo.setVisibility(View.GONE); }else{
		 * logo.setVisibility(View.VISIBLE); } } });
		 * password_field.setOnFocusChangeListener(new OnFocusChangeListener() {
		 * public void onFocusChange(View arg0, boolean arg1) { if(arg1){
		 * logo.setVisibility(View.GONE); }else{
		 * logo.setVisibility(View.VISIBLE); } } });
		 */

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
				Log.d("prueba", user);
				Log.d("prueba", pass);

				/* Falta el login en sï¿½ xD */
				loginOK = true;

				/*
				 * El usuario estï¿½ en un campamento? Se puede devolver en la
				 * misma query del login?
				 */
				camp = true;

				/* fin de lo que falta */

				if (loginOK == true && camp == true) {
					Intent login = new Intent(context, HomeActivity.class);
					startActivity(login);
					finish();
				} else if (loginOK == true && camp == false) {
					/*
					 * Intent login = new Intent(context, CampsActivity.class);
					 * startActivity(login); finish();
					 */
				} else {
					CharSequence text = "Error en usuario/contraseï¿½a!";
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
