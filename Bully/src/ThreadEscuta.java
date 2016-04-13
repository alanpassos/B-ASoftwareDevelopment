import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ThreadEscuta extends Thread {

	private int porta;
	private boolean isAtivo;
	private ServerSocket severSocket;
	private String mensageRetorno;

	public ThreadEscuta(int porta, String mensagemRetorno){
		this.porta = (porta);
		this.isAtivo = true;
		this.mensageRetorno = mensagemRetorno;
		iniciarServerSocket();
	}
	
	public boolean isAtivo() {
		return isAtivo;
	}
	
	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	private void iniciarServerSocket() {
		try {
			this.severSocket = new ServerSocket(porta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void abrirConexaoRespostaServidor() {
		try {
			Socket socketServer;
			while (isAtivo) {
				socketServer = severSocket.accept();
				if (socketServer != null) {
					SocketServidor resposta = new SocketServidor(socketServer, mensageRetorno);
					resposta.start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		abrirConexaoRespostaServidor();
	}
	
}
