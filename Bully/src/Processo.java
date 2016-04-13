import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Processo extends Thread {
	
	public static final int PORTA_INICIAL_ESCUTA_AYA = 8000;
	public static final int PORTA_INICIAL_ESCUTA_IDENTIFICADOR = 9001;
	private int identificador;
	private boolean isCoordenador;
	private int portaEscutaAYA;
	private int portaEscutaSolicitacaoId;
	private ThreadEscuta threadEscutaAYA;
	private ThreadEscuta threadEscutaIdentificador;
	private int idCoordenadorAtual;

	public Processo(int identificador){
		this.identificador = identificador;
		this.isCoordenador = false;
		this.idCoordenadorAtual = 0;
		this.portaEscutaAYA = identificador + PORTA_INICIAL_ESCUTA_AYA;
		this.portaEscutaSolicitacaoId = identificador + PORTA_INICIAL_ESCUTA_IDENTIFICADOR;
	}
		
	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public boolean isCoordenador() {
		return isCoordenador;
	}

	public int getPortaEscutaAYA() {
		return portaEscutaAYA;
	}

	public int getPortaEscutaSolicitacaoId() {
		return portaEscutaSolicitacaoId;
	}
	
	public int getIdCoordenadorAtual() {
		return idCoordenadorAtual;
	}

	public void setIdCoordenadorAtual(int idCoordenadorAtual) {
		this.idCoordenadorAtual = idCoordenadorAtual;
	}

	public void setCoordenador() {
		isCoordenador = true;
		threadEscutaAYA = new ThreadEscuta(portaEscutaAYA, "IAA");
		threadEscutaIdentificador = new ThreadEscuta(portaEscutaSolicitacaoId, String.valueOf(identificador));
		threadEscutaIdentificador.start();
		threadEscutaAYA.start();
	}
	
	public void setNaoCoordenador(){
		isCoordenador = false;
		threadEscutaAYA.setAtivo(false);
		threadEscutaIdentificador.setAtivo(false);
	}
	
	public int solicitaIdCoordenador(int portaDestino){
		EnviarMensagem enviarMensagem = new EnviarMensagem(portaDestino, "Solicita Id Coordenador");
		int idCoordenadorAtual = -1;
		String mensagemRecebida = enviarMensagem.enviarMensagemReceber();
		if (!mensagemRecebida.equals(""))
			idCoordenadorAtual = Integer.valueOf(mensagemRecebida);
		return idCoordenadorAtual;
	}
	
	public void abrirConexaoPerguntaAYA() {
		final int portaDestino = idCoordenadorAtual + PORTA_INICIAL_ESCUTA_AYA;
		Thread threadEnviaAYA = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!isCoordenador) {
					new EnviarMensagem(portaDestino, "AYA").start();
					try {
						Thread.sleep(20000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		threadEnviaAYA.start();
	}
	
	@Override
	public void run() {
		//if (isServer) {
			System.out.println("Servidor");
			setCoordenador();
			int idCoordenador = (solicitaIdCoordenador(8002));
		//} else {
			System.out.println("processo");
			abrirConexaoPerguntaAYA();
		//}
	}

	public static void main(String[] args) {
		new Processo(1).start();
	}

}
