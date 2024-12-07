package br.edu.fateczl.agendatreino.persistence;

import java.sql.SQLException;

public interface ITreinoExercicioDAO {
    public TreinoExercicioDAO open() throws SQLException;
    public void close();
}
