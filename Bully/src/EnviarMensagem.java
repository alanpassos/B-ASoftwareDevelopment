import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EnviarMensagem extends Thread {
	Socket socketEnviar;
	int porta;

	public EnviarMensagem(int porta) {
		

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

	public void enviarMensagemReceber() {

		try {

			EnviarMensagemServidor();
			ReceberMensagem();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void EnviarMensagemServidor() throws IOException {

		AbrirConexao(porta);
		/*System.out.println(socketEnviar.isClosed());
		BufferedWriter pwEnviarMensagem = new BufferedWriter(new OutputStreamWriter(socketEnviar.getOutputStream()));
		pwEnviarMensagem.write("AYA");

		pwEnviarMensagem.flush();
		*/
		 OutputStream os = socketEnviar.getOutputStream();
         OutputStreamWriter osw = new OutputStreamWriter(os);
         BufferedWriter bw = new BufferedWriter(osw);

         String mensagemEnviada = "AYA" + "\n";
         bw.write(mensagemEnviada);
         bw.flush();
		// System.out.println("Enviando mensagem ao server");
	}

	private void ReceberMensagem() {

		//AbrirConexao(porta);
		System.out.println(socketEnviar.isClosed());

		try {
			/*BufferedReader isrReceberMensagem = new BufferedReader(
					new InputStreamReader(socketEnviar.getInputStream()));

			String mensagem = isrReceberMensagem.readLine();*/
			InputStream is = socketEnviar.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String mensagem = br.readLine();
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
