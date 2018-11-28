import java.util.*;
import java.io.*;

public class Logic  {

	static boolean satisfies(Map<Character, Boolean> valoresverdade, String exp) {
		if(exp.length() == 1)
			return valoresverdade.get(exp.charAt(0)); // se for expressão atômica, retorna o valor no mapa.
		
		int s = 0;
		int f = exp.length() - 1;
		int operatorIndex = findOperator(exp); 
		switch(exp.charAt(operatorIndex)) {
		
		case '~': return !satisfies(valoresverdade, exp.substring(operatorIndex + 1, f));
		case '&': return
					satisfies(valoresverdade, exp.substring(s + 1, operatorIndex - 1))
					&& satisfies(valoresverdade, exp.substring(operatorIndex + 2, f));
		case 'v': return
				satisfies(valoresverdade, exp.substring(s + 1, operatorIndex - 1))
				|| satisfies(valoresverdade, exp.substring(operatorIndex + 2, f));
		case '>': return
				!satisfies(valoresverdade, exp.substring(s + 1, operatorIndex - 1))
				|| satisfies(valoresverdade, exp.substring(operatorIndex + 2, f));
		default:
				return true;
		}
		
		
	}
	
	static int findOperator(String exp) {	
		int numParenthesis = 0;
		for(int i = 0; i < exp.length(); i++) {
			
			if(exp.charAt(i) == '(')
				numParenthesis++;
			else if(exp.charAt(i) == ')')
				numParenthesis--;
			
			if((exp.charAt(i) == '>' || exp.charAt(i) == '&' || exp.charAt(i) == 'v' || 
					exp.charAt(i) == '~') && numParenthesis == 1)
				return i;
		}
		
		return -1;		
	}

    static boolean isLegit(String exp) {
		if(exp.length() == 1 && exp.charAt(0) >= 'A' && exp.charAt(0) <= 'Z') // se for uma expressão atômica composta por um váriavel, retorna true
			return true;
		int s = 0;
		int f = exp.length() - 1;
		if(exp.length() == 0)
			return false;
		if(exp.charAt(s) == '(' && exp.charAt(f) == ')' && exp.charAt(s + 1) == '~' && s + 2 < f) // caso seja negação, entra neste if
			return isLegit(exp.substring(s + 2, f));
		else if(exp.charAt(s) == '(' && exp.charAt(f) == ')') {
			int operatorIndex = findOperator(exp);
			if(operatorIndex != -1 && exp.charAt(operatorIndex-1) == ' ' && exp.charAt(operatorIndex+1) == ' ') {
				if(operatorIndex == 1 || operatorIndex == s-1)
					return false;
				return isLegit(exp.substring(s + 1, operatorIndex - 1)) && 
						isLegit(exp.substring(operatorIndex + 2, f));
			}
		}	
		return false;
	}    

    public static void main (String[]arg) throws Exception {
        File file = new File("/home/lpi1/javamans/Entrada.in"); // mudar o diretório
		Scanner in = new Scanner(file);
		FileWriter out = new FileWriter("Saída.out");
		int casos = in.nextInt(); // quantidade de casos.
		in.nextLine();
		String exp; // Variável que recebe o prox caso.
		String ordem; // Variável que auxilia a assimilar os valores de cada variável ao map.
		String expDefinitiva; // Variável que será a expressao quando houver apenas uma expressão
		String[] expMultiplas; // Array de clausulas para quando há chaves.
		Map<Character, Boolean> valoresverdade = new HashMap<>(); // Mapa que relaciona as variaveis aos seus valores-verdades
		int v0; // Index do primeiro valor de 0.
		int v1; // Index do primeiro valor de 1.
		boolean satisfaz; // Diz se o conjunto de expressões é sastisfátivel.
		boolean legitima; // Diz se o conjunto de expressões é legítimo.
		 
		for(int c = 0;c<casos;c++) {
			//System.out.printf("Problema #%d\n", c+1);
			out.write("Problema #" + (c+1) + "\n");
			/* reinicindo as variáveis */
			ordem = "";
			satisfaz = true;
			legitima = true;
			/*                         */
			exp = in.nextLine();
			if(!(exp.charAt(0) == '{')) { // Entra aqui caso não haja chaves no início.
				if(exp.contains("0") || exp.contains("1")) {
					v0 = exp.indexOf('0');
					v1 = exp.indexOf('1');
					if((v0 > v1 && v1 !=-1) || v0 == -1)
						expDefinitiva = exp.substring(0, v1-1);
					else 
						expDefinitiva = exp.substring(0, v0-1);
					if(isLegit(expDefinitiva)) {
						for(int i = 0;i<exp.length();i++) { // Esse for associa as variáveis aos seus valores verdades
							if(exp.charAt(i) == '0') {
								valoresverdade.put(ordem.charAt(0), false);
								if(!(ordem.length() == 1))
									ordem = ordem.substring(1);
							} else if(exp.charAt(i) == '1') {
								valoresverdade.put(ordem.charAt(0), true);
								if(!(ordem.length() == 1))
									ordem = ordem.substring(1);
							} else {
								if(!ordem.contains(exp.substring(i, i+1)) && exp.charAt(i) >= 'A' && exp.charAt(i) <= 'Z')
									ordem = ordem.concat(exp.substring(i, i+1));
							}
						}
						if(satisfies(valoresverdade, expDefinitiva)) {
							//System.out.printf("A valoracao-verdade satisfaz a proposicao.\n");
							out.write("A valoracao-verdade satisfaz a proposicao.\n");
						} else {
							//System.out.printf("A valoracao-verdade nao satisfaz a proposicao.\n");
							out.write("A valoracao-verdade nao satisfaz a proposicao.\n");
						}
				} else {
					//System.out.printf("A palavra nao e legitima.\n");
					out.write("A palavra nao e legitima.\n");
				}
				
				} else {
					//System.out.printf("A palavra nao e legitima.\n");
					out.write("A palavra nao e legitima.\n");
				}
				} else {
					if(exp.contains("0") || exp.contains("1")) {
					if(exp.contains(",")) {
						expMultiplas = exp.split(", ");
						expMultiplas[0] = expMultiplas[0].substring(1); // tirar as chaves da primeira expressão.
						v0 = expMultiplas[expMultiplas.length-1].indexOf('0');
						v1 = expMultiplas[expMultiplas.length-1].indexOf('1');
						if((v0 > v1 && v1 !=-1) || v0 == -1) // tirar as chaves e os valores verdades da ultima expressao
							expMultiplas[expMultiplas.length-1] = expMultiplas[expMultiplas.length-1].substring(0, v1-2); 
						else 
							expMultiplas[expMultiplas.length-1] = expMultiplas[expMultiplas.length-1].substring(0, v0-2);
					} else { // esse else retira as chaves para usar a função isLegit
						expMultiplas = new String[1];
						v0 = exp.indexOf('0');
						v1 = exp.indexOf('1');
						if((v0 > v1 && v1 !=-1) || v0 == -1)
							expMultiplas[0] = exp.substring(1, v1-2);
						else 
							expMultiplas[0] = exp.substring(1, v0-2);
					}
					
					for(int i = 0;i<expMultiplas.length;i++) {
						if(!isLegit(expMultiplas[i]))
							legitima = false;
					}
					
					if(!legitima)
						//System.out.printf("Ha uma palavra nao legitima no conjunto.\n");
						out.write("Ha uma palavra nao legitima no conjunto.\n");
					else {
						for(int i = 0;i<exp.length();i++) { // esse for associa os valores verdades às variaveis em ordem
							if(exp.charAt(i) == '0') {
								valoresverdade.put(ordem.charAt(0), false);
								if(!(ordem.length() == 1))
									ordem = ordem.substring(1);
							} else if(exp.charAt(i) == '1') {
								valoresverdade.put(ordem.charAt(0), true);
								if(!(ordem.length() == 1))
									ordem = ordem.substring(1);
							} else {
								if(!ordem.contains(exp.substring(i, i+1)) && exp.charAt(i) >= 'A' && exp.charAt(i) <= 'Z')
									ordem = ordem.concat(exp.substring(i, i+1));
							}
						}
						for(int i = 0;i<expMultiplas.length;i++) {
							if(!satisfies(valoresverdade, expMultiplas[i]))
								satisfaz = false;
						}
						if(satisfaz)
							//System.out.printf("A valoracao-verdade satisfaz o conjunto.\n");
							out.write("A valoracao-verdade satisfaz o conjunto.\n");
						else
							//System.out.printf("A valoracao-verdade nao satisfaz o conjunto.\n");
							out.write("A valoracao-verdade nao satisfaz o conjunto.\n");
					}
				} else {
					if(exp.charAt(exp.length()-2) == '}')
						//System.out.printf("Ha uma palavra nao legitima no conjunto.\n");
						out.write("Ha uma palavra nao legitima no conjunto.\n");
					else
						//System.out.printf("A palavra nao e legitima.\n");
						out.write("A palavra nao e legitima.\n");
				}
			
			}
			if(c != casos-1)
				//System.out.println("");
				out.write("\n");
		}
		in.close();
		out.close();
    }
}