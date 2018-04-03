package br.edu.ifspsaocarlos.sdm.jogossdm.activity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;

import br.edu.ifspsaocarlos.sdm.jogossdm.R;

public class ChronometerActivity extends AppCompatActivity {

    private ImageButton btnStart;
    private ImageButton btnPause;
    private ImageButton btnReset;
    //_________________Componente Chronometer
    private Chronometer chronometer;
    //_________________Componentes para auxílio de pausas
    private long pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);

        //_________________Obtendo a referência de componentes declarados no arquivo de layout...
        btnStart = (ImageButton) findViewById(R.id.btn_start);
        btnPause = (ImageButton) findViewById(R.id.btn_pause);
        btnReset = (ImageButton) findViewById(R.id.btn_reset);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        //_________________Se o usuário apertar o botão play...
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //_________________Se pause for diferente de 0, então o cronômetro re-inicializará a partir da última pausa
                if (pause != 0) {
                    chronometer.setBase(chronometer.getBase() + SystemClock.elapsedRealtime() - pause);
                }
                //_________________Se não, o cronômetro inicializará a partir de zero
                else {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                }
                //_________________Inicialização do cronômetro
                chronometer.start();
                //_________________Desabilitação do botão play para clique
                btnStart.setClickable(false);
                //_________________Habilitação do botão pause para clique
                btnPause.setClickable(true);
                //_________________Habilitação do botão reset para clique
                btnReset.setClickable(true);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //_________________Gravação do último instante de tempo do cronômetro
                pause = SystemClock.elapsedRealtime();
                //_________________"Pausa" o SystemClock
                chronometer.stop();
                //_________________Desabilitação do botão pause para clique
                btnPause.setClickable(false);
                //_________________Habilitação do botão play para clique
                btnStart.setClickable(true);
                //_________________Habilitação do botão reset para clique
                btnReset.setClickable(true);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //_________________"Pausa" o SystemClock
                chronometer.stop();
                //_________________Reinicia a contagem do cronômetro
                chronometer.setBase(SystemClock.elapsedRealtime());
                //_________________Atribuição de valor à variável pause (última pausa)
                pause = 0;
                //_________________Habilitação do botão play para clique
                btnStart.setClickable(true);
                //_________________Desabilitação do botão pause para clique
                btnPause.setClickable(false);
                //_________________Desabilitação do botão reset para clique
                btnReset.setClickable(false);
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
