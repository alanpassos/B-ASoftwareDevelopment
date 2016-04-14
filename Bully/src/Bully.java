import java.util.ArrayList;

public class Bully {
	public final int QUANTIDADE_PROCESSOS = 4;
	private ArrayList<Processo> processos;
	
	public Bully() {
		this.processos = new ArrayList<Processo>();
		this.processosEleitores = new ArrayList<Processo>();
	}

	public void adicionaProcesso() {
		Processo processo;
		for (int i = 0; i < QUANTIDADE_PROCESSOS; i++) {
			processo = new Processo(i);
			if (listaProcessoVazia()) {
				//processo.setCoordenador();
			} else {
				//processo.setIdCoordenadorAtual(buscaCoordenador(processo));
				//processo.abrirConexaoPerguntaAYA();
			}
			processos.add(processo);
		}
		int indiceVencedor = geraEleicao(processos.get(1));
		processos.get(indiceVencedor).setCoordenador();
		processos.get(1).setIdCoordenadorAtual(indiceVencedor);
		processos.get(1).abrirConexaoPerguntaAYA();
	}

	public int buscaCoordenador(Processo processo) {
		String[] mensagemRetornoFuncao;
		int portaInicial = Processo.PORTA_INICIAL_ESCUTA_IDENTIFICADOR;
		for (int i = 0; i < processos.size(); i++) {
			mensagemRetornoFuncao = processo.solicitaIdProcesso(portaInicial + i, false).split("#");
			if (mensagemRetornoFuncao[1].equals("true")) {
				return Integer.parseInt(mensagemRetornoFuncao[0]);
			}
		}
		return -1;
	}
	
	public int geraEleicao(Processo processo){
		int indiceVencedorEleicao = -1;
		String[] mensagemRetornoFuncao;
		int portaInicial = Processo.PORTA_INICIAL_ESCUTA_IDENTIFICADOR;
		for (int i = 0; i < processos.size(); i++) {
			System.out.println("ELEICAO");	
			mensagemRetornoFuncao = processo.solicitaIdProcesso(portaInicial + i, true).split("#");
			if (retornouIdMaior(mensagemRetornoFuncao)){
				indiceVencedorEleicao = Integer.valueOf(mensagemRetornoFuncao[0]);
				geraEleicao(processos.get(indiceVencedorEleicao));
			}
		}
		return indiceVencedorEleicao;
	}

	private boolean retornouIdMaior(String[] mensagemRetornoFuncao) {
		return mensagemRetornoFuncao.length > 1;
	}

	private boolean listaProcessoVazia() {
		return (processos.size() == 0);
	}

	public static void main(String[] args) {
		Bully bully = new Bully();
		bully.adicionaProcesso();
	}
}
