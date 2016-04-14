import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketServidor extends Thread {
	private Socket socketRecebe;
	private String mensagemRetorno;

	public SocketServidor(Socket socketRemetente, String mensagemRetorno) {

		this.socketRecebe = socketRemetente;
		this.mensagemRetorno = mensagemRetorno;
	}

	private void aguardandoPergunta() {

		BufferedReader bfMensagemRemetente;

		try {
			bfMensagemRemetente = new BufferedReader(new InputStreamReader(socketRecebe.getInputStream()));
			String mensagem = bfMensagemRemetente.readLine();
			System.out.println("Rebebendo mensagem do remetente: " + mensagem + " Porta: " + socketRecebe.getPort());
			if (mensagemSolicitaID(mensagem)) {
				solicitacaoId(mensagem);	
			}

			BufferedWriter printRespostaAoRemetente = new BufferedWriter(
					new OutputStreamWriter(socketRecebe.getOutputStream()));
			printRespostaAoRemetente.write(mensagemRetorno);
			printRespostaAoRemetente.close();

			// System.out.println("Enviando mensagem ao processo");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void solicitacaoId(String mensagem) {
		String[] mensagemRecebidaCliente = mensagem.split("#");
		String[] mensagemRetornoArray = mensagemRetorno.split("#");
		if (mensagemSolicitaEleicao(mensagemRecebidaCliente)) {
			if (idClienteMaior(mensagemRecebidaCliente, mensagemRetornoArray)) {
				mensagemRetorno = " ";
			}
		}
	}

	private boolean idClienteMaior(String[] mensagemRecebidaCliente,
			String[] mensagemRetornoArray) {
		return Integer.parseInt(mensagemRecebidaCliente[0]) >= Integer.parseInt(mensagemRetornoArray[0]);
	}

	private boolean mensagemSolicitaEleicao(String[] mensagemRecebidaCliente) {
		return mensagemRecebidaCliente[1].equals("true");
	}

	private boolean mensagemSolicitaID(String mensagem) {
		return !mensagem.equals("AYA");
	}

	@Override
	public void run() {

		aguardandoPergunta();

	}

}
