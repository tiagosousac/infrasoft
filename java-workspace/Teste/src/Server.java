import java.rmi.Naming;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RMI servidor = new Msg();
			Naming.rebind("servidormsg", servidor);
			System.out.println("Servidor de mensagens no ar");
		} catch {
			
		}
	}

}
