package br.com.tosin.seguncaauditoria.ui;

public class PrintInScreen {

	public void showAnalyzer(String baseMap, String msgInput, String msgOutput, String recurrenceMap, String freqRecurrence, String letterMoreAppear, int k) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Frequencia base: ");
		System.out.println("Mensagem de entrada: ");
		System.out.println(msgInput);
		System.out.println("Mensagem de saida: ");
		System.out.println(msgOutput);
		System.out.println("----------------------------------------------------------------");
		System.out.println("Tamanho do texto: " + msgOutput.length());
		System.out.println("A letra que mais aparece eh: " + letterMoreAppear);
		System.out.println("Esta a " + k + " letras de distancia do 'a' ou do 'A', levando em considerecao se eh maiuscula ou nao");
		System.out.println("Recorrencia no texto original: ");
		System.out.println(recurrenceMap);
		System.out.println("Frequencia do caracter no texto: ");
		System.out.println(freqRecurrence);
		System.out.println("----------------------------------------------------------------");
		System.out.println("\n");
	}
}
