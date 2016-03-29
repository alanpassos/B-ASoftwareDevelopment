import java.util.ArrayList;


public class Processo extends Thread {
	
	private int id;
	private boolean isCoordenador;
	private int idCoordenadorAtual;
	private boolean isAtivo;
	private ArrayList<Processo> processos;
	
	public Processo(int id, ArrayList<Processo> processos) {
		super();
		this.id = id;
		this.processos = processos;
		this.isCoordenador = false;
		this.isAtivo = true;
	}

	public int getIdentificador() {
		return id;
	}

	public void setIdentificador(int id) {
		this.id = id;
	}

	public boolean isCoordenador() {
		return isCoordenador;
	}

	public void setCoordenador(boolean isCoordenador) {
		this.isCoordenador = isCoordenador;
	}

	public int getIdCoordenadorAtual() {
		return idCoordenadorAtual;
	}

	public void setIdCoordenadorAtual(int idCoordenadorAtual) {
		this.idCoordenadorAtual = idCoordenadorAtual;
	}
	
	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	
	public void processoCoordenador(){
		
	}

	public void processoParticipante(){
		enviaMensagem("AYA", getIdCoordenadorAtual());
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recebeMensagem(String mensagem, int idProcessoRemetente){
		if(mensagem.toUpperCase().equals("AYA")){
			processos.get(idProcessoRemetente).enviaMensagem("IAA", getIdentificador());
		} else if(mensagem.toUpperCase().equals("IAA")){
			System.out.println("MENSAGEM RECEBIDA DO COORDENADOR");
		}
	}
	
	public void enviaMensagem(String mensagem, int idProcessoDestino){
		processos.get(idProcessoDestino).recebeMensagem(mensagem, getIdentificador());
	}
	
	@Override
	public void run(){
		while(isAtivo){
			if(isCoordenador){
				processoCoordenador();
			} else {
				processoParticipante();
			}
			
		}
	}
	
}
