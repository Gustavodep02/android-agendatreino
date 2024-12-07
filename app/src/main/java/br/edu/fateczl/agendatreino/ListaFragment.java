package br.edu.fateczl.agendatreino;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.agendatreino.controller.ExercicioController;
import br.edu.fateczl.agendatreino.controller.TreinoExercicioController;
import br.edu.fateczl.agendatreino.model.Exercicio;
import br.edu.fateczl.agendatreino.model.TreinoExercicio;
import br.edu.fateczl.agendatreino.persistence.ExercicioDAO;
import br.edu.fateczl.agendatreino.persistence.TreinoExercicioDAO;

public class ListaFragment extends Fragment {

    private View view;

    private TextView tvSaidaLista;

    private TreinoExercicioController teCont;

    public ListaFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lista, container, false);

        tvSaidaLista = view.findViewById(R.id.tvSaidaLista);
        tvSaidaLista.setMovementMethod(new android.text.method.ScrollingMovementMethod());
        teCont = new TreinoExercicioController(new TreinoExercicioDAO(view.getContext()));
        listar();

        return view;
    }
    private void listar() {
        try {
            List<TreinoExercicio> tes = teCont.findAll();
            tvSaidaLista.setText("");
            String diaAtual = "";

            for (TreinoExercicio te : tes) {
                String dia = te.getTreino().getDia();

                if (!dia.equalsIgnoreCase(diaAtual)) {
                    diaAtual = dia;
                    tvSaidaLista.append(dia.toUpperCase() + ":\n\n");
                }

                tvSaidaLista.append("  " + te.toString() + "\n\n");
            }
        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}