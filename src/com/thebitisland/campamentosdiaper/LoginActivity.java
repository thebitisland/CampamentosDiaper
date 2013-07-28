package com.thebitisland.campamentosdiaper;

import com.thebitisland.campamentosdiaper.auxClasses.DBManager;
import com.thebitisland.campamentosdiaper.auxClasses.DownloadDatabase;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	EditText user_field;
	EditText password_field;
	Button login_button;
	Boolean loginOK;
	ImageView logo;
	Context context;
	DBManager database;
	SharedPreferences prefs;
	boolean isConnected;
	String user, pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		context = getApplicationContext();
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		isConnected = checkInternetConnection(context);

		user_field = (EditText) findViewById(R.id.user_field);
		password_field = (EditText) findViewById(R.id.password_field);
		login_button = (Button) findViewById(R.id.login_button);
		logo = (ImageView) findViewById(R.id.logo);
		database = new DBManager(this);
		database.open();
		database.close();

		setupUI(findViewById(R.id.parent));

		if (isConnected) {
			DownloadDatabase thread = new DownloadDatabase(prefs, this);
			thread.execute();
		}

		loginOK = prefs.getBoolean("loginOK", false);
		if(loginOK){
			/* I logged already */
			Intent login = new Intent(context, CampActivity.class);
			startActivity(login);
			finish();
		}
		
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
				user = user_field.getText().toString();
				pass = password_field.getText().toString();

				if (checkUsername(user, pass)) {
					/* Remember me */
					SharedPreferences.Editor editor = prefs.edit();
					editor.putBoolean("loginOK", true);
					editor.commit();

					Intent login = new Intent(context, CampActivity.class);
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

	public boolean checkUsername(String username, String password) {

		if (username.equals("") || password.equals("") || username == null
				|| password == null) {
			Toast.makeText(context, "The user/password field cannot be empty",
					Toast.LENGTH_SHORT).show();
			return false;
		}
		// Open database
		DBManager db = new DBManager(context);
		db.open();

		// Search for username. If it does not exist return false.
		boolean userCheck = db.checkUsername(username);

		// Check password. If it does not match the default one, return false.
		boolean passCheck = password.equals("santonia2013");

		// Close the database
		db.close();

		return userCheck && passCheck;

	}

	public void setupUI(View view) {

		// Set up touch listener for non-text box views to hide keyboard.
		if (!(view instanceof EditText)) {

			view.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard();
					return false;
				}

			});
		}

		// If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}

	public void hideSoftKeyboard() {
		InputMethodManager inputMethodManager = (InputMethodManager) this
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus()
				.getWindowToken(), 0);
	}

	private boolean checkInternetConnection(Context ctx) {

		ConnectivityManager conn = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetworkInfo = conn.getActiveNetworkInfo();
		/* No Internet? Show dialog and Intent to system's settings */
		if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
			return true;
		}

		Toast.makeText(context, "No Internet connection. Enjoy a local use!",
				Toast.LENGTH_SHORT).show();
		return false;
	}

}
