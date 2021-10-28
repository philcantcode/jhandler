package discovery;

import executer.CmdBuilder;

public class Nmap 
{
    public Nmap() 
    {
        //TODO STUFF
    }

    public static boolean IsInstalled()
    {
        return new CmdBuilder().setProg("nmap").setArgs("-v").silence().run().status();
    }
}
