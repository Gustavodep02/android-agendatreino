package br.edu.fateczl.agendatreino;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.agendatreino.controller.ExercicioController;
import br.edu.fateczl.agendatreino.controller.TreinoController;
import br.edu.fateczl.agendatreino.controller.TreinoExercicioController;
import br.edu.fateczl.agendatreino.model.Exercicio;
import br.edu.fateczl.agendatreino.model.Treino;
import br.edu.fateczl.agendatreino.model.TreinoExercicio;
import br.edu.fateczl.agendatreino.persistence.ExercicioDAO;
import br.edu.fateczl.agendatreino.persistence.TreinoDAO;
import br.edu.fateczl.agendatreino.persistence.TreinoExercicioDAO;

public class TreinoFragment extends Fragment {

    private View view;

    private EditText etRepsTre, etSeriesTre;

    private Button btnInserirTre, btnModificarTre, btnExcluirTre;

    private Spinner spExTre,spDiaTre;

    private TextView tvListarTre;

    private TreinoController tCont;

    private ExercicioController eCont;

    private TreinoExercicioController teCont;

    private List<Exercicio> exercicios;



    public TreinoFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_treino, container, false);

        etRepsTre = view.findViewById(R.id.etRepsTre);
        etSeriesTre = view.findViewById(R.id.etSeriesTre);
        btnInserirTre = view.findViewById(R.id.btnInserirTre);
        btnModificarTre = view.findViewById(R.id.btnModificarTre);
        btnExcluirTre = view.findViewById(R.id.btnExcluirTre);
        spExTre = view.findViewById(R.id.spExTre);
        spDiaTre = view.findViewById(R.id.spDiaTre);
        tvListarTre = view.findViewById(R.id.tvListarTre);
        tvListarTre.setMovementMethod(new ScrollingMovementMethod());

        tCont = new TreinoController(new TreinoDAO(view.getContext()));
        eCont = new ExercicioController(new ExercicioDAO(view.getContext()));
        teCont = new TreinoExercicioController(new TreinoExercicioDAO(view.getContext()));
        preencheSpinnerEx();
        preencheSpinnerDia();


        btnInserirTre.setOnClickListener(op -> acaoInserir());
        btnModificarTre.setOnClickListener(op -> acaoModificar());
        btnExcluirTre.setOnClickListener(op -> acaoExcluir());


        return view;
    }

    private void acaoInserir() {
        int spPosEx = spExTre.getSelectedItemPosition();
        int spPosDia = spDiaTre.getSelectedItemPosition();
        if(spPosEx==0 || spPosDia==0){
            Toast.makeText(view.getContext(),"Selecione um exercício", Toast.LENGTH_LONG).show();

        }else{
            TreinoExercicio te = montaTreinoExercicio();
            try {
                teCont.insert(te);
                Toast.makeText(view.getContext(),"Treino inserido com sucesso", Toast.LENGTH_LONG).show();
            }catch(SQLException e){
                Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        }
    }

    private void acaoModificar() {
        int spPosEx = spExTre.getSelectedItemPosition();
        int spPosDia = spDiaTre.getSelectedItemPosition();
        if(spPosEx==0 || spPosDia==0){
            Toast.makeText(view.getContext(),"Selecione um exercício", Toast.LENGTH_LONG).show();
        }else{
            TreinoExercicio te = montaTreinoExercicio();
            try {
                teCont.update(te);
                Toast.makeText(view.getContext(),"Treino atualizado com sucesso", Toast.LENGTH_LONG).show();
            }catch(SQLException e){
                Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        }
        
    }

    private void acaoExcluir() {

            TreinoExercicio te = new TreinoExercicio();
        te.setTreino((Treino) spDiaTre.getSelectedItem());
        te.setExercicio((Exercicio) spExTre.getSelectedItem());
            try {
                if(spDiaTre.getSelectedItemPosition()!=0 && spExTre.getSelectedItemPosition()!=0){
                    teCont.delete(te);
                    Toast.makeText(view.getContext(), "Treino excluido com sucesso", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(view.getContext(), "Selecione um treino e exercicio", Toast.LENGTH_LONG).show();
                }
            }catch(SQLException e){
                Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        
    }

    private void preencheSpinnerEx() {
        Exercicio e0 = new Exercicio();
        e0.setCodigo(0);
        e0.setNome("Selecione um exercício");
        e0.setDescricao("");
        e0.setGrupo("");

        try{
            exercicios = eCont.findAll();
            exercicios.add(0, e0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, exercicios);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spExTre.setAdapter(ad);

        }catch(SQLException e){
            Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void preencheSpinnerDia() {
        Treino t0 = new Treino();
        Treino t1 = new Treino();
        Treino t2 = new Treino();
        Treino t3 = new Treino();
        Treino t4 = new Treino();
        Treino t5 = new Treino();
        Treino t6 = new Treino();
        Treino t7 = new Treino();


        t0.setId(8);
        t0.setDia("Selecione um dia");
        t1.setId(1);
        t1.setDia("Segunda");
        t2.setId(2);
        t2.setDia("Terça");
        t3.setId(3);
        t3.setDia("Quarta");
        t4.setId(4);
        t4.setDia("Quinta");
        t5.setId(5);
        t5.setDia("Sexta");
        t6.setId(6);
        t6.setDia("Sábado");
        t7.setId(7);
        t7.setDia("Domingo");

        List<Treino> treinos = new ArrayList<>();
                treinos.add(0, t0);
                treinos.add(1, t1);
                treinos.add(2, t2);
                treinos.add(3, t3);
                treinos.add(4, t4);
                treinos.add(5, t5);
                treinos.add(6, t6);
                treinos.add(7, t7);
                ArrayAdapter<Treino> ad = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, treinos);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spDiaTre.setAdapter(ad);

    }

    private TreinoExercicio montaTreinoExercicio() {
        TreinoExercicio te = new TreinoExercicio();
        te.setSeries(Integer.parseInt(etSeriesTre.getText().toString()));
        te.setReps(Integer.parseInt(etRepsTre.getText().toString()));
        te.setTreino((Treino) spDiaTre.getSelectedItem());
        te.setExercicio((Exercicio) spExTre.getSelectedItem());
        return te;
    }
    private void limpaCampos() {
        etSeriesTre.setText("");
        etRepsTre.setText("");
        spDiaTre.setSelection(0);
        spExTre.setSelection(0);
    }

}