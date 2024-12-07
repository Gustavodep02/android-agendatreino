package br.edu.fateczl.agendatreino.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.agendatreino.model.Exercicio;
import br.edu.fateczl.agendatreino.model.Treino;
import br.edu.fateczl.agendatreino.model.TreinoExercicio;

public class TreinoExercicioDAO implements ITreinoExercicioDAO , ICRUDDAO<TreinoExercicio> {

    private final Context context;
    private GenericDAO gDao;

    private SQLiteDatabase database;

    public TreinoExercicioDAO(Context context){
        this.context = context;
    }

    @Override
    public TreinoExercicioDAO open() {
        gDao = new GenericDAO(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(TreinoExercicio treinoExercicio) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("series", treinoExercicio.getSeries());
        Exercicio exercicio = treinoExercicio.getExercicio();
        values.put("id_exercicio", exercicio.getCodigo());
        Treino treino = treinoExercicio.getTreino();
        values.put("id_treino", treino.getId());
        values.put("reps", treinoExercicio.getReps());
        database.insert("treino_exercicio", null, values);
    }

    @Override
    public int update(TreinoExercicio treinoExercicio) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("series", treinoExercicio.getSeries());
        Exercicio exercicio = treinoExercicio.getExercicio();
        values.put("id_exercicio", exercicio.getCodigo());
        Treino treino = treinoExercicio.getTreino();
        values.put("id_treino", treino.getId());
        values.put("reps", treinoExercicio.getReps());
        int ret = database.update(
                "treino_exercicio",
                values,
                "id_treino = ? AND id_exercicio = ?",
                new String[] { String.valueOf(treinoExercicio.getTreino().getId()), String.valueOf(treinoExercicio.getExercicio().getCodigo()) }
        );
        return ret;
    }

    @Override
    public void delete(TreinoExercicio treinoExercicio) throws SQLException {
        database.delete(
                "treino_exercicio",
                "id_treino = ? AND id_exercicio = ?",
                new String[] { String.valueOf(treinoExercicio.getTreino().getId()), String.valueOf(treinoExercicio.getExercicio().getCodigo()) }
        );
    }

    @SuppressLint("Range")
    @Override
    public TreinoExercicio findOne(TreinoExercicio treinoExercicio) throws SQLException {
        String sql ="SELECT te.*, t.dia, e.nome, e.descricao, e.grupo " +
                "FROM treino_exercicio te " +
                "JOIN treino t ON te.id_treino = t.id " +
                "JOIN exercicio e ON te.id_exercicio = e.codigo " +
                "WHERE te.id = " + treinoExercicio.getId();
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            Treino t = new Treino();
            t.setId(cursor.getInt(cursor.getColumnIndex("id_treino")));
            t.setDia(cursor.getString(cursor.getColumnIndex("dia")));

            Exercicio e = new Exercicio();
            e.setCodigo(cursor.getInt(cursor.getColumnIndex("id_exercicio")));
            e.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            e.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            e.setGrupo(cursor.getString(cursor.getColumnIndex("grupo")));

            treinoExercicio.setId(cursor.getInt(cursor.getColumnIndex("id")));
            treinoExercicio.setSeries(cursor.getInt(cursor.getColumnIndex("series")));
            treinoExercicio.setReps(cursor.getInt(cursor.getColumnIndex("reps")));
            treinoExercicio.setTreino(t);
            treinoExercicio.setExercicio(e);
        }
        cursor.close();
        return treinoExercicio;

    }

    @SuppressLint("Range")
    @Override
    public List<TreinoExercicio> findAll() throws SQLException {
        List<TreinoExercicio> treinoExercicios = new ArrayList<>();
        String sql ="SELECT te.*, t.dia, e.nome, e.descricao, e.grupo " +
                "FROM treino_exercicio te " +
                "JOIN treino t ON te.id_treino = t.id " +
                "JOIN exercicio e ON te.id_exercicio = e.codigo ORDER BY te.id_treino";
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()){
            TreinoExercicio treinoExercicio = new TreinoExercicio();
            Treino t = new Treino();
            t.setId(cursor.getInt(cursor.getColumnIndex("id_treino")));
            t.setDia(cursor.getString(cursor.getColumnIndex("dia")));

            Exercicio e = new Exercicio();
            e.setCodigo(cursor.getInt(cursor.getColumnIndex("id_exercicio")));
            e.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            e.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            e.setGrupo(cursor.getString(cursor.getColumnIndex("grupo")));

            treinoExercicio.setId(cursor.getInt(cursor.getColumnIndex("id")));
            treinoExercicio.setSeries(cursor.getInt(cursor.getColumnIndex("series")));
            treinoExercicio.setReps(cursor.getInt(cursor.getColumnIndex("reps")));
            treinoExercicio.setTreino(t);
            treinoExercicio.setExercicio(e);

            treinoExercicios.add(treinoExercicio);
            cursor.moveToNext();
        }
        cursor.close();
        return treinoExercicios;
    }

    private static ContentValues getContentValues(TreinoExercicio treinoExercicio) {
        ContentValues cv = new ContentValues();
        cv.put("id", treinoExercicio.getId());
        cv.put("id_treino", treinoExercicio.getTreino().getId());
        cv.put("id_exercicio", treinoExercicio.getExercicio().getCodigo());
        cv.put("series", treinoExercicio.getSeries());
        cv.put("reps", treinoExercicio.getReps());

        return cv;
    }
}
