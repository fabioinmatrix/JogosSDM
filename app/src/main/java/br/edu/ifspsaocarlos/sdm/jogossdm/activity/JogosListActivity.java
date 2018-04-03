package br.edu.ifspsaocarlos.sdm.jogossdm.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.edu.ifspsaocarlos.sdm.jogossdm.R;
import br.edu.ifspsaocarlos.sdm.jogossdm.adapter.JogosListFragment;
import br.edu.ifspsaocarlos.sdm.jogossdm.model.Jogos;

public class JogosListActivity extends AppCompatActivity implements JogosListFragment.ClickJogos {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jogos);
    }

    //_________________Interface ClickInJogo
    @Override
    public void clickInJogo(Jogos jogos) {
        //_________________Criação do objeto Intent (da Intent JogosListActivity para JogosListActivity
        // - intent)
        Intent intent = new Intent(this, JogosDetailActivity.class);
        //_________________Passagem de parâmetro (putExtra)
        intent.putExtra(JogosDetailActivity.EXTRA_JOGOS, jogos);
        Bundle animation = ActivityOptionsCompat.makeCustomAnimation(
                JogosListActivity.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
        ActivityCompat.startActivity(JogosListActivity.this, intent, animation);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
