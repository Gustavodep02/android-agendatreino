package br.edu.fateczl.agendatreino.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GenericDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "agendatreino.db";
    private static final int DATABASE_VER = 1;

    private static final String CREATE_TABLE_EXERCICIO = "CREATE TABLE exercicio" +
            "(codigo INTEGER PRIMARY KEY, nome TEXT," +
            " descricao TEXT, grupo TEXT)";

    private static final String CREATE_TABLE_TREINO = "CREATE TABLE treino" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, dia TEXT not null)";

    private static final String CREATE_TABLE_TREINO_EXERCICIO = "CREATE TABLE treino_exercicio" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, id_treino INTEGER not null," +
            " id_exercicio INTEGER not null, series INTEGER not null," +
            " reps INTEGER not null, FOREIGN KEY(id_treino) REFERENCES treino(id)," +
            " FOREIGN KEY(id_exercicio) REFERENCES exercicio(codigo))";

    private static final String INSERT_TREINO = "INSERT INTO treino (dia) VALUES ('Segunda-feira'), ('Terca-feira'), ('Quarta-feira'), ('Quinta-feira'), ('Sexta-feira'), ('Sabado'), ('Domingo')";

    public GenericDAO(Context context) {
        super(context,DATABASE,null,DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXERCICIO);
        db.execSQL(CREATE_TABLE_TREINO);
        db.execSQL(CREATE_TABLE_TREINO_EXERCICIO);
        db.execSQL(INSERT_TREINO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS treino_exercicio");
            db.execSQL("DROP TABLE IF EXISTS treino");
            db.execSQL("DROP TABLE IF EXISTS exercicio");
            onCreate(db);
        }
    }
}
