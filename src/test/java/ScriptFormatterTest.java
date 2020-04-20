import com.eviltester.app.ScriptFormatterProcessor;
import com.eviltester.scriptformatter.files.*;
import com.eviltester.scriptformatter.formats.TimeEstimate;
import com.eviltester.scriptformatter.simplewriters.SimpleSystemOutBackedWriter;
import com.eviltester.scriptformatter.simplewriters.SimpleWriter;
import com.eviltester.scriptformatter.writers.ProcessorTextReport;
import org.junit.jupiter.api.Test;
import java.io.*;

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
            if(!configFile.read()){
                new SimpleSystemOutBackedWriter().writeAll(configFile.getErrorReports());
                throw new RuntimeException("Error processing Config File");
            }
            configFile.configure(paths);
        }

        // TODO: get timing estimates in HTML, JSON etc. to get timing estimates
        ScriptFormatterProcessor processor = new ScriptFormatterProcessor(paths);
        processor.outputAllScripts();

        SimpleWriter writer = new SimpleSystemOutBackedWriter();

        new ProcessorTextReport(writer).output(processor);




    }
}
