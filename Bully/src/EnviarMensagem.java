import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EnviarMensagem extends Thread {
	Socket socketEnviar;

	public EnviarMensagem(int porta) {
		try {
			this.socketEnviar = new Socket("localhost", porta);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarMensagemReceber() {

		try {

			EnviarMensagem();
			ReceberMensagem();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void EnviarMensagem() throws IOException {
		BufferedWriter  pwEnviarMensagem = new BufferedWriter ( new OutputStreamWriter( socketEnviar.getOutputStream()));
		pwEnviarMensagem.write("AYA");

		pwEnviarMensagem.flush();

		//System.out.println("Enviando mensagem ao server");
	}

	private void ReceberMensagem() {

		try {
			BufferedReader isrReceberMensagem = new BufferedReader(
					new InputStreamReader(socketEnviar.getInputStream()));

			String mensagem = isrReceberMensagem.readLine();
			System.out.println("Receberu mensagem do Server: " + mensagem);
			if (!mensagem.equals("IAA")) {
				// gerar nova eleicao ou senão receber no tempo exato

			}
			socketEnviar.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		enviarMensagemReceber();

	}

}
