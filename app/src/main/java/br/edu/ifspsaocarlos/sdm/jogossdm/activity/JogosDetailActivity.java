package br.edu.ifspsaocarlos.sdm.jogossdm.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import br.edu.ifspsaocarlos.sdm.jogossdm.R;
import br.edu.ifspsaocarlos.sdm.jogossdm.fragment.JogosDetailFragment;
import br.edu.ifspsaocarlos.sdm.jogossdm.model.Jogos;

public class JogosDetailActivity extends AppCompatActivity {

    public static final String EXTRA_JOGOS = "jogos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogos_detail);

        if (savedInstanceState == null) {
            //_________________Recebimento do objeto Jogos proveniente da tela de listagem
            Intent intent = getIntent();
            Jogos jogos = (Jogos) intent.getSerializableExtra(EXTRA_JOGOS);
            //_________________Instância do Fragment de detalhes
            JogosDetailFragment fragment = JogosDetailFragment.newInstance(jogos);
            //_________________Adição dinâmica do Fragment por intermédio do método
            // getSupportFragmentManager() da classe FragmentManager
            FragmentManager fragmentManager = getSupportFragmentManager();
            //_________________Nova transação utilizando o método beginTransaction() que retorna um
            // FragmentTransaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //_________________Adição do fragmento ao FrameLayout
            fragmentTransaction.replace(R.id.detail, fragment, JogosDetailFragment.TAG_DETAIL);
            fragmentTransaction.commit();
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
