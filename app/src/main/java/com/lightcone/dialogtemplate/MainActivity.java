package com.lightcone.dialogtemplate;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private static final int launcherIcon = R.drawable.dialog_icon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Identify buttons in XML layout and attach click  listeners to each

		View button01 = findViewById(R.id.button01);
		button01.setOnClickListener(this);

		View button02 = findViewById(R.id.button02);
		button02.setOnClickListener(this);

		View button03 = findViewById(R.id.button03);
		button03.setOnClickListener(this);

		View button04 = findViewById(R.id.button04);
		button04.setOnClickListener(this);

		View button05 = findViewById(R.id.button05);
		button05.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	// General-purpose method to create dialogs; first form (method is overloaded). To
	// omit iconID, showCancel, showOK, or okClass options, set the following: 
	//
	//     iconID = -1
	//     showCancel = false
	//     showOK = false
	//     okClass = null

	public void makeDialog(Context context, String title, String message, boolean showCancel,
			boolean showOK, int theme, int iconID, Class<?> okClass){

		AlertFragment.context = context;
		AlertFragment.iconID = iconID;
		AlertFragment.title = title;
		AlertFragment.message = message;
		AlertFragment.theme = theme;
		AlertFragment.showOK = showOK;
		AlertFragment.showCancel = showCancel;
		AlertFragment.okClass = okClass;

		DialogFragment fragment = new AlertFragment();
		fragment.show(getSupportFragmentManager(), "Dialog");
	}

	// General-purpose method to create dialogs; second form (method is overloaded).
	// This form omits the iconID, and okClass arguments. To also omit showCancel or
	// showOK, set them to false.

	public void makeDialog(Context context, String title, String message, boolean showCancel,
			boolean showOK, int theme){

		AlertFragment.context = context;
		AlertFragment.title = title;
		AlertFragment.message = message;
		AlertFragment.showOK = showOK;
		AlertFragment.showCancel = showCancel;
		AlertFragment.theme = theme;

		DialogFragment fragment = new AlertFragment();
		fragment.show(getSupportFragmentManager(), "Dialog");
	}


	/**
	 * Example of a class to create alert dialog fragments. To call from another
	 * class, set the values of the static variables and then instantiate and show.  
	 * Example:
	 * 
	 *      AlertFragment.context = context;
			AlertFragment.iconID = iconID;
			AlertFragment.title = title;
			AlertFragment.message = message;
			AlertFragment.theme = theme;
			AlertFragment.showOK = showOK;
			AlertFragment.showCancel = showCancel;
			AlertFragment.okClass = okClass;

			DialogFragment fragment = new AlertFragment();
			fragment.show(getSupportFragmentManager(), "Dialog");
	 */

	public static class AlertFragment extends DialogFragment {

		public static Context context;
		public static String message;
		public static String title = null;
		public static int iconID = -1;
		public static int theme = -1;
		public static boolean showOK;
		public static boolean showCancel;
		public static Class<?> okClass = null;
		public static int buttonPressed;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {

			// Use the Builder class to construct the dialog.  Use the
			// form of the builder constructor that allows a theme to be set.

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), theme);

			if(title != null) builder.setTitle(title);
			if(iconID != -1) builder.setIcon(iconID);
			builder.setMessage(message);
			if(showOK && okClass != null){
				builder.setPositiveButton("Select this task", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent i = new Intent(context, okClass);
						startActivity(i);
					}
				});
			}
			if(showCancel){
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// Default is to cancel the dialog window.
					}
				});
			}
			// Create the AlertDialog object and return it
			return builder.create();
		}

	}

	// Process the button clicks 
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){	

		case R.id.button01:

			// Most common themes are AlertDialog.THEME_HOLO_DARK and AlertDialog.THEME_HOLO_LIGHT 
			
			makeDialog(this, "Title", "My message", true, true, AlertDialog.THEME_HOLO_LIGHT,
					launcherIcon, TaskActivity1.class);
			break;

		case R.id.button02:

			makeDialog(this, "Title2", "My message2", true, false, AlertDialog.THEME_HOLO_LIGHT);
			break;

		case R.id.button03:

			makeDialog(this, "Title3", "My message3", false, false, AlertDialog.THEME_TRADITIONAL);
			break;

		case R.id.button04:

			makeDialog(this, null, "My message4", false, false, AlertDialog.THEME_HOLO_DARK);
			break;

		case R.id.button05:
			// Example of using custom dialog theme defined in res/values/styles.xml
			makeDialog(this, "Title5", "My message5", false, false, R.style.DialogNoTitle);
			break;

		}

	}

}
