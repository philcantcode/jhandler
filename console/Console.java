package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import os.NetInterface;

public class Console 
{

    private static BufferedReader reader;
    private static int inputCount = 0;

    public Console()
    {
        reader = new BufferedReader(new InputStreamReader(System.in));

        while (true)
        {
            try {
                System.out.print("> ");
                processInput(reader.readLine());
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void processInput(String input) 
    {
        switch (input) 
        {
            case "setup":
                setup();
                break;
            case "list ip ifaces":
                printIfaces(true);
                break;
            case "test plugins":
                testInstallations();
                break;
            case "q":
            case "Q":
            case "quit": 
                System.exit(0);
                break;
            default:
                System.out.printf("[%d] %s not recognised\n", inputCount++, input);
        }
    }

    private static void testInstallations()
    {
        System.out.printf("\t 1) NMAP = %b \n", discovery.Nmap.IsInstalled());
    }

    private static void printIfaces(boolean ipOnly)
    {
        HashMap<String, NetInterface> ifaces = os.Network.getInterfaces();
       
        for (String key: ifaces.keySet())
        {
            if (ipOnly && ifaces.get(key).hasIP)
                ifaces.get(key).print();
            else if (!ipOnly)
                ifaces.get(key).print();
        }
    }

    public static void setup()
    {
        System.out.println("Enter an interface to use:");
        printIfaces(true);
        String iface = utils.UserInput.getInput();
    }
}
