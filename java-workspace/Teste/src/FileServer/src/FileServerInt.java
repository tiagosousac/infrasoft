import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileServerInt extends Remote {
	public boolean login(FileClientInt c) throws RemoteException;
}
