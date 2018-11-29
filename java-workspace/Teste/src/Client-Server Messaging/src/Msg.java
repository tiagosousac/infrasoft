import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Msg extends UnicastRemoteObject implements RMI {
	String message = "Bom dia grupo";
	public Msg() throws RemoteException {}
	public void setMsg(String message) {
		this.message = message;
		System.out.println("A função SET foi usada:" + message);
	}
	public String getMsg() {
		System.out.println("A função GET foi usada:" + message);
		return this.message;
	}
}
