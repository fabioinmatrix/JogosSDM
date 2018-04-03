package br.edu.ifspsaocarlos.sdm.jogossdm.activity;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.jogossdm.R;

public class ChessClockActivity extends AppCompatActivity {

    private TextView tvCountPlayerOne;
    private TextView tvCountPlayerTwo;
    private ImageButton btnPlayerOne;
    private ImageButton btnPlayerTwo;
    //_________________Componente Chronometer
    private Chronometer chronometerOne;
    private Chronometer chronometerTwo;
    //_________________Componentes para auxílio de pausas
    private long pausePlayerOne;
    private long pausePlayerTwo;
    //_________________Componente para contagem de movimentos
    public int countPlayerOne;
    public int countPlayerTwo;
    //_________________Componente de som
    private String sound = "shipbell";
    //_________________Componente MediaPlayer para execução de sons de alerta
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_clock);

        //_________________Obtendo a referência de componentes declarados no arquivo de layout
        tvCountPlayerOne = (TextView) findViewById(R.id.tv_count_player_one);
        tvCountPlayerTwo = (TextView) findViewById(R.id.tv_count_player_two);

        btnPlayerOne = (ImageButton) findViewById(R.id.btn_playerOne);
        btnPlayerTwo = (ImageButton) findViewById(R.id.btn_playerTwo);

        chronometerOne = (Chronometer) findViewById(R.id.chronometerPlayerOne);
        chronometerTwo = (Chronometer) findViewById(R.id.chronometerPlayerTwo);

        //_________________Inicialização do método startPlayerOne()
        startPlayerOne();

        //_________________Se o jogador 1 apertar o botão
        btnPlayerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //_________________Inicialização do cronômetro do jogador 2
                startPlayerTwo();
                //_________________Execução do método playSound para emissão de som de alerta
                playSound();
                //_________________Incremento de 1 no componente contador de movimentos
                countPlayerOne++;
                //_________________Exibição do número total de movimentos no TextView countPlayerOne
                tvCountPlayerOne.setText(String.valueOf(countPlayerOne));
            }
        });
        btnPlayerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlayerOne();
                playSound();
                countPlayerTwo++;
                tvCountPlayerTwo.setText(String.valueOf(countPlayerTwo));
            }
        });
    }

    //_________________Método startPlayerOne()
    public void startPlayerOne() {
        //_________________Se pausePlayerOne for diferente de 0, então o cronômetro re-inicializará
        // a partir da última pausa
        if (pausePlayerOne != 0) {
            chronometerOne.setBase(chronometerOne.getBase() + SystemClock.elapsedRealtime()
                    - pausePlayerOne);
        }
        //_________________Se não, o cronômetro inicializará a partir de zero
        else {
            chronometerOne.setBase(SystemClock.elapsedRealtime());
        }
        //_________________Inicialização do cronômetro
        chronometerOne.start();
        //_________________Gravação do último instante de tempo do jogador 2
        pausePlayerTwo = SystemClock.elapsedRealtime();
        //_________________Pausa do cronômetro do jogador 2
        chronometerTwo.stop();
        //_________________Desabilitação do botão do jogador 2 para clique
        btnPlayerTwo.setEnabled(false);
        //_________________Habilitação do botão do jogador 1 para clique
        btnPlayerOne.setEnabled(true);
    }

    public void startPlayerTwo() {
        if (pausePlayerTwo != 0) {
            chronometerTwo.setBase(chronometerTwo.getBase() + SystemClock.elapsedRealtime()
                    - pausePlayerTwo);
        } else {
            chronometerTwo.setBase(SystemClock.elapsedRealtime());
        }
        chronometerTwo.start();
        pausePlayerOne = SystemClock.elapsedRealtime();
        chronometerOne.stop();
        btnPlayerOne.setEnabled(false);
        btnPlayerTwo.setEnabled(true);
    }

    //_________________Método para emissão de sons de alertas
    public void playSound() {
        //_________________Armazenamento da posição do som contido no diretório: raw - pacote:
        // br.edu.ifspsaocarlos.sdm.jogossdm
        int soundPlay = getResources().getIdentifier(sound, "raw",
                "br.edu.ifspsaocarlos.sdm.jogossdm");
        //_________________Execução do método .create com parâmetros armazenados em soundPlay
        mediaPlayer = MediaPlayer.create(this, soundPlay);
        //_________________Execução (inicialização) do objeto (som)
        mediaPlayer.start();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
