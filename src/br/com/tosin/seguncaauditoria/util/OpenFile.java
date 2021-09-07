package br.com.tosin.seguncaauditoria.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenFile {

    public String readFile(String filename) {

        if (filename.endsWith(".txt")) {
            return readTxt(filename);
        } else if (filename.endsWith(".dat")) {
            return readDat(filename);
        }

        return null;
    }

    private String readDat(String filename) {
        String result = "";

        BufferedReader br = null;

        try {
            String name = filename;

            FileReader file = new FileReader(new File(name));
            br = new BufferedReader(file);
            String temp = br.readLine();
            while (temp != null) {
                temp = br.readLine();
                System.out.println(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    private String readTxt(String filename) {
        String result = "";
        BufferedReader bufferedReader = null;

        try {
            FileReader file = new FileReader(filename);
            bufferedReader = new BufferedReader(file);
            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            result = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return result;
    }
}
