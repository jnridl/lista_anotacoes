package id.logistics.ceep.ui.activity;

import static id.logistics.ceep.ui.activity.NotaActivityContantes.CHAVE_NOTA;
import static id.logistics.ceep.ui.activity.NotaActivityContantes.COD_REQUISAO_INSERT_NOTA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.logistics.ceep.R;
import id.logistics.ceep.dao.NotaDAO;
import id.logistics.ceep.model.Nota;
import id.logistics.ceep.ui.recyclerview.adapter.ListaNotasAdapter;
import id.logistics.ceep.ui.recyclerview.adapter.listener.OnItemClickListener;

public class ListaNotasActivity extends AppCompatActivity {


    private ListaNotasAdapter adapter;
    private Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        List<Nota> todasNotas = pegaTodasNotas();
        configuraReciclerView(todasNotas);
        botaoInsereNota();

    }

    private void botaoInsereNota() {
        TextView botaoInsereNota = findViewById(R.id.lista_notas_insere_nota);
        botaoInsereNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiBotaoFormActivity();
            }
        });
    }

    private void vaiBotaoFormActivity() {
        Intent iniciaFormularioNota = new Intent(ListaNotasActivity.this, FormNotaActivity.class);
        startActivityForResult(iniciaFormularioNota, COD_REQUISAO_INSERT_NOTA);
    }

    private List<Nota> pegaTodasNotas() {
        NotaDAO dao = new NotaDAO();

        for(int i = 0; i < 10; i++){
            dao.insere(new Nota("Titulo " + (i+1), "Descrição da nota de teste"));
        }

        return dao.todos();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(ehResultadoComNota(requestCode, resultCode, data)){
            Nota notaRecebida = (Nota) data.getSerializableExtra(CHAVE_NOTA);
            adicionaNota(notaRecebida);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void adicionaNota(Nota nota) {
        new NotaDAO().insere(nota);
        adapter.adiciona(nota);
    }

    private boolean ehResultadoComNota(int requestCode, int resultCode, @Nullable Intent data) {
        return ehCodRequestNota(requestCode) && ehCodResultNota(resultCode) & temNota(data);
    }

    private boolean temNota(@Nullable Intent data) {
        return data.hasExtra(CHAVE_NOTA);
    }

    private boolean ehCodResultNota(int resultCode) {
        return resultCode == 2;
    }

    private boolean ehCodRequestNota(int requestCode) {
        return requestCode == COD_REQUISAO_INSERT_NOTA;
    }

    private void configuraReciclerView(List<Nota> todasNotas) {

        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);

    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(this, todasNotas);
        listaNotas.setAdapter(adapter);

        adapter.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Nota nota) {
                Toast.makeText(ListaNotasActivity.this,
                        nota.getTitulo(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}