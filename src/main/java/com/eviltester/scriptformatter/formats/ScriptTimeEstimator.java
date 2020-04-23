package com.eviltester.scriptformatter.formats;

import com.eviltester.scriptformatter.script.DoSayScript;
import com.eviltester.scriptformatter.script.ScriptSection;

public class ScriptTimeEstimator {

    public TimeEstimate calculateEstimates(final DoSayScript script) {

        int sayWordCount=0;

        for(ScriptSection section : script.getSections()){
            if(section.isSay()){
                String[] words = section.getText().replaceAll(String.format("%n"), " ").split(" ");
                sayWordCount += words.length;
            }
        }

        final TimeEstimate estimate = new TimeEstimate().
                                            forWords(sayWordCount).
                                            forText(script.getName()).
                                            atPath(script.getPath());

//        System.out.println(String.format("words: %d  " + "time: %d  video: %s",
//                                            sayWordCount,
//                                            estimate.atWPM(100).getMinutes(),
//                                            script.getName()));

        for(int wordsPerMinute = 100; wordsPerMinute<=150; wordsPerMinute+=10){
            TimeEstimate.WordsPerMinute wpm = estimate.atWPM(wordsPerMinute);
            //System.out.println(wpm.report());
        }

        //System.out.println("");

        return estimate;
    }
}
