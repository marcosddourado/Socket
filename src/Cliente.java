import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.io.PrintStream;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cliente = new Socket("127.0.0.1", 12345);
		System.out.println("O cliente se conectou ao servidor!");

		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		Scanner inputServidor = new Scanner(cliente.getInputStream());

		while (true) {
			saida.println(teclado.nextLine());
			System.out.println("server: " + inputServidor.nextLine());	
		}

//		saida.close();
//		teclado.close();
//		cliente.close();
	}
}