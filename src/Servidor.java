import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

public class Servidor {
	private static final int NUMERO2 = 2;
	private static final int NUMERO1 = 1;
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
			int num1 = Integer.parseInt(parts[NUMERO1]);
			int num2 = Integer.parseInt(parts[NUMERO2]);

			analisarRequisicao(saida, parts, num1, num2);
		}

		inputCliente.close();
		servidor.close();
		cliente.close();
	}

	private static void analisarRequisicao(PrintStream saida, String[] parts, int num1, int num2) {
		if (parts[OPERACAO].equals("add")) {
			int soma = num1 + num2;
			saida.println("0 " + soma);
		} else if (parts[OPERACAO].equals("sub")) {
			int sub = num1 - num2;
			saida.println("0 " + sub);
		} else if (parts[OPERACAO].equals("mul")) {
			int mul = num1 * num2;
			saida.println("0 " + mul);
		} else if (parts[OPERACAO].equals("div") && num2 != 0) {
			int div = num1/num2;
			saida.println("0 " + div);
		} else {
			saida.println("1");
		}
	}
}