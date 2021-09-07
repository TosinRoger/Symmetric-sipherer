package br.com.tosin.seguncaauditoria.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveFile {

	public void saveFile(String content, String filename){
		FileWriter arq = null;
		PrintWriter gravarArq ;
		String temp = filename;
		
		try {
			
			if (filename.endsWith(".txt"))
				temp = filename.substring(0, filename.length()-4);
			
			
			arq = new FileWriter(temp);
			gravarArq = new PrintWriter(arq);
			
			gravarArq.println(content);
			
			arq.close();
			
			System.out.println("Arquivo gerado com sucesso!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao gerar arquivo!");
			e.printStackTrace();
		}
		
	}
}
