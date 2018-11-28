import java.rmi.server.UnicastRemoteObject;

public class Msg extends UnicastRemoteObject implements RMI {
	private String message = "Não há nenhuma mensagem";
	public void printMsg() {
		System.out.println("Mensagem top");
	}
	public String getMsg() {
		System.out.println("Chamou o metodo GET:" + message);
		return this.message;
	}
	public void setMsg(String message) {
		this.message = message;
		System.out.println("Chamou o metodo SET:" + message);
	}
}
