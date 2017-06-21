package com.game.android.mahfuzcse11.contectprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckUserPermsions();
    }


    void CheckUserPermsions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                                Manifest.permission.READ_CONTACTS},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

        getReadContact();

    }


    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getReadContact();
                } else {

                    Toast.makeText(this, "Contact Denial", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }//End of onRequestPermissionsResult


    ArrayList<ContactItems> contactItemsArrayList = new ArrayList<>();


    public void getReadContact() {

        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {

            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contactItemsArrayList.add(new ContactItems(name, phone));

        }

        MyCustomAdapter myCustomAdapter = new MyCustomAdapter(contactItemsArrayList);
        ListView lsNews = (ListView) findViewById(R.id.listView);
        lsNews.setAdapter(myCustomAdapter);
        myCustomAdapter.notifyDataSetChanged();

    }//End Of getReadContact method


    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<ContactItems> listnewsDataAdpater;

        public MyCustomAdapter(ArrayList<ContactItems> listnewsDataAdpater) {
            this.listnewsDataAdpater = listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.list_item, null);

            final ContactItems s = listnewsDataAdpater.get(position);

            TextView tvName = (TextView) myView.findViewById(R.id.tvName);
            tvName.setText(s.name);

            TextView tVPhoneNumber = (TextView) myView.findViewById(R.id.tvPhoneNumber);
            tVPhoneNumber.setText(s.phoneNumber);

            return myView;
        }

    }


}
