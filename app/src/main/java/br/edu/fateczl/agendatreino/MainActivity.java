package br.edu.fateczl.agendatreino;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            carregaFragment(bundle);
        }else {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment, new InicioFragment());
            ft.commit();
        }
    }
    private void carregaFragment(Bundle bundle){
        String tipo = bundle.getString("tipo");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(tipo.equals("exercicio")){
            fragment = new ExercicioFragment();
        }else if (tipo.equals("treino")){
            fragment = new TreinoFragment();
        }else{
            fragment = new ListaFragment();
        }
        ft.replace(R.id.fragment, fragment);
        ft.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);

        if (id == R.id.item_exercicio){
            bundle.putString("tipo", "exercicio");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }else if (id == R.id.item_treino){
            bundle.putString("tipo", "treino");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }else{
            bundle.putString("tipo", "lista");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
    }
}