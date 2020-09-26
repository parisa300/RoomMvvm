package com.example.roommvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {
    public static final String EXTRA_TITLE="com.codinginflow.architectureeexample.EXTRA_TITLE";
    public static final String EXTRA_ID="com.codinginflow.architectureeexample.EXTRA_ID";
    public static final String EXTRA_DESCRIPTION="com.codinginflow.architectureeexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="com.codinginflow.architectureeexample.EXTRA_PRIORITY";
   private EditText edtTitle,EdtDec;
   private NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        edtTitle=findViewById(R.id.edttitle);
        EdtDec=findViewById(R.id.edtdesc);
        numberPicker=findViewById(R.id.numberPicker_priority);
       numberPicker.setMinValue(1);
       numberPicker.setMaxValue(10);
       getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

       Intent intent=getIntent();
       if(intent.hasExtra(EXTRA_ID)){
           setTitle("Edit_Note");
           edtTitle.setText(intent.getStringExtra(EXTRA_TITLE));
           EdtDec.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
           numberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
       }else {
           setTitle("Add_Note");
       }

    }
  private void SaveNote(){
        String title=edtTitle.getText().toString();
        String desc=EdtDec.getText().toString();
        int priority=numberPicker.getValue();

        if(title.trim().isEmpty()||desc.trim().isEmpty()){
            Toast.makeText(this, "please enter title and desc", Toast.LENGTH_SHORT).show();
            return;
        }
      Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,desc);
        data.putExtra(EXTRA_PRIORITY,priority);
        int id =getIntent().getIntExtra(EXTRA_ID,-1);
        if(id !=-1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();

  }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                SaveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}