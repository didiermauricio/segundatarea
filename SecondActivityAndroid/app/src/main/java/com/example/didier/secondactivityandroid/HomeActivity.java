package com.example.didier.secondactivityandroid;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.sqlitedemoo.R;
public class HomeActivity extends Activity {
    Button addmem_bt;
    ListView lv;
    SQLController dbcon;
    TextView memID_tv, memName_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbcon = new SQLController(this);
        dbcon.open();
        addmem_bt = (Button) findViewById(R.id.newTask);
        lv = (ListView) findViewById(R.id.task_id);
        addmem_bt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_mem = new Intent(HomeActivity.this, NewTaskActivity.class);
                startActivity(add_mem);
            }
        });
        Cursor cursor = dbcon.readData();
        String[] from = new String[] { DBhelper.MEMBER_ID, DBhelper.MEMBER_NAME };
        int[] to = new int[] { R.id.Task_id, R.id.Title_name };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
        HomeActivity.this, R.layout.view_task_sumary, cursor, from, to);
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                memID_tv = (TextView) view.findViewById(R.id.Task_id);
                memName_tv = (TextView) view.findViewById(R.id.Title_name);
                String memberID_val = memID_tv.getText().toString();
                String memberName_val = memName_tv.getText().toString();
                Intent modify_intent = new Intent(getApplicationContext(),
                        ModifyTask.class);
                modify_intent.putExtra("memberName", memberName_val);
                modify_intent.putExtra("memberID", memberID_val);
                startActivity(modify_intent);
            }
        });
    }
}
