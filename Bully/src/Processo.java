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

	public Processo(int identificador) {
		this.identificador = identificador;
		this.isCoordenador = false;
		this.idCoordenadorAtual = 0;
		this.portaEscutaAYA = identificador + PORTA_INICIAL_ESCUTA_AYA;
		this.portaEscutaSolicitacaoId = identificador + PORTA_INICIAL_ESCUTA_IDENTIFICADOR;
		iniciaThreadEscutaIdentificador();
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

	public void iniciaThreadEscutaIdentificador() {
		threadEscutaIdentificador = new ThreadEscuta(portaEscutaSolicitacaoId,
				String.valueOf(identificador) + "#" + isCoordenador());
		threadEscutaIdentificador.start();
	}

	public void setCoordenador() {
		isCoordenador = true;
		iniciaThreadEscutaAYA();
		threadEscutaIdentificador.setMensagemRetorno(String.valueOf(identificador) + "#" + isCoordenador());
	}

	private void iniciaThreadEscutaAYA() {
		threadEscutaAYA = new ThreadEscuta(portaEscutaAYA, "IAA");
		threadEscutaAYA.start();
		threadEscutaIdentificador.setMensagemRetorno(String.valueOf(identificador) + "#" + isCoordenador());
	}

	public void setNaoCoordenador() {
		isCoordenador = false;
		threadEscutaAYA.setAtivo(false);
	}

	public String solicitaIdProcesso(int portaDestino, boolean isEleicao) {
		EnviarMensagem enviarMensagem = new EnviarMensagem(portaDestino, String.valueOf(identificador) + "#" + isEleicao);
		String mensagemRecebida = enviarMensagem.enviarMensagemReceber();
		return mensagemRecebida;
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
		// if (isServer) {
		System.out.println("Servidor");
		setCoordenador();
		String mensagem = (solicitaIdProcesso(8002, false));
		// } else {
		System.out.println("processo");
		abrirConexaoPerguntaAYA();
		// }
	}

	public static void main(String[] args) {
		new Processo(1).start();
	}

}
