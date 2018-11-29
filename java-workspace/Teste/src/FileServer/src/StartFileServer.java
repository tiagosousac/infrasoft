import java.rmi.Naming;

public class StartFileServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			
			FileServer fs = new FileServer();
			fs.setFile("C:\\Users\\Tiago - PC\\Desktop\\o1f0rsci5otx.jpg");
			Naming.rebind("rmi://localhost/abc", fs);
			System.out.println("Server online.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
