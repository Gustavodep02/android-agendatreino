package br.edu.fateczl.agendatreino.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.agendatreino.model.Treino;
import br.edu.fateczl.agendatreino.model.TreinoExercicio;
import br.edu.fateczl.agendatreino.persistence.TreinoDAO;
import br.edu.fateczl.agendatreino.persistence.TreinoExercicioDAO;

public class TreinoExercicioController implements IController<TreinoExercicio>{
    private final TreinoExercicioDAO tDao;

    public TreinoExercicioController(TreinoExercicioDAO tDao) {
        this.tDao = tDao;
    }

    @Override
    public void insert(TreinoExercicio treino) throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        tDao.insert(treino);
        tDao.close();
    }

    @Override
    public void update(TreinoExercicio treino) throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        tDao.update(treino);
        tDao.close();
    }

    @Override
    public void delete(TreinoExercicio treino) throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        tDao.delete(treino);
        tDao.close();
    }

    @Override
    public TreinoExercicio findOne(TreinoExercicio treino) throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        return tDao.findOne(treino);
    }

    @Override
    public List<TreinoExercicio> findAll() throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        return tDao.findAll();
    }
}
