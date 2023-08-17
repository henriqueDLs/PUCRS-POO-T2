import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ACMEEnergy {

	private Conglomerado conglomerado = new Conglomerado();
	private Scanner entrada = new Scanner(System.in);

	public void inicializa() {
		UsinaEnNRen u1 = new UsinaEnNRen("Lapa", 213.2, 2.00, "carvao",5);
		conglomerado.cadastraUsina(u1);
		UsinaEnRen u2 = new UsinaEnRen("Ola", 182.1, 3.00, "solar");
		conglomerado.cadastraUsina(u2);
		u1 = new UsinaEnNRen("Poca", 213.2, 2.00, "carvao",5);
		u2 = new UsinaEnRen("Cola", 182.1, 3.00, "solar");
		conglomerado.cadastraUsina(u1);
		conglomerado.cadastraUsina(u2);
		u1 = new UsinaEnNRen("Moca", 512.7, 4.00, "nuclear",20);
		u2 = new UsinaEnRen("Meta", 321.3, 2.00, "hidrica");
		conglomerado.cadastraUsina(u1);
		conglomerado.cadastraUsina(u2);
		u1 = new UsinaEnNRen("Fren", 121.2, 10.00, "petroleo",3);
		u2 = new UsinaEnRen("Quente", 102.6, 5.00, "eolica");
		conglomerado.cadastraUsina(u1);
		conglomerado.cadastraUsina(u2);
		u1 = new UsinaEnNRen("Faca", 213.2, 2.00, "nuclear",15);
		u2 = new UsinaEnRen("Lenta", 182.1, 3.00, "hidrica");
		conglomerado.cadastraUsina(u1);
		conglomerado.cadastraUsina(u2);
	}

	public void executa() {
		int opcao=0;
		do {
			menu();
			try{
				opcao = entrada.nextInt();
			}
			catch (InputMismatchException e){
				System.out.println("Valor invalido, diferente tipo de dado esperado!");
				return;
			}
			entrada.nextLine();
			switch (opcao) {
				case 0:
					break;
				case 1:
					cadastrarUsina();
					break;
				case 2:
					pesquisarUsina();
					break;
				case 3:
					listarUsinas();
					break;
				case 4:
					consultarPreco();
					break;
				case 5:
					salvarUsinarArquivo();
					break;
				default:
					System.out.println("Opcao invalida.");
					break;
			}
		}
		while (opcao != 0);
	}

	private void menu() {
		System.out.println("=======================");
		System.out.println("Opcoes:");
		System.out.println("[0] Sair do sistema");
		System.out.println("[1] Cadastrar nova usina");
		System.out.println("[2] Pesquisar uma usina");
		System.out.println("[3] Listar todas usinas");
		System.out.println("[4] Consultar o preco do MWh");
		System.out.println("[5] Salvar usinas em arquivo");
	}

	private void cadastrarUsina() {
		System.out.println("Deseja cadastrar uma usina de energia renovavel? \n[1] Sim\n[2] Nao");
		double producao=0,custo=0;
		int opcao=0,durabilidade=0;
		String fonte="",nome="";
		try{
			opcao = entrada.nextInt();
		}
		catch(InputMismatchException e){
			System.out.println("Valor invalido, diferente tipo de dado esperado!");
		}
		if(opcao!=1&&opcao!=2){System.out.println("Opcao invalida!"); return;}
		entrada.nextLine();
		System.out.println("Digite o nome da usina:");
		try{
			nome = entrada.nextLine();
		}
		catch(InputMismatchException e){
			System.out.println("Valor invalido, diferente tipo de dado esperado!");
		}
		System.out.println("Digite a producao da usina:");
		try{
			producao = entrada.nextDouble();
		}
		catch (InputMismatchException e){
			System.out.println("Valor invalido, diferente tipo de dado esperado!");
		}
		entrada.nextLine();
		System.out.println("Digite o custo de producao da usina:");
		try{
			custo = entrada.nextDouble();
		}
		catch (InputMismatchException e){
			System.out.println("Valor invalido, diferente tipo de dado esperado!");
		}
		entrada.nextLine();
		if (opcao == 1) {
			System.out.println("Digite a fonte de energia da usina:");
			try{
				fonte = entrada.nextLine();
			}
			catch (InputMismatchException e){
				System.out.println("Valor invalido, diferente tipo de dado esperado!");
			}
			UsinaEnRen u = new UsinaEnRen(nome, producao, custo, fonte);
			conglomerado.cadastraUsina(u);
		} else if (opcao == 2) {
			System.out.println("Digite o combustivel utilizado pela usina:");
			try{
				fonte = entrada.nextLine();
			}
			catch (InputMismatchException e){
				System.out.println("Valor invalido, diferente tipo de dado esperado!");
			}
			System.out.println("Digite a durabilidade do combustivel utilizado pela usina:");
			try{
				durabilidade = entrada.nextInt();
			}
			catch (InputMismatchException e){
				System.out.println("Valor invalido, diferente tipo de dado esperado!");
			}
			entrada.nextLine();
			UsinaEnNRen u = new UsinaEnNRen(nome, producao, custo, fonte,durabilidade);
			conglomerado.cadastraUsina(u);
		}
	}

	private void pesquisarUsina() {
		System.out.println("Digite o nome da usina da qual deseja pesquisar:");
		String nome ="";
		try{
			nome = entrada.nextLine();
		}
		catch (InputMismatchException e){
			System.out.println("Valor invalido, diferente tipo de dado esperado!");
		}
		Usina u = conglomerado.pesquisaUsina(nome);
		if (u == null) {
			System.out.println("Nenhuma usina localizada com este nome!");
		} else {
			System.out.println("Dados da usina:");
			System.out.println("=========================");
			System.out.println("Nome da usina: " + u.getNome());
			System.out.println("Producao da usina: " + u.getProducaoMWh());
			System.out.println("Custo de producao da usina: " + u.getCustoMWh());
			if (u instanceof UsinaEnRen) {
				System.out.println("Fonte de energia utilizada pela usina: " + ((UsinaEnRen) u).getFonteEnergia());
			} else if (u instanceof UsinaEnNRen) {
				System.out.println("Combustivel utilizado pela usina: " + ((UsinaEnNRen) u).getCombustivel());
				System.out.println("Durabilidade do combustivel utilizado pela usina: " + ((UsinaEnNRen) u).getDurabilidadeCombustivel());
			}
			System.out.println("Preco do MWh: " + u.calculaPrecoMWh());
		}

	}

	private void listarUsinas() {
		ArrayList<Usina> todasUsinas = conglomerado.listaTodasUsinas();
		if (todasUsinas.isEmpty()) {
			System.out.println("Nenhuma usina cadastrada");
		} else {
			System.out.println("Dados das usinas cadastradas no sistema:");
			System.out.println("================================================");
			for (Usina u : todasUsinas) {
				System.out.println("Nome da usina: " + u.getNome());
				System.out.println("Producao da usina: " + u.getProducaoMWh());
				System.out.println("Custo de producao da usina: " + u.getCustoMWh());
				if (u instanceof UsinaEnRen) {
					System.out.println("Fonte de energia utilizada pela usina: " + ((UsinaEnRen) u).getFonteEnergia());
					System.out.println("Preco do MWh: " + u.calculaPrecoMWh() + "\n");
				} else if (u instanceof UsinaEnNRen) {
					System.out.println("Combustivel utilizado pela usina: " + ((UsinaEnNRen) u).getCombustivel());
					System.out.println("Durabilidade do combustivel utilizado pela usina: " + ((UsinaEnNRen) u).getDurabilidadeCombustivel());
					System.out.println("Preco do MWh: " + u.calculaPrecoMWh() + "\n");
				}
			}
		}
	}

	private void consultarPreco() {
		System.out.println("Digite o nome da usina da qual deseja pesquisar:");
		String nome = "";
		try{
			nome = entrada.nextLine();
		}
		catch (InputMismatchException e){
			System.out.println("Valor invalido, diferente tipo de dado esperado!");
		}
		double preco = conglomerado.consultaPreco(nome);
		if (preco == -1) {
			System.out.println("Nenhuma usina localizada com este nome!");
		} else {
			System.out.println("Preco de MWh: " + preco);
		}
	}

	private void salvarUsinarArquivo(){
		System.out.println("Digite o nome do arquivo para salvar os dados:");
		String nomeArquivo = "";
		try{
			nomeArquivo = entrada.nextLine();
		}
		catch (InputMismatchException e){
			System.out.println("Valor invalido, diferente tipo de dado esperado!");
		}
		conglomerado.salvaDadosArquivo(nomeArquivo);
	}
}
