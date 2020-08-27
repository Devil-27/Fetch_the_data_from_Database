package com.examples.datafetch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1 , et2 ;
    Button b1,btnview;
    DB_Helper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DB_Helper(this);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        b1 = (Button) findViewById(R.id.button);
        btnview = (Button) findViewById(R.id.button2);
        AddData();
        viewData();
    }
    public void AddData()
    {
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean isinserted = mydb.insertData(et1.getText().toString() , et2.getText().toString());
                if(isinserted = true)
                    Toast.makeText(MainActivity.this,"DATA HAS BEEN SUCCESSFULLY INSERTED !!!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"DATA HAS NOT BEEN INSERTED !!!",Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void viewData()
    {
        btnview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Cursor cu = mydb.getData();
                if(cu.getCount() == 0)
                {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(cu.moveToNext())
                {
                    buffer.append("ID : " + cu.getString(0) + "\n");
                    buffer.append("NAME : " + cu.getString(1) + "\n");
                    buffer.append("PHONE : " + cu.getString(2) + "\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }
    public void showMessage(String title , String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

