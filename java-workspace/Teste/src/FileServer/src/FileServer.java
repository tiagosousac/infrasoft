import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileServer extends UnicastRemoteObject implements FileServerInt {
	
	private String file = "";
	protected FileServer() throws RemoteException {
		super();
	}
	
	public void setFile(String f) {
		file = f;
	}
	
	public boolean login(FileClientInt c) throws RemoteException {
		// sending the file
		try {
			File f1 = new File(file);
			FileInputStream in = new FileInputStream(f1);
			byte [] mydata = new byte[1024*1024];
			int mylen = in.read(mydata);
			while(mylen>0) {
				c.sendData(f1.getName(), mydata, mylen);
				mylen = in.read(mydata);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}

