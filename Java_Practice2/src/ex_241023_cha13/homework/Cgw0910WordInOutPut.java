package ex_241023_cha13.homework;

import java.io.*;
import java.util.HashMap;

public class Cgw0910WordInOutPut {
    private File wordFile = new File("WordTestCgw0910.csv");

    public Cgw0910WordInOutPut() {
        try {
            if (!wordFile.exists()) {
                wordFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("파일 생성 중 오류 발생: " + e.getMessage());
        }
    }

    public void wordWriter(String word, String mean) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(wordFile, true))) {
            writer.write(word + "," + mean); // 단어와 의미를 한 줄에 기록
            writer.newLine(); // 줄 바꿈
        } catch (IOException e) {
            System.out.println("파일 쓰기 중 오류 발생: " + e.getMessage());
        }
    }

    public HashMap<String, String> wordLeader() {
        HashMap<String, String> wordsHashMap = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(wordFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) { // 단어와 의미 쌍으로 존재할 때만 추가
                    wordsHashMap.put(data[0], data[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
        
        return wordsHashMap;
    }
}
