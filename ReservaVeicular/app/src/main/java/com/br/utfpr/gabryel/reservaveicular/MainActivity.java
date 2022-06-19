package com.br.utfpr.gabryel.reservaveicular;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.br.utfpr.gabryel.reservaveicular.fragment.DatePickerFragment;
import com.br.utfpr.gabryel.reservaveicular.model.enums.TipoCnh;
import com.br.utfpr.gabryel.reservaveicular.utils.MontarUtils;


public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCnh;
    private final MontarUtils montarUtils;

    public MainActivity() {
        this.montarUtils = new MontarUtils(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCnh = findViewById(R.id.spinnerCnh);
        spinnerCnh.setAdapter(montarUtils.carregarDadosSpinner(TipoCnh.descricaoList()));
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(findViewById(R.id.editDtNascimento));
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}