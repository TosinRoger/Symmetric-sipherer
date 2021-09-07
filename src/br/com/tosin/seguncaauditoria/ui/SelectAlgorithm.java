package br.com.tosin.seguncaauditoria.ui;

import java.util.Scanner;

import br.com.tosin.seguncaauditoria.almorithm.CipherCesar;
import br.com.tosin.seguncaauditoria.almorithm.CifradorVernam;
import br.com.tosin.seguncaauditoria.almorithm.FrequencyAnalyzer;

public class SelectAlgorithm {

    private static int LIMIT_OPTION = 5;
    private Scanner scanner;
    private int option = -1;


    public void execute() {

        System.out.println("digite help");

        scanner = new Scanner(System.in);

        while (true) {
            option = -1;

            String txtOpen = "";
            txtOpen = ioSelected();

            openOption(txtOpen);
        }
    }

    private void printOptions() {
        System.out.println("\n\n\n");
        System.out.println("-----------------------------");
        System.out.println("\n");

        System.out.println("0 - Para sair");

        System.out.println("Algoritmo de Cesar");
        System.out.println("Cifrar");
        System.out.println("texto-aberto.text e texto-cifrado.txt são arquivo na pasta raiz do projeto.");
        System.out.println("cesar -c -k 5 < texto-aberto.txt > texto-cifrado.txt");
        System.out.println("Decifrar");
        System.out.println("cesar -d -k 5 < texto-cifrado.txt > texto-saida.txt");

        System.out.println("\n");

        System.out.println("Cifrador de Vernam");
        System.out.println("Cifrar");
        System.out.println("texto-aberto.text e texto-cifrado.txt são arquivo na pasta raiz do projeto.");
        System.out.println("vernam -c chave.txt < texto-aberto.txt > texto-cifrado.txt");
        System.out.println("Decifrar");
        System.out.println("vernam -d chave.txt < texto-cifrado.txt > texto-saida.txt");

        System.out.println("\n");

//        System.out.println("Cifrador RC4");
//        System.out.println("Cifrar");
//        System.out.println("openssl rc4 -in input.txt -out output.rc4");
//        System.out.println("Decifrar");
//        System.out.println("openssl rc4 -d -in output.rc4 -out inputOriginal.txt");

        System.out.println("\n");
        System.out.println("Analisador de frequencia");
        System.out.println("freqAnalyzer \"texto que deseja saber a frequencia, nao percia de aspas\"");
    }

    private String ioSelected() {
        String txt = "";

        do {
            txt = scanner.nextLine();

            if (txt.equals("0"))
                option = 0;
            else if (txt.equalsIgnoreCase("help") || txt.equalsIgnoreCase("ajuda"))
                option = 1;
            else if (txt.startsWith("cesar"))
                option = 2;
            else if (txt.startsWith("vernam"))
                option = 3;
//            else if (txt.startsWith("openssl"))
//                option = 4;
            else if (txt.startsWith("freqAnalyzer"))
                option = 5;

        } while (option < 0 && option >= LIMIT_OPTION);

        return txt;
    }

    private void openOption(String txtOpen) {
        switch (option) {
            case 0:
                System.exit(0);
                break;
            case 1:
                printOptions();
                break;
            case 2:
                String[] temp = validInputCesar(txtOpen);

                if (temp != null) {
                    CipherCesar cipherCesar = new CipherCesar();
                    cipherCesar.execute(temp);
                } else {
                    System.out.println("Entrada invalida");
                }
                break;
            case 3:
                CifradorVernam vernam = new CifradorVernam();
                vernam.execute(txtOpen);
                break;

            case 5:
                String parse = txtOpen.substring(13);
                FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
                frequencyAnalyzer.execute(parse);
                break;
            default:
                break;
        }
    }

    private String[] validInputCesar(String textOpen) {
        String[] temp = textOpen.split(" ");

        /*
         * cifrar
         * cesar -c -k 5 < texto-aberto.txt > texto-cifrado.txt
         * descifrar
         * cesar -d -k 5 < texto-cifrado.txt > texto-aberto.txt
         */

        if (temp.length != 8) {
            return null;
        }
        if (!temp[1].equalsIgnoreCase("-c") && !temp[1].equalsIgnoreCase("-d"))
            return null;
        if (Integer.parseInt(temp[3]) < 0)
            return null;

        return temp;
    }
}
