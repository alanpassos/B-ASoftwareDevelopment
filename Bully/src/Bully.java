import java.util.ArrayList;


public class Bully {
	public final int QUANTIDADE_PROCESSOS = 4;
	private ArrayList<Processo> processos;
	
	public Bully(){
		this.processos = new ArrayList<Processo>();
	}
	
	public void adicionaProcesso(){
		Processo processo;
		for (int i = 0; i < QUANTIDADE_PROCESSOS; i++) {
			processo = new Processo(i);
			if(listaProcessoVazia()){
				processo.setCoordenador();
			}else{
				processo.setIdCoordenadorAtual(buscaCoordenador(processo));
				processo.abrirConexaoPerguntaAYA();
			}
			processos.add(processo);
		}
	}
	
	public int buscaCoordenador(Processo processo){
		int idCoordenadorAtual = 0;
		int valorRetornofFuncao = 0;
		int portaInicial = Processo.PORTA_INICIAL_ESCUTA_IDENTIFICADOR;
		for (int i = 0; i < processos.size(); i++) {
			valorRetornofFuncao = processo.solicitaIdCoordenador(portaInicial + i);
			if (valorRetornofFuncao != -1)
				idCoordenadorAtual = valorRetornofFuncao;
		}
		return idCoordenadorAtual;
	}
	
	private boolean listaProcessoVazia(){
		return (processos.size() == 0);
	}
	
	public static void main(String[] args) {
		Bully bully = new Bully();
		bully.adicionaProcesso();
	}
}
