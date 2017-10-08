package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static BufferedReader reader;
    public static void main(String[] args) throws Exception {
	// write your code here
        Scanner input=new Scanner(System.in);
        System.out.println("Find anagrams for:");
        String ana=input.nextLine();

        URL  myUrl=new URL("https://new.wordsmith.org/anagram/anagram.cgi?anagram="+ana+"&t=500&a=n");
        reader=new BufferedReader(new InputStreamReader(myUrl.openStream()));
        String inputLine;
        boolean Have=false;
        while((inputLine=reader.readLine())!=null)
        {
            ArrayList Arr=getResult(inputLine);
            if(Arr.size()!=0){
                for (int i=0;i<Arr.size();i++){
                    System.out.println(Arr.get(i));
                }
                Have=true;

                break;
            }
        }
       if(!Have){
           System.out.println("No anagrams for "+ana);
       }


    }

    public static ArrayList<String> getResult(String Line) {
        ArrayList<String> result=new ArrayList<String>();
        int Isthere=Line.indexOf("Displaying all:");
        if(Isthere==-1){
            return result;
        }else{
            String inputLine;
            try {
                while (!(inputLine = reader.readLine()).contains("<script>document.body.style.cursor='default';</script></div>")) {
                 inputLine=inputLine.replaceAll("</b>","");
                 inputLine=inputLine.replaceAll("<br>","");
                 result.add(inputLine.trim());
                }
                return result;
            }catch(Exception E){
               return result;
            }

        }
    }


}
