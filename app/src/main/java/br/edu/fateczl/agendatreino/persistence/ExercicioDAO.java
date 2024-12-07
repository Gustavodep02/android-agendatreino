package br.edu.fateczl.agendatreino.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.agendatreino.model.Exercicio;

public class ExercicioDAO implements IExercicioDAO, ICRUDDAO<Exercicio> {

    private final Context context;
    private GenericDAO gDao;

    private SQLiteDatabase database;

    public ExercicioDAO(Context context){
        this.context = context;
    }

    @Override
    public ExercicioDAO open() throws SQLException {
        gDao = new GenericDAO(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Exercicio exercicio) throws SQLException {
        ContentValues cv = getContentValues(exercicio);
        database.insert("exercicio", null, cv);
    }

    @Override
    public int update(Exercicio exercicio) throws SQLException {
        ContentValues cv = getContentValues(exercicio);
        int ret = database.update("exercicio", cv, "codigo = "+ exercicio.getCodigo(), null);;
        return ret;
    }

    @Override
    public void delete(Exercicio exercicio) throws SQLException {
        database.delete("exercicio", "codigo = "+ exercicio.getCodigo(), null);
    }

    @SuppressLint("Range")
    @Override
    public Exercicio findOne(Exercicio exercicio) throws SQLException {
        String sql = "SELECT * FROM exercicio WHERE codigo = " + exercicio.getCodigo();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            exercicio.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            exercicio.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            exercicio.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            exercicio.setGrupo(cursor.getString(cursor.getColumnIndex("grupo")));
        }
        cursor.close();
        return exercicio;
    }

    @SuppressLint("Range")
    @Override
    public List<Exercicio> findAll() throws SQLException {
        List<Exercicio> exercicios = new ArrayList<>();
        String sql = "SELECT * FROM exercicio";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            Exercicio exercicio = new Exercicio();
            exercicio.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            exercicio.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            exercicio.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            exercicio.setGrupo(cursor.getString(cursor.getColumnIndex("grupo")));

            exercicios.add(exercicio);
            cursor.moveToNext();
        }
        cursor.close();
        return exercicios;
    }

    private static ContentValues getContentValues(Exercicio exercicio) {
        ContentValues cv = new ContentValues();
        cv.put("codigo", exercicio.getCodigo());
        cv.put("nome", exercicio.getNome());
        cv.put("descricao", exercicio.getDescricao());
        cv.put("grupo", exercicio.getGrupo());

        return cv;
    }
}
