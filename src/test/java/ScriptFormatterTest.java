import com.eviltester.scriptformatter.files.ScriptConfigFile;
import com.eviltester.scriptformatter.files.ScriptPaths;
import com.eviltester.scriptformatter.html.ScriptFormatter;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class ScriptFormatterTest {

    @Test
    public void givenAScriptFolderCreateAHTMLFiles() throws IOException {

        ScriptPaths paths = new ScriptPaths();

        // hard code your paths here, or use a config.properties file in user.dir
        paths.readFrom(""); // set your input path here if necessary e.g. "/my/input/path"
        paths.outputTo(""); // set your output path here if necessary e.g. "/my/output/path"

        // if you have config.properties in the root of
        // the project then read the
        // properties from there, leaving the Strings above blank
        // inputPath = /my/input/path
        // outputPath = /my/output/path
        if(!paths.hasInputPath()){

            ScriptConfigFile configFile = new ScriptConfigFile();
            configFile.read();
            configFile.configure(paths);
        }

        File inputFolder = new File(paths.getInputPath());
        File[] files = inputFolder.listFiles();
        Arrays.sort(files);

        for (final File fileEntry : files) {
            if(fileEntry.getName().endsWith(".md")){
                new ScriptFormatter(paths.getInputPath(), fileEntry.getName(), paths.getOutputPath(), fileEntry.getName()+".html").output();
            }
        }
    }
}
