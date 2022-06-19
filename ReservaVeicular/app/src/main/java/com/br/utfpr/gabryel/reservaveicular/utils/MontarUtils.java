package com.br.utfpr.gabryel.reservaveicular.utils;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class MontarUtils {

    private Context CONTEXT;

    public MontarUtils(final Context context) {
        this.CONTEXT = context;
    }

    public ArrayAdapter<String> carregarDadosSpinner(final List<String> valores) {
        return new ArrayAdapter<>(CONTEXT, android.R.layout.simple_spinner_item, valores);
    }

}
