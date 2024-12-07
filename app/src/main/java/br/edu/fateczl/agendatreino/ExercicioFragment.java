package br.edu.fateczl.agendatreino;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.agendatreino.controller.ExercicioController;
import br.edu.fateczl.agendatreino.model.Exercicio;
import br.edu.fateczl.agendatreino.persistence.ExercicioDAO;

public class ExercicioFragment extends Fragment {

    private View view;

    private EditText etCodigoEx, etNomeEx, etDescEx, etGrupoEx;

    private Button btnInserirEx, btnModificarEx, btnExcluirEx, btnListarEx, btnBuscarEx;

    private TextView tvListarEx;

    private ExercicioController eCont;

    public ExercicioFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_exercicio, container, false);

        etCodigoEx = view.findViewById(R.id.etCodigoEx);
        etNomeEx = view.findViewById(R.id.etNomeEx);
        etDescEx = view.findViewById(R.id.etDescEx);
        etGrupoEx = view.findViewById(R.id.etGrupoEx);
        btnInserirEx = view.findViewById(R.id.btnInserirEx);
        btnModificarEx = view.findViewById(R.id.btnModificarEx);
        btnExcluirEx = view.findViewById(R.id.btnExcluirEx);
        btnListarEx = view.findViewById(R.id.btnListarEx);
        btnBuscarEx = view.findViewById(R.id.btnBuscarEx);
        tvListarEx = view.findViewById(R.id.tvListarEx);
        tvListarEx.setMovementMethod(new ScrollingMovementMethod());

        eCont = new ExercicioController(new ExercicioDAO(view.getContext()));

        btnInserirEx.setOnClickListener(op -> acaoInserir());
        btnModificarEx.setOnClickListener(op -> acaoModificar());
        btnExcluirEx.setOnClickListener(op -> acaoExcluir());
        btnListarEx.setOnClickListener(op -> acaoListar());
        btnBuscarEx.setOnClickListener(op -> acaoBuscar());

        return view;
    }

    private void acaoInserir(){
        Exercicio ex = montaExercicio();
        try {
            eCont.insert(ex);
            Toast.makeText(view.getContext(),"Exercicio inserido com sucesso", Toast.LENGTH_LONG).show();
        }catch(SQLException e){
            Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoModificar(){
        Exercicio ex = montaExercicio();
        try {
            eCont.update(ex);
            Toast.makeText(view.getContext(),"Exercicio atualizado com sucesso", Toast.LENGTH_LONG).show();
        }catch(SQLException e){
            Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoExcluir(){
        Exercicio ex = montaExercicio();
        try {
            eCont.delete(ex);
            Toast.makeText(view.getContext(),"Exercicio excluido com sucesso", Toast.LENGTH_LONG).show();
        }catch(SQLException e){
            Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void acaoBuscar(){
        Exercicio ex = montaExercicio();
        try {
            ex = eCont.findOne(ex);
            if(ex.getNome() != null){
                preencheCampos(ex);
            }else {
                Toast.makeText(view.getContext(), "Exercicio nao encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        }catch(SQLException e){
            Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void acaoListar(){
       tvListarEx.setText("");
        try{
            List<Exercicio> exercicios = eCont.findAll();
            for (Exercicio e : exercicios){
                tvListarEx.append(e.toString() + "\n");
            }
        }catch(SQLException e){
            Toast.makeText(view.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    private Exercicio montaExercicio(){
        Exercicio e = new Exercicio();
        e.setCodigo(Integer.parseInt(etCodigoEx.getText().toString()));
        e.setNome(etNomeEx.getText().toString());
        e.setDescricao(etDescEx.getText().toString());
        e.setGrupo(etGrupoEx.getText().toString());
        return e;
    }

    private void limpaCampos(){
        etCodigoEx.setText("");
        etNomeEx.setText("");
        etDescEx.setText("");
        etGrupoEx.setText("");
    }

    public void preencheCampos(Exercicio e){
        etCodigoEx.setText(String.valueOf(e.getCodigo()));
        etNomeEx.setText(e.getNome());
        etDescEx.setText(e.getDescricao());
        etGrupoEx.setText(e.getGrupo());
    }
}