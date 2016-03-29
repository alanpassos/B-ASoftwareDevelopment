import java.util.ArrayList;


public class Bully {
	
	private final int QUANTIDADE_PROCESSOS = 5;
	private ArrayList<Processo> processos;
	
	public Bully(){
		this.processos = new ArrayList<Processo>();
	}
	
	public void adicionarProcesso(){
		Processo processo;
		int idCoordenador;
		for (int i = 0; i < QUANTIDADE_PROCESSOS; i++) {
			processo = new Processo(i, processos);
			if(listaVazia()){
				processo.setCoordenador(true);
				processo.setIdCoordenadorAtual(processo.getIdentificador());
			} else {
				idCoordenador = getIdCoordenador();
				processo.setIdCoordenadorAtual(idCoordenador);
			}
			processo.start();
			processos.add(processo);
		}
	}

	public int getIdCoordenador(){
		for (Processo processo : processos) {
			if (processo.isCoordenador())
				return processo.getIdentificador();

		}
		return -1;
	}

	private boolean listaVazia() {
		return processos.size() == 0;
	}
	
	public static void main(String[] args) {
		Bully bully = new Bully();
		bully.adicionarProcesso();
	
	}
	
}
