package os;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

public class Network 
{
    public Network()
    {
        
    }

    // Return a list of network interfaces
    public static HashMap<String, NetInterface> getInterfaces()
    {
        HashMap<String, NetInterface> interfaces = new HashMap<>();

        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

            for (NetworkInterface iface : Collections.list(nets))
            {
                String displayName = iface.getDisplayName();
                String name = iface.getName();
                ArrayList<String> ips = new ArrayList<>();

                Enumeration<InetAddress> inetAddresses = iface.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) 
                {
                    ips.add(inetAddress.toString());
                }

                NetInterface ifaceObj = new NetInterface(name, displayName, ips);
                interfaces.put(name, ifaceObj);
            }
                
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return interfaces;
    }
}
