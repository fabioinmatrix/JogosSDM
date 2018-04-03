package br.edu.ifspsaocarlos.sdm.jogossdm.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import br.edu.ifspsaocarlos.sdm.jogossdm.R;
import br.edu.ifspsaocarlos.sdm.jogossdm.model.Jogos;

/**
 * Created by fabio on 20/11/2017.
 */

//_________________Classe JogosDetailFragment herda de Fragment
public class JogosDetailFragment extends Fragment {
    public static final String TAG_DETAIL = "tagDetail";
    private static final String EXTRA_JOGOS = "jogos";

    TextView mTextName;
    TextView mTextDescription;
    RatingBar mRatingStars;

    Jogos mJogos;

    //_________________Método estático para criação de nova instância recebendo como parâmetro um
    // objeto Jogos
    public static JogosDetailFragment newInstance(Jogos jogos) {
        //_________________Armazenamento de parâmetros internos em um objeto Bundle
        Bundle parameters = new Bundle();
        parameters.putSerializable(EXTRA_JOGOS, jogos);
        //_________________Armazenamento de parâmetros internos em um objeto Fragment
        JogosDetailFragment fragment = new JogosDetailFragment();
        //_________________Instância do Fragment e atribuição dos parâmetros no método setArguments()
        fragment.setArguments(parameters);
        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        mJogos = (Jogos) getArguments().getSerializable(EXTRA_JOGOS);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detail_jogos, container, false);

        //_________________Obtendo a referência de componentes declarados no arquivo de layout
        mTextName = (TextView) layout.findViewById(R.id.txtName);
        mTextDescription = (TextView) layout.findViewById(R.id.txtDescription);
        mRatingStars = (RatingBar) layout.findViewById(R.id.rtbStars);

        //_________________Se mJogos for diferente de nulo...
        if (mJogos != null) {
            mTextName.setText(mJogos.name);
            mTextDescription.setText(mJogos.description);
            mRatingStars.setRating(mJogos.stars);
        }
        return layout;
    }
}
