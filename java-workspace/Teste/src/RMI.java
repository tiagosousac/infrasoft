import java.rmi.Remote;
import java.rmi.RemoteException;

interface RMI extends Remote {
	public void printMsg() throws RemoteException;
	public void setMsg() throws RemoteException;
	public String getMsg(String message) throws RemoteException;
}
