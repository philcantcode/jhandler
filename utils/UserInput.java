package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput 
{
    public static String getInput()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       
        try {
            return reader.readLine();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
