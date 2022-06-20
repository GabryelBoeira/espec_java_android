package com.br.utfpr.gabryel.reservaveicular.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    //codigo de referencia: https://developer.android.com/guide/topics/ui/controls/pickers
    private EditText dataText;

    public DatePickerFragment(final EditText editText) {
        this.dataText = editText;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String mes = month < 9 ? "0" + (month + 1) : (month + 1) + "";
        String dia = day < 9 ? "0" + (day) : (day) + "";
        dataText.setText(dia + "/" + mes + "/" + year);
    }
}
