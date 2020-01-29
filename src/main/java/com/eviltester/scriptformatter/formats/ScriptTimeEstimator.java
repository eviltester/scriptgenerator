package com.eviltester.scriptformatter.formats;

import com.eviltester.scriptformatter.script.DoSayScript;
import com.eviltester.scriptformatter.script.ScriptSection;

public class ScriptTimeEstimator {

    public void showEstimates(final DoSayScript script) {

        int sayWordCount=0;

        for(ScriptSection section : script.getSections()){
            if(section.isSay()){
                String[] words = section.getText().replaceAll(String.format("%n"), " ").split(" ");
                sayWordCount += words.length;
            }
        }

        System.out.println(String.format("words: %d  " + "time: %d  video: %s",
                                            sayWordCount,
                                            sayWordCount/100,
                                            script.getName()));

        for(int wordsPerMinute = 100; wordsPerMinute<=150; wordsPerMinute+=10){
            final int minuteWordCount = sayWordCount / wordsPerMinute;
            int remainingWordsPerSecond = (int)(((float)(sayWordCount - (minuteWordCount * wordsPerMinute)) / wordsPerMinute)*60) ;
            System.out.println(String.format("estimated time at: %d words per minute %02d:%02d", wordsPerMinute, minuteWordCount,  remainingWordsPerSecond));
        }

        System.out.println("");
    }
}
