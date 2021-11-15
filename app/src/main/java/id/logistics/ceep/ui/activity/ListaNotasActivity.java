package id.logistics.ceep.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.logistics.ceep.R;
import id.logistics.ceep.dao.NotaDAO;
import id.logistics.ceep.model.Nota;
import id.logistics.ceep.ui.recyclerview.adapter.ListaNotasAdapter;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        List<Nota> todasNotas = notasExemplo();
        configuraReciclerView(todasNotas);

    }

    private List<Nota> notasExemplo() {

        NotaDAO dao = new NotaDAO();

        for(int i = 1; i <= 10; i++){
            dao.insere(new Nota("Nota " + i, "Descrição nota " + i));
        }

        return dao.todos();

    }

    private void configuraReciclerView(List<Nota> todasNotas) {

        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);

    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        listaNotas.setAdapter(new ListaNotasAdapter(this, todasNotas));
    }

}