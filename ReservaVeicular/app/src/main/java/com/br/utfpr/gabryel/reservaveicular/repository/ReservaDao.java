package com.br.utfpr.gabryel.reservaveicular.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.br.utfpr.gabryel.reservaveicular.model.Reserva;

import java.util.List;

@Dao
public interface ReservaDao {

    @Insert
    long insert(Reserva reserva);

    @Delete
    void delete(Reserva reserva);

    @Update
    void update(Reserva reserva);

    @Query("SELECT * FROM reservas WHERE id = :id")
    Reserva getById(long id);

    @Query("SELECT * FROM reservas ORDER BY tipo ASC")
    List<Reserva> buscarTodosReservas();
}
