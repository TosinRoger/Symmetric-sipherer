package br.com.tosin.seguncaauditoria.almorithm;

import java.util.ArrayList;
import java.util.List;

import br.com.tosin.seguncaauditoria.util.OpenFile;
import br.com.tosin.seguncaauditoria.util.SaveFile;

public class CifradorVernam {

	public void execute(String txt) {
		// txt === vernam -c chave.dat < texto-aberto.txt > texto-cifrado.txt
		String[] input = txt.split(" ");
		String inputText;
		String letterEncode;
		String key;
		
		OpenFile openFile = new OpenFile();
		
		// verifica se e criptografia ou decriptografia
		if (input[1].equals("-c")) {
			letterEncode = openFile.readFile(input[2]);
			inputText = openFile.readFile(input[4]);
			
			// verifica se o texto e a chave sao valido
			if (letterEncode != null && inputText != null && !letterEncode.isEmpty() && !inputText.isEmpty()) {
				key = buildKey(letterEncode, inputText.length());
				
				String result = cipher(inputText, key);
				
				SaveFile save = new SaveFile();
				save.saveFile(result, input[6]);
				
				// mostrar resultados e analise de frequencia
				
				new FrequencyAnalyzer().execute(result);
			}
			else {
				System.out.println("Chave ou texto vazio");
			}
		}
		else {
			letterEncode = openFile.readFile(input[2]);
			inputText = openFile.readFile(input[4]);
			
			// verifica se o texto e a chave sao valido
			if (!letterEncode.isEmpty() && !inputText.isEmpty()) {
				key = buildKey(letterEncode, inputText.length());
				
				String result = decipher(inputText, key);
				
				SaveFile save = new SaveFile();
				save.saveFile(result, input[6]);
				
				// mostrar resultados e analise de frequencia
				new FrequencyAnalyzer().execute(result);
			}
		}
	}
	
	/**
	 * Popula um mapa dos caracter usando a posição como chave e o caracter como valor. 
	 * As primeira posições são [A-Z], depois [a-z] por último [0-9]
	 * @return Map de caracteres
	 */
	private List<Character> mapLetter() {
		List<Character> alphabet = new ArrayList<>();
		for(int i = 'A'; i <= 'Z'; i++) {
			alphabet.add(((char)i));
		}
		for(int i = 'a'; i <= 'z'; i++) {
			alphabet.add(((char)i));
		}
		for (int i = '0'; i <= '9'; i++) {
			alphabet.add(((char)i));
		}

		return alphabet;
	}
	
	/**
	 * Constroi a chave do mesmo tamanho da mensagem de entrada. 
	 * Se a mensagem de entrada for menor que a chave de entrada, 
	 * entao a chave de saida sera quebrada do mesmo tamanho da mensagem de saida.
	 * @param pass Chave de entrada
	 * @param sizeTxt Tamanho do texto a ser encriptado
	 * @return chave de saida, do mesmo tamanho da mensagem
	 */
	private String buildKey(String pass, int sizeTxt) {
		String key = "";
		int count = -1;
		
		for (int i = 0; i < sizeTxt; i++) {
			count = pass.length() > count + 1 ? count + 1 : 0;
			
			key += pass.charAt(count);
		}
		
		return key;
	}
	
	/**
	 * Procura a posicao de um caracter no mapeamento de caracteres
	 * @param letter Caracter a ser procurado
	 * @return posicao do caracter na lista
	 */
	private int fetchPositionLetter(char letter) {
		List<Character> alphabet = mapLetter();
		for (int i = 0; i < alphabet.size(); i++) {
			if (alphabet.get(i) == letter) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Encripta uma mensagem com uma chave
	 * @param text
	 * @param key
	 * @return Texto encriptado
	 */
	private String cipher(String text, String key) {
		String txtChiper = "";
		
		List<Character> alphabet = mapLetter();
		
		for (int i = 0; i < text.length(); i++) {
			int posCharMsg = fetchPositionLetter(text.charAt(i));
			int posCharKey = fetchPositionLetter(key.charAt(i));
			
			// verifica se eh um character do map senao repete o caracter
			if (posCharMsg != -1 && posCharKey != -1) {
				int temp = posCharMsg + posCharKey;
				
				/*
				 * Se posicao da codificacao for menor do que tamanho adiciona a letra
				 * senao rotaciona para o inicio do vetor
				 */
				
				if (temp < alphabet.size()) {
					txtChiper += alphabet.get(temp);
				}
				else {
					temp = temp - alphabet.size();
					txtChiper += alphabet.get(temp);
				}
				
			}
			else {
				txtChiper += text.charAt(i);
			}

		}
		
		return txtChiper;
	}
	
	/**
	 * Desencripta uma mensagem com base em uma chave
	 * @param text Mensagem encriptada
	 * @param key Chave 
	 * @return Texto desencriptado
	 */
	private String decipher(String text, String key) {
		String txtChiper = "";
		
		List<Character> alphabet = mapLetter();
		
		
		for (int i = 0; i < text.length(); i++) {
			int posCharMsg = fetchPositionLetter(text.charAt(i));
			int posCharKey = fetchPositionLetter(key.charAt(i));
			
			// verifica se eh um character do map senao repete o caracter
			if (posCharMsg != -1 && posCharKey != -1) {
				int temp = posCharMsg - posCharKey;
				
				/*
				 * Se posicao da codificacao for maior do que 0 adiciona a letra
				 * senao rotaciona para o fim do vetor
				 */
				
				if (temp >= 0) {
					txtChiper += alphabet.get(temp);
				}
				else {
					temp = alphabet.size() + temp;
					txtChiper += alphabet.get(temp);
				}
			}
			else {
				txtChiper += text.charAt(i);
			}

		}
		
		return txtChiper;
	}
}
