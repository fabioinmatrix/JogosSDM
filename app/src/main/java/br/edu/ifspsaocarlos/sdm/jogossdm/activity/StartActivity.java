package br.edu.ifspsaocarlos.sdm.jogossdm.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ifspsaocarlos.sdm.jogossdm.R;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //_________________Obtendo a referência de componentes declarados no arquivo de layout...
        buttonStart = (Button) findViewById(R.id.imgbtn_start);
        buttonStart.setOnClickListener(this);
    }

    //_________________Obtendo evento de clique através do disparo de componentes...
    @Override
    public void onClick(View view) {
        //_________________Exemplo de utilização do método onClick com switch
        //_________________Obtenção do Id da view por intermédio de getId()
        switch (view.getId()) {
            case R.id.imgbtn_start:
                //_________________Criação do objeto Intent
                Intent intentThisToJogosListActivity = new Intent(this, AppsActivity.class);
                Bundle animation = ActivityOptionsCompat.makeCustomAnimation(
                        StartActivity.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
                ActivityCompat.startActivity(StartActivity.this, intentThisToJogosListActivity, animation);
                break;
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
