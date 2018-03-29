package com.example.admin.baithuchanhso2;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.admin.baithuchanhso2.adapter.ContactAdapter;
import com.example.admin.baithuchanhso2.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private List<Contact> arrayContact;
   private ContactAdapter adapter;
   private EditText edtName;
   private EditText edtNumber;
   private RadioButton rbtnMale;
   private RadioButton rbtnFemale;
   private Button btnAddcontact;
   private ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidge();
        arrayContact = new ArrayList<>();
        adapter = new ContactAdapter(this,R.layout.item_contact_listview,arrayContact);
        lvContact.setAdapter(adapter);
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               showDialogConfim(position);
            }
        });


    }
    public void setWidge(){
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_number);
        rbtnMale = (RadioButton) findViewById(R.id.rbtn_male);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtn_female);
        btnAddcontact = (Button) findViewById(R.id.btn_add_contac);
        lvContact = (ListView) findViewById(R.id.lv_contact);
    }
    public void addContact(View view){
        if (view.getId()==R.id.btn_add_contac){
            String name = edtName.getText().toString().trim();
            String number = edtNumber.getText().toString().trim();
            boolean isMale = true;
            if (rbtnMale.isChecked()){
                isMale = true;
            }else {
                isMale = false;
            }
           if (TextUtils.isEmpty(name)|| TextUtils.isEmpty(number)){
               Toast.makeText(this,"Xin hay nhap chu hoac so",Toast.LENGTH_SHORT).show();
           }else {
               Contact contact = new Contact(isMale,name,number);
               arrayContact.add(contact);

           }
           adapter.notifyDataSetChanged();
        }
    }
    public void showDialogConfim(final int position){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnSendMessage = (Button) dialog.findViewById(R.id.btn_send_message);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               intenCall(position);
            }
        });
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Send Mesage", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
    private void intenCall(int position){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+arrayContact.get(position).getmNumber()));
        startActivity(intent);
    }
}

