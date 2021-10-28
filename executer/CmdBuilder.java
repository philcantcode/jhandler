package executer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CmdBuilder 
{
    private String prog = "";
    private ArrayList<String> args = new ArrayList<String>();
    private ArrayList<String> result = new ArrayList<String>();

    private ProcessBuilder builder;
    private Process process;

    private boolean combineStdoutStdErr = true;

    private boolean success = true;
    private boolean silence = false;

    public CmdBuilder()
    {

    }

    public CmdBuilder setProg(String prog)
    {
        this.prog = prog;

        return this;
    }

    public CmdBuilder setArgs(String... args)
    {
        for (String arg : args) 
        {
            this.args.add(arg);
        }

        return this;
    }

    public CmdBuilder run()
    {
        List<String> command = new ArrayList<String>();
        command.add(prog);
        command.addAll(args);

        builder = new ProcessBuilder(command);
        builder.redirectErrorStream(combineStdoutStdErr);

        try {
            process = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String result;
           
            while (true)
             {
                result = r.readLine();
                
                if (result == null) 
                { 
                    break; 
                }

                this.result.add(result);
            }
        } 
        catch (IOException e) {
            success = false;
            utils.Log.RuntimeLog(e);
        }

        if (!silence)
            printResult();

        return this;
    }

    // Hide the standard errors from output
    public CmdBuilder hideErr()
    {
        combineStdoutStdErr = false;

        return this;
    }

    public CmdBuilder silence()
    {
        silence = true;

        return this;
    }

    public boolean status()
    {
        return success;
    }

    public void printResult()
    {
        for (String r : result) {
            System.out.println(r);
        }
    }

}
