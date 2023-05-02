package com.alexis.reto_meli.view.builder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.alexis.reto_meli.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ErrorAlertBuild {

    @Inject
    public ErrorAlertBuild() {
        //no implemented
    }

    public AlertDialog buildAlert(Context context , String message){
        View alertCustomView = LayoutInflater.from(context).inflate(R.layout.custom_alert_view,null);
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(context);
        TextView tv_error_message = alertCustomView.findViewById(R.id.tv_error_message);
        tv_error_message.setText(message);
        alertDialog.setView(alertCustomView);
        Button btn_close = (Button) alertCustomView.findViewById(R.id.btn_close);

        final androidx.appcompat.app.AlertDialog dialog = alertDialog.create();

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        return dialog;
    }
}
