import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.PrintStream;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// dispara cliente
		new Cliente("127.0.0.1", 12345).executa();
	}

	private String host;
	private int porta;

	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException {
		Socket cliente = new Socket(this.host, this.porta);
		System.out.println("O cliente se conectou ao servidor!");

		// thread para receber mensagens do servidor
		Recebedor r = new Recebedor(cliente.getInputStream());
		new Thread(r).start();

		// lê msgs do teclado e manda pro servidor
		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		while (teclado.hasNextLine()) {
			saida.println(teclado.nextLine());
		}

		saida.close();
		teclado.close();
		cliente.close();
	}
}

// public class Cliente {
// private static final int RESPOSTA = 1;
// private static final int STAUS_RESPOSTA = 0;
//
// public static void main(String[] args) throws UnknownHostException,
// IOException {
// Socket cliente = new Socket("127.0.0.1", 12345);
// System.out.println("O cliente se conectou ao servidor!");
// System.out.println("Faca uma requisicao ou escreva SAIR");
//
// Scanner teclado = new Scanner(System.in);
// PrintStream saida = new PrintStream(cliente.getOutputStream());
// Scanner inputServidor = new Scanner(cliente.getInputStream());
//
// while (true) {
// String entrada = teclado.nextLine();
// if(!entrada.equalsIgnoreCase("SAIR")) {
// saida.println(entrada);
// } else {
// break;
// }
//
// String[] resposta = inputServidor.nextLine().split(" ");
// imprimirReposta(resposta);
// }
//
// saida.close();
// teclado.close();
// cliente.close();
// }
//
// private static void imprimirReposta(String[] resposta) {
// if(resposta[STAUS_RESPOSTA].equals("0")) {
// System.out.println("Resposta: " + resposta[RESPOSTA]);
// } else {
// System.out.println("ERRO!");
// }
// }
// }