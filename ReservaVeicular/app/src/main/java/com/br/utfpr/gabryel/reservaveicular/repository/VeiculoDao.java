package com.br.utfpr.gabryel.reservaveicular.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.br.utfpr.gabryel.reservaveicular.model.Veiculo;

import java.util.List;

@Dao
public interface VeiculoDao {

    @Insert
    long insert(Veiculo veiculo);

    @Delete
    void delete(Veiculo veiculo);

    @Update
    void update(Veiculo veiculo);

    @Query("SELECT * FROM veiculos WHERE id = :id")
    Veiculo getById(long id);

    @Query("SELECT * FROM veiculos")
    List<Veiculo> buscarTodosVeiculos();
}
