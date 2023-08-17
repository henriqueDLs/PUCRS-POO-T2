import java.io.*;
import java.util.ArrayList;

public class Conglomerado {

	private ArrayList<Usina> usinas = new ArrayList<>();

	public boolean cadastraUsina(Usina usina) {
		return usinas.add(usina);

	}

	public Usina pesquisaUsina(String nome) {
		for(Usina n : usinas){
			if(n.getNome().equals(nome)){
				return n;
			}
		}
		return null;
	}

	public ArrayList<Usina> listaTodasUsinas() {
		ArrayList<Usina> todasUsinas = new ArrayList<>();
		if(usinas.isEmpty()){return null;}
		else {
			return (ArrayList<Usina>)usinas.clone();
		}
	}

	public double consultaPreco(String nome) {
		for(Usina u: usinas){
			if(u.getNome().equals(nome)){
				return u.calculaPrecoMWh();
			}
		}
		return -1;
	}

	public boolean salvaDadosArquivo(String nomeArquivo) {
		String linha = "";
		try {
			FileWriter fw = new FileWriter(nomeArquivo);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Usina u : usinas) {
				linha = u.geraResumo();
				bw.write(linha);
				bw.newLine();
			}
			bw.close();
		}
		catch (IOException e){
			System.err.println("Erro: " + e);
			return false;
		}
		return true;
	}

}
