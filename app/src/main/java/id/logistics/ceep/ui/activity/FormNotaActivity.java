package id.logistics.ceep.ui.activity;

import static id.logistics.ceep.ui.activity.NotaActivityContantes.CHAVE_NOTA;
import static id.logistics.ceep.ui.activity.NotaActivityContantes.COD_RESULT_NOTA_CRIADA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import id.logistics.ceep.R;
import id.logistics.ceep.model.Nota;

public class FormNotaActivity extends AppCompatActivity {

    private int posicaoRecebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_nota);

        Intent dadosRecebidos = getIntent();

        if(dadosRecebidos.hasExtra(CHAVE_NOTA) && dadosRecebidos.hasExtra("posicao")){

            Nota notaRecebida = (Nota) dadosRecebidos.getSerializableExtra(CHAVE_NOTA);

            posicaoRecebida = dadosRecebidos.getIntExtra("posicao", -1);

            TextView titulo = findViewById(R.id.formulario_nota_titulo);
            titulo.setText(notaRecebida.getTitulo());

            TextView descricao = findViewById(R.id.formulario_nota_descricao);
            descricao.setText(notaRecebida.getDescricao());

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        if(ehMenuSalvaNota(item)){

            Nota notaCriada = criaNota();
            retornaNota(notaCriada);
            finish();

        }
        
        return super.onOptionsItemSelected(item);
        
    }

    private void retornaNota(Nota nota) {
        Intent resultInsercao = new Intent();
        resultInsercao.putExtra(CHAVE_NOTA, nota);
        resultInsercao.putExtra("posicao", posicaoRecebida);
        setResult(COD_RESULT_NOTA_CRIADA, resultInsercao);
    }

    @NonNull
    private Nota criaNota() {

        EditText titulo = findViewById(R.id.formulario_nota_titulo);
        EditText descricao = findViewById(R.id.formulario_nota_descricao);

        return new Nota(titulo.getText().toString(), descricao.getText().toString());

    }

    private boolean ehMenuSalvaNota(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_form_nota_ic_salva;
    }

}