package br.edu.fateczl.agendatreino.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.agendatreino.model.Treino;

public class TreinoDAO implements ITreinoDAO, ICRUDDAO<Treino> {

    private final Context context;
    private GenericDAO gDao;

    private SQLiteDatabase database;

    public TreinoDAO(Context context){
        this.context = context;
    }
    @Override
    public TreinoDAO open() throws SQLException {
        gDao = new GenericDAO(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Treino treino) throws SQLException {
        ContentValues cv = getContentValues(treino);
        database.insert("treino",null,cv);

    }

    @Override
    public int update(Treino treino) throws SQLException {
        ContentValues cv = getContentValues(treino);
        int ret = database.update("treino",cv,"id = "+treino.getId(),null);
        return ret;
    }

    @Override
    public void delete(Treino treino) throws SQLException {
        database.delete("treino","id = "+treino.getId(),null);
    }

    @SuppressLint("Range")
    @Override
    public Treino findOne(Treino treino) throws SQLException {
        String sql = "SELECT * FROM treino WHERE id = "+treino.getId();
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){

            treino.setId(cursor.getInt(cursor.getColumnIndex("id")));
            treino.setDia(cursor.getString(cursor.getColumnIndex("dia")));

        }
        cursor.close();
        return treino;
    }

    @SuppressLint("Range")
    @Override
    public List<Treino> findAll() throws SQLException {
        List<Treino> treinos =new ArrayList<>();
        String sql = "SELECT * FROM treino";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
         while(!cursor.isAfterLast()){
            Treino treino = new Treino();
             treino.setId(cursor.getInt(cursor.getColumnIndex("id")));
             treino.setDia(cursor.getString(cursor.getColumnIndex("dia")));

            treinos.add(treino);
        }
        cursor.close();
        return treinos;
    }

    private static ContentValues getContentValues(Treino treino) {
        ContentValues cv = new ContentValues();
        cv.put("dia", treino.getDia());
        cv.put("id", treino.getId());
        return cv;
    }
}
