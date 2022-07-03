package com.br.utfpr.gabryel.reservaveicular.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmarAcaoBuilder {

    public static void criarAcaoComDoisPosicionamentos(final Context contexto,
                                                                      final DialogInterface.OnClickListener acaoExecutar,
                                                                      final String labelTitulo,
                                                                      final String labelPositive,
                                                                      final String labelCancel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

        builder.setMessage(labelTitulo)
            .setPositiveButton(labelPositive, acaoExecutar)
            .setNegativeButton(labelCancel, acaoExecutar);

        builder.create().show();
    }

}
