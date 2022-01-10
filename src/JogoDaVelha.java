import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class JogoDaVelha {
	
	static ArrayList<Integer> PosicaoJogador = new ArrayList<Integer>();
	static ArrayList<Integer> PosicaoCPU = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		char[][] tabuleiro = {{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}};
		
			ImprimeTabuleiro(tabuleiro);
			
			while(true) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Entre com o lugar que você deseja colocar a peça(1 a 9): ");
				int jogadorPos = sc.nextInt();
				while(PosicaoJogador.contains(jogadorPos) || PosicaoCPU.contains(jogadorPos)){
					System.out.println("Posição já está ocupada! Entre com outra posição");
					jogadorPos = sc.nextInt();
				}
				
				ColocaPeca(tabuleiro, jogadorPos, "jogador");
				
				Random rand = new Random();
				int cpuPos = rand.nextInt(9)+1;
				while(PosicaoJogador.contains(cpuPos) || PosicaoCPU.contains(cpuPos)){
					cpuPos = rand.nextInt(9)+1;
				}
				ColocaPeca(tabuleiro, cpuPos, "CPU");
				ImprimeTabuleiro(tabuleiro);
				String resultado = ChecaVencedor();
				System.out.println(resultado);
			}
		}
	
	

	public static void ImprimeTabuleiro(char[][] tabuleiro) {
		for(char[] fileira: tabuleiro) {
			for(char c : fileira) {
				System.out.print(c);
			}
			System.out.println();
		}
		
	}
	
	public static void ColocaPeca(char[][] tabuleiro, int pos, String usuario){
		
		char simbolo = ' ';
		
		if(usuario.equals("jogador")) {
			simbolo = 'X';
			PosicaoJogador.add(pos);
		} else if(usuario.equals("CPU")) {
			simbolo = 'O';
			PosicaoCPU.add(pos);
		}
		
		switch(pos) {
		case 1: 
			tabuleiro[0][0] = simbolo;
			break;
		case 2: 
			tabuleiro[0][2] = simbolo;
			break;
		case 3: 
			tabuleiro[0][4] = simbolo;
			break;
		case 4: 
			tabuleiro[2][0] = simbolo;
			break;
		case 5: 
			tabuleiro[2][2] = simbolo;
			break;
		case 6: 
			tabuleiro[2][4] = simbolo;
			break;
		case 7: 
			tabuleiro[4][0] = simbolo;
			break;
		case 8: 
			tabuleiro[4][2] = simbolo;
			break;
		case 9: 
			tabuleiro[4][4] = simbolo;
			break;
		default:
			break;
		
	}
}
	public static String ChecaVencedor() {
		List linhaTopo = Arrays.asList(1, 2, 3);
		List linhaMeio = Arrays.asList(4, 5, 6);
		List linhaInferior = Arrays.asList(7, 8, 9);
		List ColEsquerda = Arrays.asList(1, 4, 7);
		List ColMeio = Arrays.asList(2, 5, 8);
		List ColDireita = Arrays.asList(3, 6, 9);
		List Cruz1 = Arrays.asList(1, 5, 9);
		List Cruz2 = Arrays.asList(7, 5, 3);
		
		List<List> vitoria = new ArrayList<List>();
		vitoria.add(linhaTopo);
		vitoria.add(linhaMeio);
		vitoria.add(linhaInferior);
		vitoria.add(ColEsquerda);
		vitoria.add(ColMeio);
		vitoria.add(ColDireita);
		vitoria.add(Cruz1);
		vitoria.add(Cruz2);
		
		for(List l : vitoria) {
			if(PosicaoJogador.containsAll(l)){
				return "Parabéns você ganhou!!";
		} else if(PosicaoCPU.containsAll(l)){
				return "A máquina ganhou!";
		} else if(PosicaoJogador.size() + PosicaoCPU.size() == 9) {
				return "Empate!";
		}
	}
		return "";
}
}