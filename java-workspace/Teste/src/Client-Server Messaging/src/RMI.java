import java.rmi.Remote;
import java.rmi.RemoteException;

interface RMI extends Remote {
	public String getMsg() throws RemoteException;
	public void setMsg(String message) throws RemoteException;
}
