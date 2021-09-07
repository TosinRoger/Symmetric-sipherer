package br.com.tosin.seguncaauditoria.almorithm;

import java.util.HashMap;

import br.com.tosin.seguncaauditoria.ui.PrintInScreen;

public class FrequencyAnalyzer {

    private HashMap<String, Float> freqTable;

    public FrequencyAnalyzer() {
        completeList();
    }

    private final void completeList() {
        freqTable = new HashMap<>();
        freqTable.put("a", (float) 14.63);
        freqTable.put("b", (float) 01.04);
        freqTable.put("c", (float) 03.88);
        freqTable.put("d", (float) 04.99);
        freqTable.put("e", (float) 12.57);
        freqTable.put("f", (float) 01.02);
        freqTable.put("g", (float) 01.30);
        freqTable.put("h", (float) 01.28);
        freqTable.put("i", (float) 06.18);
        freqTable.put("j", (float) 00.40);
        freqTable.put("k", (float) 00.02);
        freqTable.put("l", (float) 02.78);
        freqTable.put("m", (float) 04.74);
        freqTable.put("n", (float) 01.28);
        freqTable.put("o", (float) 10.73);
        freqTable.put("p", (float) 02.52);
        freqTable.put("q", (float) 01.20);
        freqTable.put("r", (float) 06.53);
        freqTable.put("s", (float) 07.81);
        freqTable.put("t", (float) 04.34);
        freqTable.put("u", (float) 04.63);
        freqTable.put("v", (float) 01.67);
        freqTable.put("w", (float) 00.01);
        freqTable.put("x", (float) 00.21);
        freqTable.put("y", (float) 00.01);
        freqTable.put("z", (float) 00.47);
    }

    public void execute(String txtInput) {

        // mapa de recorrencia das letras
        HashMap<String, Integer> recurrence = countRecurrence(txtInput);

        // letra que mais apareceu
        String letter = moreAppear(recurrence);

        // distancia dessa letra ate o 'a' ou 'A'
        int distance = distanceToA(letter);

        // deixa em modulo se for negativo (geralmente eh)
        distance = Math.abs(distance);

        // abrir cesar com k == distance
        String result = new CipherCesar().internalCall(txtInput, distance);

        new PrintInScreen().showAnalyzer(
                mapToString(),
                txtInput,
                result,
                recurrence.toString(),
                mapFrequencyRecurrence(recurrence, txtInput.length()).toString(),
                letter,
                distance
        );
    }

    /**
     * Conta a recorrencia de cada caracter em um texto, exceto espaço, ponto e vírgula
     *
     * @param input
     * @return HashMap com o valor da recorrencia de cada letra no texto
     */
    private HashMap<String, Integer> countRecurrence(String input) {
        HashMap<String, Integer> recurrence = new HashMap<>();

        for (char c : input.toCharArray()) {
            if (c != ' ' && c != ',' && c != '.')
                recurrence.put(String.valueOf(c), (recurrence.containsKey(String.valueOf(c)) ? recurrence.get(String.valueOf(c)) + 1 : 1));
        }

        return recurrence;
    }

    /**
     * Procura a letra que mais apareceu a string analisada
     *
     * @param recurrence Letra que mais apareceu
     * @return
     */
    private String moreAppear(HashMap<String, Integer> recurrence) {
        int bigger = 0;
        String letter = "";

        for (String key : recurrence.keySet()) {
            if (recurrence.get(key) > bigger) {
                bigger = recurrence.get(key);
                letter = key;
            }
        }

        return letter;
    }

    /**
     * Verifica qual a distancia da letra que mais apareceu ate a letra 'a'
     *
     * @param letter
     * @return
     */
    private int distanceToA(String letter) {
        int distance = 0;

        if (!letter.isEmpty()) {
            if (letter.charAt(0) >= 'a' && letter.charAt(0) <= 'z') {
                distance = 'a' - letter.charAt(0);
            } else if (letter.charAt(0) >= 'A' && letter.charAt(0) <= 'Z') {
                distance = 'A' - letter.charAt(0);
            }
        }

        return distance;
    }

    private String mapToString() {
        return freqTable.toString();
    }

    private HashMap<String, Float> mapFrequencyRecurrence(HashMap<String, Integer> recurrence, int length) {
        HashMap<String, Float> freqTxt = new HashMap<>();

        for (String key : recurrence.keySet()) {
            float percent = ((float) recurrence.get(key) / length) * 100;
            freqTxt.put(key, percent);
        }

        return freqTxt;
    }
}
