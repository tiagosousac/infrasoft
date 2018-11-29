import java.rmi.Naming;
import java.util.Scanner;

public class StartFileClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileClient c = new FileClient("imed");
			FileServerInt server = (FileServerInt)Naming.lookup("rmi://localhost/abc");
			server.login(c);
			System.out.println("Listening...");
			Scanner s = new Scanner(System.in);
			while(true) {
				String line = s.nextLine();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
