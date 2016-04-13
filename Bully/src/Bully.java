import java.util.ArrayList;

public class Bully {
	public final int QUANTIDADE_PROCESSOS = 4;
	private ArrayList<Processo> processos;

	public Bully() {
		this.processos = new ArrayList<Processo>();
	}

	public void adicionaProcesso() {
		Processo processo;
		for (int i = 0; i < QUANTIDADE_PROCESSOS; i++) {
			processo = new Processo(i);
			if (listaProcessoVazia()) {
				processo.setCoordenador();
			} else {
				processo.setIdCoordenadorAtual(buscaCoordenador(processo));
				processo.abrirConexaoPerguntaAYA();
			}
			processos.add(processo);
		}
	}

	public int buscaCoordenador(Processo processo) {
		String[] mensagemRetornofFuncao;
		int portaInicial = Processo.PORTA_INICIAL_ESCUTA_IDENTIFICADOR;
		for (int i = 0; i < processos.size(); i++) {
			mensagemRetornofFuncao = processo.solicitaIdProcesso(portaInicial + i).split("#");
			if (mensagemRetornofFuncao[1].equals("true")) {
				return Integer.parseInt(mensagemRetornofFuncao[0]);
			}
		}
		return -1;
	}

	private boolean listaProcessoVazia() {
		return (processos.size() == 0);
	}

	public static void main(String[] args) {
		Bully bully = new Bully();
		bully.adicionaProcesso();
	}
}
