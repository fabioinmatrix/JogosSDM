package br.edu.ifspsaocarlos.sdm.jogossdm.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.jogossdm.model.Jogos;

/**
 * Created by fabio on 20/11/2017.
 */

//_________________Classe JogosListFragment herda de ListFragment
public class JogosListFragment extends ListFragment {

    //_________________Componentes
    List<Jogos> mJogos;
    ArrayAdapter<Jogos> mAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //_________________Armazenamento da lista de objetos Jogos
        mJogos = loadJogos();
        //_________________Adapter para exibição da lista na tela
        mAdapter = new ArrayAdapter<Jogos>(getActivity(), android.R.layout.simple_list_item_1, mJogos);
        //_________________Inicialização da lista de objetos Jogos
        setListAdapter(mAdapter);
    }

    private List<Jogos> loadJogos() {
        List<Jogos> jogos = new ArrayList<>();
        //_________________Adição e criação de objetos Jogos
        jogos.add(new Jogos("Jogo de Xadrez", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Chess Clock.\nHabilidades: " +
                "Lógica, estratégia e tática.\nComplexidade: ", 5f));
        jogos.add(new Jogos("Jogo de Damas", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Não aplicável.\nHabilidades: " +
                "Estratégia e gestão.\nComplexidade: ", 2.5f));
        jogos.add(new Jogos("Carcassonne", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Não aplicável.\nHabilidades: " +
                "Estratégia e gestão.\nComplexidade: ", 1f));
        jogos.add(new Jogos("Gamão", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Não aplicável.\nHabilidades: " +
                "Contagem e estratégia.\nComplexidade: ", 4f));
        jogos.add(new Jogos("Descobridores de Catan", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Não aplicável.\nHabilidades: " +
                "Estratégia e gestão.\nComplexidade: ", 2.5f));
        jogos.add(new Jogos("Trilha", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Não aplicável.\nHabilidades: " +
                "Estratégia.\nComplexidade: ", 2f));
        jogos.add(new Jogos("Ludo", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Não aplicável.\nHabilidades: Estratégia.\nComplexidade: ", 3.5f));
        jogos.add(new Jogos("Monopoly", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Não aplicável.\nHabilidades: " +
                "Estratégia e negociação.\nComplexidade: ", 2.5f));
        jogos.add(new Jogos("Jogo da Vida", "\nAcessório(s): Dado(s).\n" +
                "Cronômetro: Não aplicável.\nHabilidades: " +
                "Sorte e Gestão simples.\nComplexidade: ", 1.0f));
        return jogos;
    }

    //_________________Método para ser envocado quando o usuário clicar em um item da lista
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Activity activity = getActivity();
        //_________________Se a activity implementa a interface ClickJogos
        if (activity instanceof ClickJogos) {
            //_________________Obtenção do objeto Jogos referente à posição na lista que foi clicada
            // com a chamada ao método getItemAtPosition()
            Jogos jogos = (Jogos) l.getItemAtPosition(position);
            ClickJogos listener = (ClickJogos) activity;
            listener.clickInJogo(jogos);
        }
    }

    //_________________Definindo uma interface para notificação de click do usuário
    public interface ClickJogos {
        void clickInJogo(Jogos jogos);
    }
}
