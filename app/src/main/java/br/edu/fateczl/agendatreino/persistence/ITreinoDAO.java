package br.edu.fateczl.agendatreino.persistence;

import java.sql.SQLException;

public interface ITreinoDAO {
    public TreinoDAO open() throws SQLException;
    public void close();
}
