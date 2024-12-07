package br.edu.fateczl.agendatreino.persistence;

import java.sql.SQLException;

public interface IExercicioDAO {
    public ExercicioDAO open() throws SQLException;
    public void close();
}
