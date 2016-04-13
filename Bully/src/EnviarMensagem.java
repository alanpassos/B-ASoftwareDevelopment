import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EnviarMensagem extends Thread {
	
	private Socket socketEnviar;
	private int porta;
	private String mensagemEnviada;

	public EnviarMensagem(int porta, String mensagemEnviada) {
		this.porta = porta;
		this.mensagemEnviada = mensagemEnviada;
	}

	private void abrirConexao(int porta) {
		try {
			this.socketEnviar = new Socket("127.0.0.1", porta);
		} catch (Exception e) {
			//e.printStackTrace();
			return;
		} 
	}
	
	private boolean setTimeOutSocket(){
		try {
			this.socketEnviar.setSoTimeout(10000);
		} catch (SocketException e) {
			//e.printStackTrace();
			return true;
		}
		return false;
	}

	public String enviarMensagemReceber() {
		String mensagemRecebida = "";

		try {
			abrirConexao(porta);
			if (socketEnviar != null) {
				enviarMensagemServidor();
				mensagemRecebida = receberMensagem();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return mensagemRecebida;
	}

	private void enviarMensagemServidor() throws IOException {
		OutputStream os = socketEnviar.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(mensagemEnviada + "\n");
		bw.flush();
	}

	private String receberMensagem() {
		String mensagemRecebida = "";
		try {
			InputStream is = socketEnviar.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			mensagemRecebida = br.readLine();
			System.out.println("Recebeu mensagem do Server: " + mensagemRecebida);
			if (!mensagemRecebida.equals("IAA")) {

			}
			socketEnviar.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mensagemRecebida;
	}

	@Override
	public void run() {
		enviarMensagemReceber();
	}

}
