import java.rmi.Naming;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RMI obj = (RMI)Naming.lookup("rmi://localhost/servidor");
			Scanner in = new Scanner(System.in);
			while(true) {
					/*String m = JOptionPane.showInputDialog("Digite uma mensagem para o servidor");
					obj.setMsg(m);
					JOptionPane.showMessageDialog(null, obj.getMsg());*/
				System.out.println("Opção '1': Ler mensagem; Opção '2': Alterar mensagem");
				String a = in.nextLine();
				if(a.equals("1")) {
					System.out.println(obj.getMsg());
				} else if (a.equals("2")) {
					String b = in.nextLine();
					obj.setMsg(b);
					System.out.println(b);
				} else {
					System.out.println("tente novamente");
				}
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
