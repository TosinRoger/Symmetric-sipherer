package br.com.tosin.seguncaauditoria.almorithm;

import java.util.HashMap;

import br.com.tosin.seguncaauditoria.util.OpenFile;
import br.com.tosin.seguncaauditoria.util.SaveFile;

public class CipherCesar {
	/**
	 * cifrar cesar -c -k 5 < texto-aberto.txt > texto-cifrado.txt descifrar
	 * cesar -d -k 5 < texto-cifrado.txt > texto-aberto.txt
	 */
	public void execute(String[] txtOpen) {
		OpenFile openFile = new OpenFile();
		String file = openFile.readFile(txtOpen[5]);
		String result = "";
		if (file != null && !file.isEmpty()) {
			// encriptar
			if (txtOpen[1].equals("-c"))
				result = cipher(file, Integer.parseInt(txtOpen[3]));
			else
				result = decryption(file, Integer.parseInt(txtOpen[3]));

			SaveFile save = new SaveFile();
			save.saveFile(result, txtOpen[7]);

			new FrequencyAnalyzer().execute(file, result);
		}
	}

	/**
	 * Chamada de cesar dentro do proprio programa sempre precisar abrir um arquivo;
	 * @param txt Texto a ser decriptado
	 * @param k valor da chave
	 * @return Texto decriptado
	 */
	public String internalCall(String txt, int k) {
		String result = decryption(txt, k);
		return result;
	}

	/**
	 * Criptar de cesar
	 * @param input Text a ser criptado
	 * @param k chave
	 * @return Texto criptado
	 */
	private String cipher(String input, int k) {
		String result = "";
		
		
		for (int i = 0; i < input.length(); i++) {
			char originalLetter = (char) input.charAt(i);
			
			// se for ' ' ou ', ' ou '.'
			if (originalLetter == ' ' || originalLetter == ',' || originalLetter == '.') {
				result += originalLetter;
			}
			/*
			 * verificar se letra + k esta dentro do intervalo
			 * senao faz a rotacao
			 */
			else {
				HashMap<Integer, Character> map = mapLetter();
				
				int position = hasLetter(originalLetter);
				
				if(position != -1) {
					/*
					 *  se k for maior que tamanho do vetor pega o resto da divisão pelo tamanho
					 * 
					 *  se posicao + k for menor que o tamanho do map troca 
					 *  senao fazer a diferenca entre (size - posicao), entao fazer k - (diferenca)
					 *  e contar k a partir do 0
					 */
					int temp = k > map.size() ? k % map.size() : k;
					
					if(position + k < map.size()) {
						result += map.get(position + k);
					}
					
					else {
						temp = map.size() - position + k;
						temp = temp > map.size() ? temp - map.size() : temp;
						result += map.get(temp);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Decritador de cesar
	 * @param input Texto criptado
	 * @param k chave
	 * @return Texto decriptado
	 */
	private String decryption(String input, int k) {
		String result = "";
		
		
		for (int i = 0; i < input.length(); i++) {
			char originalLetter = (char) input.charAt(i);
			
			// se for ' ' ou ', ' ou '.'
			if (originalLetter == ' ' || originalLetter == ',' || originalLetter == '.') {
				result += originalLetter;
			}
			/*
			 * verificar se letra + k esta dentro do intervalo
			 * senao faz a rotacao
			 */
			else {
				HashMap<Integer, Character> map = mapLetter();
				
				int position = hasLetter(originalLetter);
				
				if(position != -1) {
					/*
					 *  se k for maior que tamanho do vetor pega o resto da divisão pelo tamanho
					 *  
					 *  se posicao + k for menor que o tamanho do map troca
					 *  senao fazer a diferenca entre (size - posicao), entao fazer k - (diferenca)
					 *  e contar k a partir do 0
					 */
					int temp = k > map.size() ? k % map.size() : k;
					
					if(position - k >= 1) {
						result += map.get(position - k);
					}
					else {
						temp = map.size() - k + position;
						temp = temp < 1 ? map.size() + temp : temp;
						result += map.get(temp);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Verifica se tem uma letra especifica no mapa de caracteres
	 * @param letter Letra a ser encontrada
	 * @return -1 se não encontrar, senão retorna a chave do caracter
	 */
	private int hasLetter(char letter) {
		HashMap<Integer, Character> map = mapLetter();
		
		for (int key : map.keySet()) {
			if (map.get(key) == letter) {
				return key;
			}
		}
		return -1;
	}
	
	/**
	 * Popula um mapa dos caracter usando a posição como chave e o caracter como valor. 
	 * As primeira posições são [A-Z], depois [a-z] por último [0-9]
	 * @return Map de caracteres
	 */
	private HashMap<Integer, Character> mapLetter() {
		HashMap<Integer, Character> map = new HashMap<>();
		int count = 1;
		for(int i = 'A'; i <= 'Z'; i++) {
			map.put(count, (char) i);
			count++;
		}
		for(int i = 'a'; i <= 'z'; i++) {
			map.put(count, (char) i);
			count++;
		}
		for (int i = '0'; i <= '9'; i++) {
			map.put(count, (char) i);
			count++;
		}
		
		return map;
	}

}
