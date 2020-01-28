package com.eviltester.scriptformatter.html;

import java.io.*;

public class ScriptFormatter {
    private final String inputPath;
    private final String inputFileName;
    private final String outputPath;
    private final String outputFileName;

    public ScriptFormatter(final String inputPath, final String inputFileName, final String outputPath, final String outputFileName) {
        this.inputPath = inputPath;
        this.inputFileName = inputFileName;
        this.outputPath = outputPath;
        this.outputFileName = outputFileName;
    }

    public void output() throws IOException {
        // read the file
        File inputFile = new File(inputPath,inputFileName);
        if(!inputFile.exists()) {
            throw new RuntimeException("Input file not exist: " + inputFile.getAbsolutePath());
        }

        File outputFile = new File(outputPath,outputFileName);
        if(inputFile.getAbsolutePath().equalsIgnoreCase(outputFile.getAbsolutePath())){
            throw new RuntimeException("Input file same as output file: " + inputFile.getAbsolutePath());
        }

        if(outputFile.exists()){
            outputFile.delete();
        }

        new File(outputPath).mkdirs();


        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        final BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String nextLine = reader.readLine();

        boolean processingDo=false;
        boolean processingSay=false;

        StringBuilder doThis = new StringBuilder();
        StringBuilder sayThis = new StringBuilder();

        int sayWordCount=0;

        final TableWriter tableWriter = new TableWriter(writer);

        while(nextLine!=null){

            String trimmed = nextLine.trim();
            if(trimmed.equalsIgnoreCase("do:")){
                // we found a DO:
                tableWriter.writeLine(sayThis, doThis);

                //
                doThis = new StringBuilder();
                processingDo = true;

                processingSay=false;
            }

            if(trimmed.equalsIgnoreCase("say:")){
                // we found a SAY:
                sayThis = new StringBuilder();
                processingSay=true;
                processingDo=false;
            }


            if(processingDo || processingSay){

                if(processingDo){
                    doThis.append(trimmed + "<br>");
                }else{
                    // count say words
                    String[] words = trimmed.split(" ");
                    sayWordCount += words.length;
                    sayThis.append(trimmed + "<br>");
                }

            }else{
                // by default just write the line out
                writer.write(nextLine + "<br>");
                writer.newLine();
            }

            nextLine = reader.readLine();

        }

        // write the last say and do
        tableWriter.writeLine(sayThis, doThis);

        tableWriter.finish();
        writer.close();
        reader.close();

        System.out.println(String.format("words: %d  " + "time: %d  video: %s", sayWordCount, sayWordCount/100, inputFileName));
        for(int wordsPerMinute = 100; wordsPerMinute<=150; wordsPerMinute+=10){
            final int minuteWordCount = sayWordCount / wordsPerMinute;
            int remainingWordsPerSecond = (int)(((float)(sayWordCount - (minuteWordCount * wordsPerMinute)) / wordsPerMinute)*60) ;
            System.out.println(String.format("estimated time at: %d words per minute %02d:%02d", wordsPerMinute, minuteWordCount,  remainingWordsPerSecond));
        }
        System.out.println("");
    }
}
