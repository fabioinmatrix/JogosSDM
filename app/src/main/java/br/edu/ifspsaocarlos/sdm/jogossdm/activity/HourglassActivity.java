package br.edu.ifspsaocarlos.sdm.jogossdm.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.jogossdm.R;

public class HourglassActivity extends AppCompatActivity {

    private EditText etTime;
    private String etTimeToString;
    private long etTimeToLong;
    private TextView tvTime;
    private ImageView myHourglass;
    private ImageButton btnStart;
    private ImageButton btnStop;
    //_________________Componentes de som
    private ImageButton btnSound;
    private boolean soundOnOff;
    private String sound = "horn";
    //_________________Componente MediaPlayer para execução de sons de alerta
    private MediaPlayer mediaPlayer;
    //_________________Componente CountDownTimer para contagem regressiva
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourglass);

        //_________________Obtendo a referência de componentes declarados no arquivo de layout
        myHourglass = (ImageView) findViewById(R.id.iv_hourglass);
        myHourglass.animate().setStartDelay(3000).rotation(360f).setDuration(2000).start();

        btnStart = (ImageButton) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(btnClickListener);

        btnStop = (ImageButton) findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(btnClickListener);

        btnSound = (ImageButton) findViewById(R.id.btn_sound);
        btnSound.setOnClickListener(btnClickListener);

        soundOnOff = false;

        tvTime = (TextView) findViewById(R.id.tv_time);
        etTime = (EditText) findViewById(R.id.et_time);
    }

    private View.OnClickListener btnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_start:
                    //_________________Inicialização do método startCountDownTimer
                    startCountDownTimer();
                    break;
                case R.id.btn_stop:
                    //_________________Inicialização do método stopCountDownTimer
                    stopCountDownTimer();
                    break;
                case R.id.btn_sound:
                    soundChoice();
            }
        }
    };

    private void startCountDownTimer() {
        //_________________Se tempo for <= 0, exibe Toast
        if (etTime.getText().length() <= 0) {
            Toast timeUp = Toast.makeText(getApplicationContext(),
                    "Digite o tempo para iniciar...", Toast.LENGTH_LONG);
            timeUp.show();
        }
        //_________________Se não
        else {
            //_________________Desabilitação do btnStart para cliques
            btnStart.setEnabled(false);
            //_________________Desabilita o EditText para inserção de valores
            etTime.setEnabled(false);
            //_________________Converte e atribui o valor digitado no EditText à variável etTimeToString
            etTimeToString = etTime.getText().toString();
            //_________________Conversão do tipo String para tipo Long
            etTimeToLong = Long.parseLong(etTimeToString);
            //_________________Exibição da contagem regressiva no TextView tvTime
            tvTime.setText(etTimeToString);
            //_________________Exibição da mensagem "Ampulheta cronometrando..." no EditText etTime
            etTime.setText("Ampulheta cronometrando...");
            //_________________Instânciação do componente CountDownTimer (conversão de segundos para milisegundos
            countDownTimer = new CountDownTimer(etTimeToLong * 1000, 1000) {

                @Override
                public void onTick(long l) {
                    //_________________Formatação para exibição de milisegundos em horas/minutos/segundos
                    tvTime.setText(String.format("%02d:%02d:%02d", l / 3600000,
                            (l / 60000) % 60, (l / 1000) % 60));
                }

                @Override
                public void onFinish() {
                    //_________________Exibição do texto "Tempo esgotado" no TextView tvTime
                    tvTime.setText("Tempo esgotado!");
                    //_________________Habilitação do componente de vibração
                    Vibrator vibRate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    //_________________Vibração definida para duração de 2 segundos
                    vibRate.vibrate(2000);
                    //_________________Limpeza do EditText para exibição do hint (xml)
                    etTime.setText("");
                    //_________________Habilitação do EditText para inserção de valores
                    etTime.setEnabled(true);
                    //_________________Se soundOnOff (true)
                    if (soundOnOff) {
                        //_________________Execução do método playSound() para aviso de alerta
                        playSound();
                    }
                    //_________________Implementação de Handler e Runnable para delay na limpeza
                    // dos componentes etTime e tvTime
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //_________________Limpeza do EditText para exibição do hint (xml)
                            // e TextView
                            tvTime.setText("");
                        }
                    }, 4000);
                    //_________________Habilitação do btnStart para cliques
                    btnStart.setEnabled(true);
                }
            };
            //_________________Inicialização da contagem regressiva
            countDownTimer.start();
        }
    }

    private void stopCountDownTimer() {
        //_________________Habilitação do btnStart para cliques
        btnStart.setEnabled(true);
        //_________________Se a contagem for igual à zero e o etTime for maior que zero
        if (countDownTimer != null && etTime.getText().length() > 0) {
            //_________________Cancelamento da contagem
            countDownTimer.cancel();
            countDownTimer = null;
            //_________________Habilitação do EditText para inserção de valores
            etTime.setEnabled(true);
            //_________________Limpeza do EditText para exibição do hint (xml)
            etTime.setText("");
            //_________________Limpeza do TextView
            tvTime.setText("");
            //_________________Exibição do texto "Cancelado!" no TextView tvTime
            tvTime.setText("Cancelado!");
            //_________________Implementação de Handler e Runnable para delay na limpeza
            // do componente tvTime após o cancelamento
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //_________________Limpeza do TextView
                    tvTime.setText("");
                }
            }, 4000);
        }
    }

    //_________________Método para mudança do ImageButton btnSound
    public void soundChoice() {
        if (soundOnOff) {
            //_________________Substituição do valor booleano
            soundOnOff = false;
            //_________________Substituição da imagem (de sound_on para sound_off
            btnSound.setImageResource(R.drawable.sound_off);
        } else {
            //_________________Substituição do valor booleano
            soundOnOff = true;
            //_________________Substituição da imagem (de sound_off para sound_on
            btnSound.setImageResource(R.drawable.sound_on);
        }
    }

    //_________________Método para emissão de sons de alertas
    public void playSound() {
        //_________________Armazenamento da posição do som contido no diretório: raw - pacote:
        // br.edu.ifspsaocarlos.sdm.jogossdm
        int soundPlay = getResources().getIdentifier(sound, "raw", "br.edu.ifspsaocarlos.sdm.jogossdm");
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
