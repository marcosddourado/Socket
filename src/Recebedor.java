import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {

	private InputStream servidor;
	private static final int RESPOSTA = 1;
	private static final int STAUS_RESPOSTA = 0;

	public Recebedor(InputStream servidor) {
		this.servidor = servidor;
	}

	public void run() {
		// recebe msgs do servidor e imprime na tela
		Scanner inputServidor = new Scanner(this.servidor);
		while (inputServidor.hasNextLine()) {
//			System.out.println(inputServidor.nextLine());

			String[] resposta = inputServidor.nextLine().split(" ");
			imprimirReposta(resposta);
		}
	}

	private static void imprimirReposta(String[] resposta) {
		if (resposta[STAUS_RESPOSTA].equals("0")) {
			System.out.println("Resposta: " + resposta[RESPOSTA]);
		} else {
			System.out.println("ERRO!");
		}
	}
}