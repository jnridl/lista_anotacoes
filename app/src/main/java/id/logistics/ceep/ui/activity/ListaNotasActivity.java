package id.logistics.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.logistics.ceep.R;
import id.logistics.ceep.dao.NotaDAO;
import id.logistics.ceep.model.Nota;
import id.logistics.ceep.ui.recyclerview.adapter.ListaNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {

    private ListaNotasAdapter adapter;
    private List<Nota> todasNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        todasNotas = notasExemplo();
        configuraReciclerView(todasNotas);

        TextView botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
        botaoInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iniciaFormularioNota = new Intent(ListaNotasActivity.this, FormNotaActivity.class);
                startActivity(iniciaFormularioNota);
            }
        });


    }

    @Override
    protected void onResume() {

        NotaDAO dao = new NotaDAO();
        todasNotas.clear();
        todasNotas.addAll(dao.todos());
        adapter.notifyDataSetChanged();
        super.onResume();

    }

    private List<Nota> notasExemplo() {

        NotaDAO dao = new NotaDAO();

        for(int i = 1; i <= 3; i++){
            dao.insere(new Nota("Nota " + i, "Descrição nota " + i));
        }

        return dao.todos();

    }

    private void configuraReciclerView(List<Nota> todasNotas) {

        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);

    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(this, todasNotas);
        listaNotas.setAdapter(adapter);
    }

}