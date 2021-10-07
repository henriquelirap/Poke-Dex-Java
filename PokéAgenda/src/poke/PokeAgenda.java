package poke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class PokeAgenda {
  public static void main(String[] args) throws IOException {
    File file = new File("PokeAgenda.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("PokeAgenda.txt"), "UTF-8"));
    ArrayList < Pokemon > pokemons = new ArrayList < Pokemon > ();
    ArrayList < Pokemon > aux = new ArrayList < Pokemon > ();
    Scanner scn = new Scanner(System.in);
    String decisao;
    String buscador = null;
    String st;
    String preencher[];
    boolean work, full = true;
    int j = 0;

    while ((st = br.readLine()) != null) {
      preencher = st.split(","); //Preenchendo ArrayList com Pokémons existentes
      pokemons.add(new Pokemon(preencher[0].trim(), preencher[1].trim()));

    }
    
    while (full) {

      work = true;
      decisao = JOptionPane.showInputDialog("Bem vindo a sua Poké-Agenda! \n0 - Exibir Poké-Agenda \n1 - Inserir Pokémons \n2 - Buscar \n3 - Remover \nX - Encerrar/Gravar");

      while (work) {
        if (decisao.equals("2")) {
          work = false;
        }
        if (decisao.equals("1")) { //Inserindo Pokémon
          String nomeAdd = JOptionPane.showInputDialog("Digite o nome do Pokémon a ser inserido");
          String tipoAdd = JOptionPane.showInputDialog("Digite o tipo do Pokémon a ser inserido");
          decisao = JOptionPane.showInputDialog("0 - Exibir Poké-Agenda \n1 - Inserir Pokémons \n2 - Buscar \n3 - Remover \nX - Encerrar/Gravar");
          pokemons.add(new Pokemon(nomeAdd, tipoAdd));
        } else {
          break;
        } 
      }

      if (decisao.equals("2")) {
        decisao = JOptionPane.showInputDialog("1 - Buscar por nome \n2 - Buscar por tipo");
      }

      if (decisao.equals("1")) { //Buscando por nome.			
        buscador = JOptionPane.showInputDialog("Busca - Digite o nome do PokémAon: ");
        int verificador = 0;
        for (Pokemon p: pokemons) {
          if (p.getNome().toLowerCase().contains(buscador) || p.getNome().toUpperCase().contains(buscador)) {
            aux.add(new Pokemon(p.getNome(), p.getTipo()));
            verificador++;
          }
        }

        if (verificador == 0) {
          JOptionPane.showMessageDialog(null, "Pokémon não encontrado! Tente novamente!", null, JOptionPane.ERROR_MESSAGE);
        } else {

          JOptionPane.showMessageDialog(null, "Exibindo Poké-Agenda - Filtro por nome: \n" + aux.toString(), null, JOptionPane.INFORMATION_MESSAGE);
          aux.clear();

        }
      }

      if (decisao.equals("2")) { //Buscando por tipo.
        buscador = JOptionPane.showInputDialog("Busca - Digite o tipo do Pokémon: ");
        int verificador = 0;
        for (Pokemon p: pokemons) {
          if (p.getTipo().toLowerCase().contains(buscador) || p.getTipo().toUpperCase().contains(buscador)) {
            aux.add(new Pokemon(p.getNome(), p.getTipo()));
            verificador++;
          }
        }

        if (verificador == 0) {
          JOptionPane.showMessageDialog(null, "Pokémon não encontrado! Tente novamente!", null, JOptionPane.ERROR_MESSAGE);
        } else {

          JOptionPane.showMessageDialog(null, "Exibindo Poké-Agenda - Filtro por tipo: \n" + aux.toString(), null, JOptionPane.INFORMATION_MESSAGE);
          aux.clear();
        }
      }

      if (decisao.equals("3")) { //Removendo Pokémon.
        decisao = JOptionPane.showInputDialog("1 - Remover por nome \n2 - Remover por tipo");

        if (decisao.equals("1")) { //Removendo por nome.
          j = 0;
          buscador = JOptionPane.showInputDialog("Remover - Digite o nome do Pokémon: ");
          int verificador = 0;
          for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getNome().toLowerCase().contains(buscador) || pokemons.get(i).getNome().toUpperCase().contains(buscador)) {
              pokemons.remove(i);
              i = i - 1;
              j++;
              verificador++;
            }
          }

          if (verificador == 0) {
            JOptionPane.showMessageDialog(null, "Pokémon não encontrado! Tente novamente!", null, JOptionPane.ERROR_MESSAGE);
            continue;
          } else {
            JOptionPane.showMessageDialog(null, "Removido com sucesso " + j + " Pokémons!", null, JOptionPane.INFORMATION_MESSAGE);
            continue;
          }
        }

        if (decisao.equals("2")) { //Removendo por tipo.
          j = 0;
          buscador = JOptionPane.showInputDialog("Remover - Digite o tipo do Pokémon:");

          int verificador = 0;
          for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getTipo().toLowerCase().contains(buscador) || pokemons.get(i).getTipo().toUpperCase().contains(buscador)) {
              pokemons.remove(i);
              i = i - 1;
              j++;
              verificador++;
            }
          }

          if (verificador == 0) {
            JOptionPane.showMessageDialog(null, "Pokémon não encontrado! Tente novamente!", null, JOptionPane.ERROR_MESSAGE);
            continue;
          } else {
            JOptionPane.showMessageDialog(null, "Removido com sucesso " + j + " Pokémons!", null, JOptionPane.INFORMATION_MESSAGE);
            continue;
          }
        }
      }

      if (decisao.equals("0")) {

        if (pokemons.size() == 0) {
          JOptionPane.showMessageDialog(null, "Você não possui Pokémons cadastrados!", null, JOptionPane.INFORMATION_MESSAGE);
          continue;
        } else {
          for (Pokemon p: pokemons) {
            aux.add(new Pokemon(p.getNome(), p.getTipo()));
          }
          JOptionPane.showMessageDialog(null, "Exibindo Poké-Agenda - Todos Pokémons: \n" + aux.toString(), null, JOptionPane.INFORMATION_MESSAGE);
          aux.clear();
        }

      }

      if (decisao.equalsIgnoreCase("x")) {
        JOptionPane.showMessageDialog(null, "Programa encerrado!", null, JOptionPane.INFORMATION_MESSAGE);
        work = false;
        full = false;
      }

    }
    
    // Reescrevendo arquivo txt atualizado.
    
    FileWriter fw = new FileWriter(file);
    PrintWriter pw = new PrintWriter(fw);

    for (Pokemon p: pokemons) { 
      pw.println(p.getNome() + ", " + p.getTipo());
    }

    pw.close();
    br.close();
    scn.close();
  }
}