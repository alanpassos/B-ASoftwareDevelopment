import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReceberMensagem extends Thread {
	Socket socketEnviar;
	int porta;

	public ReceberMensagem(int porta) {
		

		this.porta = porta;

	}

	private void AbrirConexao(int porta) {
		try {
			this.socketEnviar = new Socket("127.0.0.1", porta);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void ReceberMensagemServidor() {

		AbrirConexao(porta);
		System.out.println(socketEnviar.isClosed());

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

		ReceberMensagemServidor();

	}

}
