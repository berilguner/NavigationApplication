package com.example.navigationson;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;


public class DialogFragment {


    public Context context;
    static Event e;



    public static Dialog createDialog(final Context context, boolean isNew) {
        // dialog nesnesi oluştur ve layout dosyasına bağlan
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        final Button btnKaydet = (Button) dialog.findViewById(R.id.button_kaydet);
        Button btnIptal = (Button) dialog.findViewById(R.id.button_iptal);
        final EditText eventEdit = (EditText) dialog.findViewById(R.id.eventEdit);
        final EditText detailEdit = (EditText) dialog.findViewById(R.id.detailEdit);
        final Spinner spinner = dialog.findViewById(R.id.spn_type);


        if (isNew) {
            btnKaydet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (spinner.getSelectedItemPosition() != 0 && !eventEdit.getText().toString().isEmpty() &&
                            !detailEdit.getText().toString().isEmpty()) {


                        LatLng latLng = MapTwo.getInstance().getMarker().getPosition();
                        Location location = new Location((long) latLng.longitude, (long) latLng.latitude);


                        // LoginForm loginForm = new LoginForm();


                        SharedPreferences  sharedPreferences = context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE);
                        Event event = new Event(eventEdit.getText().toString(),
                                detailEdit.getText().toString(),
                                spinner.getSelectedItemPosition(),
                                location.getLat(), location.getLng(),
                                sharedPreferences.getString("username",""));


                        EventRoomDatabase.getInstance(context).eventDao().insert(event);


                        dialog.dismiss();
                        MapTwo.getInstance().clearMap();
                        MapFragment.changeFabIconAdd();



                        // MapFragment.callFab();


                        } else {
                        Toast.makeText(context, "Gerekli alanları doldurunuz", Toast.LENGTH_LONG).show();
                    }




                }




            });





        } else {
            btnKaydet.setText("update");
            btnKaydet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (spinner.getSelectedItemPosition() != 0 && !eventEdit.getText().toString().isEmpty() &&
                            !detailEdit.getText().toString().isEmpty()) {

                        e.setDetail(detailEdit.getText().toString());
                        e.setEvent(eventEdit.getText().toString());
                        e.setType(spinner.getSelectedItemPosition());
                        EventRepository.getInstance(context).update(e);


                        dialog.dismiss();
                        MapTwo.getInstance().clearMap();
                        MapFragment.changeFabIconAdd();


                    } else {
                        Toast.makeText(context, "Gerekli alanları doldurunuz", Toast.LENGTH_LONG).show();
                    }



                }
            });

        }


        // iptal butonunun tıklanma olayları
        btnIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                MapTwo.getInstance().clearMap();
                MapFragment.changeFabIconAdd();


            }
        });



        //dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


    public static void fillfields(Dialog dialog, Event event) {

        final EditText t = dialog.findViewById(R.id.eventEdit);
        t.setText(event.getEvent());

        final EditText m = dialog.findViewById(R.id.detailEdit);
        m.setText(event.getDetail());

        final Spinner s = dialog.findViewById(R.id.spn_type);
        s.setSelection(event.getType());

        e = event;


    }


}

