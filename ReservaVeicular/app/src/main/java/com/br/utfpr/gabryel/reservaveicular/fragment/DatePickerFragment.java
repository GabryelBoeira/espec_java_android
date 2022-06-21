package com.br.utfpr.gabryel.reservaveicular.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    //codigo de referencia: https://developer.android.com/guide/topics/ui/controls/pickers
    private EditText dataText;

    public DatePickerFragment(final EditText editText) {
        this.dataText = editText;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        if (dataText.getText() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            try {
                date = sdf.parse(dataText.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.setTime(date);
        }

        return new DatePickerDialog(getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String mes = month < 9 ? "0" + (month + 1) : (month + 1) + "";
        String dia = day < 9 ? "0" + (day) : (day) + "";
        dataText.setText(dia + "/" + mes + "/" + year);
    }
}
