package br.edu.fateczl.agendatreino.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.agendatreino.model.Exercicio;
import br.edu.fateczl.agendatreino.persistence.ExercicioDAO;

public class ExercicioController implements IController<Exercicio>{

    private final ExercicioDAO eDao;

    public ExercicioController(ExercicioDAO eDao) {
        this.eDao = eDao;
    }

    @Override
    public void insert(Exercicio exercicio) throws SQLException {
        if(eDao.open() == null){
            eDao.open();
        }
        eDao.insert(exercicio);
        eDao.close();
    }

    @Override
    public void update(Exercicio exercicio) throws SQLException {
        if(eDao.open() == null){
            eDao.open();
        }
        eDao.update(exercicio);
        eDao.close();
    }

    @Override
    public void delete(Exercicio exercicio) throws SQLException {
        if(eDao.open() == null){
            eDao.open();
        }
        eDao.delete(exercicio);
        eDao.close();
    }

    @Override
    public Exercicio findOne(Exercicio exercicio) throws SQLException {
        if(eDao.open() == null){
            eDao.open();
        }
        return eDao.findOne(exercicio);
    }

    @Override
    public List<Exercicio> findAll() throws SQLException {
        if(eDao.open() == null){
            eDao.open();
        }
        return eDao.findAll();
    }
}
