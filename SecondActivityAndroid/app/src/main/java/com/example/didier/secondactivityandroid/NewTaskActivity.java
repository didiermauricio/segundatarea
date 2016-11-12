package com.example.didier.secondactivityandroid;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.sqlitedemoo.R;

public class NewTaskActivity extends Activity implements OnClickListener {
	EditText et;
	Button add_bt, read_bt;
	SQLController dbcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtask);
		et = (EditText) findViewById(R.id.taskTitle);
		add_bt = (Button) findViewById(R.id.saveTask);

		dbcon = new SQLController(this);
		dbcon.open();
		add_bt.setOnClickListener(this);
	}
	public void onButtonClicked(View v){
		DatePickerFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(),"Date Picker");
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.saveTask:
			String name = et.getText().toString();
			dbcon.insertData(name);
			Intent main = new Intent(NewTaskActivity.this, HomeActivity.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(main);
			break;

		default:
			break;
		}
	}

}
