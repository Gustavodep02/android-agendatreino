package br.edu.fateczl.agendatreino.controller;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.agendatreino.model.Exercicio;
import br.edu.fateczl.agendatreino.model.Treino;
import br.edu.fateczl.agendatreino.persistence.ExercicioDAO;
import br.edu.fateczl.agendatreino.persistence.TreinoDAO;

public class TreinoController implements IController<Treino> {
    private final TreinoDAO tDao;

    public TreinoController(TreinoDAO tDao) {
        this.tDao = tDao;
    }

    @Override
    public void insert(Treino treino) throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        tDao.insert(treino);
        tDao.close();
    }

    @Override
    public void update(Treino treino) throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        tDao.update(treino);
        tDao.close();
    }

    @Override
    public void delete(Treino treino) throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        tDao.delete(treino);
        tDao.close();
    }

    @Override
    public Treino findOne(Treino treino) throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        return tDao.findOne(treino);
    }

    @Override
    public List<Treino> findAll() throws SQLException {
        if(tDao.open() == null){
            tDao.open();
        }
        return tDao.findAll();
    }
}
