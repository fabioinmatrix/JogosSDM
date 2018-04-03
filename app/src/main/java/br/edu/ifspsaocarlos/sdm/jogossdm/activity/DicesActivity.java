package br.edu.ifspsaocarlos.sdm.jogossdm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Random;

import br.edu.ifspsaocarlos.sdm.jogossdm.R;

public class DicesActivity extends AppCompatActivity implements View.OnClickListener {

    //_________________Componente para escolha randômica de números (faces dos dados)
    public static final Random RANDOM = new Random();
    private ImageButton rollDices;
    private ImageView imageView1, imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dices);

        //_________________Obtendo a referência de componentes declarados no arquivo de layout
        rollDices = (ImageButton) findViewById(R.id.rollDices);
        rollDices.setOnClickListener(this);

        imageView1 = (ImageView) findViewById(R.id.iv_dice_four);
        imageView2 = (ImageView) findViewById(R.id.iv_dice_two);
    }

    @Override
    public void onClick(View view) {

        //_________________Atribuição de números randômicos
        int value1 = randomDiceValue();
        int value2 = randomDiceValue();

        //_________________Concatenação do número randômico gerado com o nome da imagem (face)
        // do dado contido no diretório: drawable - pacote: br.edu.ifspsaocarlos.sdm.jogossdm
        int res1 = getResources().getIdentifier("dice_" + value1, "drawable",
                "br.edu.ifspsaocarlos.sdm.jogossdm");
        int res2 = getResources().getIdentifier("dice_" + value2, "drawable",
                "br.edu.ifspsaocarlos.sdm.jogossdm");

        //_________________Exibição das faces dos dados nos TextView
        imageView1.setImageResource(res1);
        imageView2.setImageResource(res2);
    }

    //_________________Método para limitação da escolha de números randômicos (de 1 à 6)
    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }
}
