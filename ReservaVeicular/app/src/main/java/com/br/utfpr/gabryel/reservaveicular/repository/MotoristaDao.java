package com.br.utfpr.gabryel.reservaveicular.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.br.utfpr.gabryel.reservaveicular.model.Motorista;

import java.util.List;

@Dao
public interface MotoristaDao {

    @Insert
    long insert(Motorista motorista);

    @Delete
    void delete(Motorista motorista);

    @Update
    void update(Motorista motorista);

    @Query("SELECT * FROM motoristas WHERE id = :id")
    Motorista getById(long id);

    @Query("SELECT * FROM motoristas ORDER BY nome ASC")
    List<Motorista> buscarTodosMotoristas();
}
