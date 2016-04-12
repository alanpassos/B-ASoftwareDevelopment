import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class RespostaServidor extends Thread {
	Socket socketRecebe;

	public RespostaServidor(Socket socketRemetente) {

		this.socketRecebe = socketRemetente;
	}

	private void aguardandoPergunta() {

		BufferedReader bfMensagemRemetente;

		try {
			bfMensagemRemetente = new BufferedReader(new InputStreamReader(socketRecebe.getInputStream()));
			String mensagem = bfMensagemRemetente.readLine();
			System.out.println("Rebebendo mensagem do remetente: " + mensagem + " Porta: "+ socketRecebe.getPort());

			BufferedWriter printRespostaAoRemetente = new BufferedWriter(
					new OutputStreamWriter(socketRecebe.getOutputStream()));
			printRespostaAoRemetente.write("IAA");
			printRespostaAoRemetente.close();

			// System.out.println("Enviando mensagem ao processo");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		aguardandoPergunta();

	}

}
