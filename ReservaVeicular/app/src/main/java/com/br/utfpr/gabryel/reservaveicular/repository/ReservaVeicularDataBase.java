package com.br.utfpr.gabryel.reservaveicular.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.br.utfpr.gabryel.reservaveicular.converters.ConverterLocalDate;
import com.br.utfpr.gabryel.reservaveicular.model.Motorista;
import com.br.utfpr.gabryel.reservaveicular.model.Reserva;
import com.br.utfpr.gabryel.reservaveicular.model.Veiculo;

@Database(entities = {Motorista.class, Veiculo.class, Reserva.class}, version = 1)
@TypeConverters({ConverterLocalDate.class})
public abstract class ReservaVeicularDataBase extends RoomDatabase {

    private static ReservaVeicularDataBase DATABASE;

    public abstract MotoristaDao motoristaDao();
    public abstract VeiculoDao veiculoDao();
    public abstract ReservaDao reservaDao();


    public static ReservaVeicularDataBase getDatabase(final Context context) {
        if (DATABASE != null) return DATABASE;

        synchronized (ReservaVeicularDataBase.class) {
            if (DATABASE == null) {
                Builder builder = Room.
                        databaseBuilder(context, ReservaVeicularDataBase.class, "reservaVeicular.db");
                DATABASE = (ReservaVeicularDataBase) builder.build();
            }
        }

        return DATABASE;
    }

}
