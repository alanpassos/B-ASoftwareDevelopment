import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Processo extends Thread {

	ServerSocket processo;

	boolean isServer = false;

	private void AbrirConexao() {
		try {
			processo = new ServerSocket(8696);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void abrirConexaoResposta() {
		try {

			Socket socketServer;
			while (isServer) {
			//	System.out.println("Aguardando pergunta");
				socketServer = processo.accept();
				if (socketServer != null) {
					RespostaServidor resposta = new RespostaServidor(socketServer);
					resposta.start();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void abrirConexaoPergunta() {
		while (!isServer) {
			//System.out.println("Respondendo");

			new EnviarMensagem(8696).start();

			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void run() {

		if (isServer) {
			AbrirConexao();
			abrirConexaoResposta();
		} else {
			abrirConexaoPergunta();
		}

	}

	public static void main(String[] args) {
		new Processo().start();
	}

}
