import java.io.File;
import java.io.FileWriter;
import java.util.*;
public class file_manager {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("D:\\Users\\tsc2\\Desktop\\arquivos\\infrasoft.txt"); // mudar o diretÃ³rio
		Scanner in = new Scanner(file);
		FileWriter out = new FileWriter("D:\\Users\\tsc2\\Desktop\\arquivos\\Saída.txt");
		while(in.hasNext()) {
			String a = in.nextLine();
			//System.out.println(a);
			out.write(a + "\n");
		}
		
		
		
		
		in.close();
		out.close();
	}

}
