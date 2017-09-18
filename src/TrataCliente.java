import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TrataCliente implements Runnable {

	private InputStream cliente;
	private Servidor servidor;
	private static final int NUMERO2 = 2;
	private static final int NUMERO1 = 1;
	private static final int OPERACAO = 0;

	public TrataCliente(InputStream cliente, Servidor servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
	}

	public void run() {
		// quando chegar uma msg, distribui pra todos
		Scanner inputCliente = new Scanner(this.cliente);
		while (inputCliente.hasNextLine()) {
			// servidor.distribuiMensagem(inputCliente.nextLine());

			String[] parts = inputCliente.nextLine().split(" ");
			System.out.println("op " + parts[OPERACAO]);
			int num1 = Integer.parseInt(parts[NUMERO1]);
			int num2 = Integer.parseInt(parts[NUMERO2]);

			processarRequisicao(parts, num1, num2);

		}
		inputCliente.close();
	}

	private void processarRequisicao(String[] parts, int num1, int num2) {
		if (parts[OPERACAO].equals("add")) {
			int soma = num1 + num2;
			servidor.distribuiMensagem("0 " + soma);
		} else if (parts[OPERACAO].equals("sub")) {
			int sub = num1 - num2;
			servidor.distribuiMensagem("0 " + sub);
		} else if (parts[OPERACAO].equals("mul")) {
			int mul = num1 * num2;
			servidor.distribuiMensagem("0 " + mul);
		} else if (parts[OPERACAO].equals("div") && num2 != 0) {
			int div = num1 / num2;
			servidor.distribuiMensagem("0 " + div);
		} else {
			servidor.distribuiMensagem("1");
		}
	}
}
