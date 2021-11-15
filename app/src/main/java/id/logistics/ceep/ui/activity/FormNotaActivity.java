package id.logistics.ceep.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import id.logistics.ceep.R;
import id.logistics.ceep.dao.NotaDAO;
import id.logistics.ceep.model.Nota;

public class FormNotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_nota);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        if(item.getItemId() == R.id.menu_form_nota_ic_salva){

            EditText titulo = findViewById(R.id.formulario_nota_titulo);
            EditText descricao = findViewById(R.id.formulario_nota_descricao);

            Nota notaCriada = new Nota(titulo.getText().toString(), descricao.getText().toString());
            new NotaDAO().insere(notaCriada);
            finish();

        }
        
        return super.onOptionsItemSelected(item);
        
    }
}