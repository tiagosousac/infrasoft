import java.rmi.Naming;

public class Server {
	public static void main(String[] args) {
		try {
			RMI servidor = new Msg();
			Naming.rebind("servidor", servidor);
			System.out.println("Servidor online.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
