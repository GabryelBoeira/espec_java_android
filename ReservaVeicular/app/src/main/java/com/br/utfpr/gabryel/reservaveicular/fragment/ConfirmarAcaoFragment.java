package com.br.utfpr.gabryel.reservaveicular.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.br.utfpr.gabryel.reservaveicular.R;

public class ConfirmarAcaoFragment extends DialogFragment {

    private String titulo;

    public ConfirmarAcaoFragment(final String titulo) {
        this.titulo = titulo;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(titulo)
            .setPositiveButton(R.string.label_sim, (dialog, id) -> {

            })
            .setNegativeButton(R.string.label_nao, (dialog, id) -> dialog.dismiss());
        return builder.create();
    }

}
