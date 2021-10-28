package os;

import java.util.ArrayList;
import java.util.HashMap;

public class NetInterface 
{
    public String name = "";
    public String description = "";
    public ArrayList<String> ips = new ArrayList<>();

    public boolean hasIP = false;

    public NetInterface(String name, String desc, ArrayList<String> ips)
    {
        this.name = name;
        this.description = desc;
        
        for (String ip : ips) {
            this.ips.add(ip.replaceAll("/", ""));
        }

        if (ips.size() > 0)
            hasIP = true;
    }

    public static HashMap<String, NetInterface> filterIPInterfaces(HashMap<String, NetInterface> interfaces)
    {
        HashMap<String, NetInterface> filteredIfaces = new HashMap<String, NetInterface>();

        for (String ifaceName : interfaces.keySet()) 
        {
            if (interfaces.get(ifaceName).hasIP)  
                filteredIfaces.put(ifaceName, interfaces.get(ifaceName));  
        }

        return filteredIfaces;
    }

    public void print()
    {
        System.out.printf("[%s] - %s\n%s\n\n", name, ips.toString(), description);
    }
}   
