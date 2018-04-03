package br.edu.ifspsaocarlos.sdm.jogossdm.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import br.edu.ifspsaocarlos.sdm.jogossdm.R;

public class AppsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imageButtonDices;
    private ImageButton imageButtonHourglass;
    private ImageButton imageButtonChronometer;
    private ImageButton imageButtonChessClock;
    private ImageButton imageButtonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        //_________________Obtendo a referência de componentes declarados no arquivo de layout
        imageButtonDices = (ImageButton) findViewById(R.id.imbtn_dices);
        imageButtonDices.setOnClickListener(this);

        imageButtonHourglass = (ImageButton) findViewById(R.id.imbtn_hourglass);
        imageButtonHourglass.setOnClickListener(this);

        imageButtonChronometer = (ImageButton) findViewById(R.id.imbtn_chronometer);
        imageButtonChronometer.setOnClickListener(this);

        imageButtonChessClock = (ImageButton) findViewById(R.id.imbtn_chess_clock);
        imageButtonChessClock.setOnClickListener(this);

        imageButtonInfo = (ImageButton) findViewById(R.id.imbtn_info);
        imageButtonInfo.setOnClickListener(this);
    }

    //_________________Obtendo evento de clique através do disparo de componentes
    @Override
    public void onClick(View view) {
        //_________________Exemplo de utilização do método onClick com estruturas aninhadas if
        if (view == imageButtonDices) {
            //_________________Criação do objeto Intent (da Intent AppsActivity para DicesActivity
            // - ThisToDicesActivity)
            Intent intentThisToDicesActivity = new Intent(this, DicesActivity.class);
            Bundle animation = ActivityOptionsCompat.makeCustomAnimation(
                    AppsActivity.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
            ActivityCompat.startActivity(AppsActivity.this, intentThisToDicesActivity, animation);
        }
        if (view == imageButtonHourglass) {
            Intent intentThisToHourglassActivity = new Intent(this, HourglassActivity.class);
            Bundle animation = ActivityOptionsCompat.makeCustomAnimation(
                    AppsActivity.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
            ActivityCompat.startActivity(AppsActivity.this, intentThisToHourglassActivity, animation);
        }
        if (view == imageButtonChronometer) {
            Intent intentThisToChronometerActivity = new Intent(this, ChronometerActivity.class);
            Bundle animation = ActivityOptionsCompat.makeCustomAnimation(
                    AppsActivity.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
            ActivityCompat.startActivity(AppsActivity.this, intentThisToChronometerActivity, animation);
        }
        if (view == imageButtonChessClock) {
            Intent intentThisToChessClockActivity = new Intent(this, ChessClockActivity.class);
            Bundle animation = ActivityOptionsCompat.makeCustomAnimation(
                    AppsActivity.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
            ActivityCompat.startActivity(AppsActivity.this, intentThisToChessClockActivity, animation);
        }
        if (view == imageButtonInfo) {
            Intent intentThisToJogosListActivity = new Intent(this, JogosListActivity.class);
            Bundle animation = ActivityOptionsCompat.makeCustomAnimation(
                    AppsActivity.this, R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
            ActivityCompat.startActivity(AppsActivity.this, intentThisToJogosListActivity, animation);
        }
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
