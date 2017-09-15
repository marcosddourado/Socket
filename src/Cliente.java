import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.PrintStream;

public class Cliente {
	private static final int RESPOSTA = 1;
	private static final int STAUS_RESPOSTA = 0;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cliente = new Socket("127.0.0.1", 12345);
		System.out.println("O cliente se conectou ao servidor!");
		System.out.println("Faca uma requisicao ou escreva SAIR");

		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		Scanner inputServidor = new Scanner(cliente.getInputStream());

		while (true) {
			String entrada = teclado.nextLine();
			if(!entrada.equalsIgnoreCase("SAIR")) {
				saida.println(entrada);
			} else {
				break;
			}
			
			String[] resposta = inputServidor.nextLine().split(" ");
			imprimirReposta(resposta);
		}

		saida.close();
		teclado.close();
		cliente.close();
	}

	private static void imprimirReposta(String[] resposta) {
		if(resposta[STAUS_RESPOSTA].equals("0")) {
			System.out.println("Resposta: " + resposta[RESPOSTA]);
		} else {
			System.out.println("ERRO!");	
		}
	}
}