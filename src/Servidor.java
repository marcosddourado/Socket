import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class Servidor {
	private static final int OPERACAO = 0;

	public static void main(String args[]) throws IOException {

		ServerSocket servidor = new ServerSocket(12345);
		System.out.println("Porta 12345 aberta!");

		Socket cliente = servidor.accept();
		System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());

		Scanner inputCliente = new Scanner(cliente.getInputStream());
		PrintStream saida = new PrintStream(cliente.getOutputStream());

		while (inputCliente.hasNextLine()) {
			String[] parts = inputCliente.nextLine().split(" ");
			System.out.println("op " + parts[OPERACAO]);

			if (parts[OPERACAO].equals("add")) {
				saida.println("0 " + Integer.parseInt(parts[1]) + Integer.parseInt(parts[2]));
				System.out.println("0 " + Integer.parseInt(parts[1]) + Integer.parseInt(parts[2]));
			}

		}

		inputCliente.close();
		servidor.close();
		cliente.close();
	}
}