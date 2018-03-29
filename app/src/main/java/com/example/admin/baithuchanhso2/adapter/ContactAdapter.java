package com.example.admin.baithuchanhso2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.baithuchanhso2.R;
import com.example.admin.baithuchanhso2.model.Contact;

import java.util.List;

/**
 * Created by Admin on 20/3/2018.
 */

public class ContactAdapter extends ArrayAdapter<Contact>{
    private Context context;
    private int resource;
    private List<Contact> arayContact;

    public ContactAdapter(@NonNull Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arayContact=objects;

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_listview,parent,false);
            viewHolder.imgAvatar = (ImageView) convertView.findViewById(R.id.img_avatar);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();

        }
        Contact contact = arayContact.get(position);
        viewHolder.tvName.setText(contact.getmName());
        viewHolder.tvNumber.setText(contact.getmNumber());
        if (contact.isMale()){
            viewHolder.imgAvatar.setBackgroundResource(R.drawable.danong);
        } else{
            viewHolder.imgAvatar.setBackgroundResource(R.drawable.banba);
        }
        return convertView;
    }
    public class ViewHolder{
         ImageView imgAvatar;
         TextView tvName;
         TextView tvNumber;
    }
}
