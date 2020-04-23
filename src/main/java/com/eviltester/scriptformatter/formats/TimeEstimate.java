package com.eviltester.scriptformatter.formats;

import java.util.ArrayList;
import java.util.List;

public class TimeEstimate {
    private int wordCount;
    private String textName;
    List<WordsPerMinute> estimates= new ArrayList<>();
    private String path;

    public TimeEstimate forWords(final int wordCount) {
        this.wordCount = wordCount;
        return this;
    }

    public TimeEstimate forText(final String name) {
        this.textName = name;
        return this;
    }

    public WordsPerMinute atWPM(final int wpm) {
        final WordsPerMinute estimate = new WordsPerMinute(wpm, wordCount);
        estimates.add(estimate);
        return estimate;
    }

    public String report(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Time Estimates For %d words in %s%n", wordCount, textName));
        for(WordsPerMinute estimate : estimates){
            sb.append(String.format("%s%n", estimate.report()));
        }
        return sb.toString();
    }

    public String getText() {
        return textName;
    }

    public TimeEstimate atPath(final String path) {
        this.path = path;
        return this;
    }

    public String getPath() {
        return path;
    }

    public class WordsPerMinute {
        private final int minuteWordCount;
        private final int remainingWordsPerSecond;
        private final int wordCount;
        private final int wpm;

        public WordsPerMinute(int rateOfWordsPerMinute, int wordCount) {
            this.wordCount = wordCount;
            this.wpm = rateOfWordsPerMinute;
            this.minuteWordCount = this.wordCount / rateOfWordsPerMinute;
            this.remainingWordsPerSecond = (int)(((float)(this.wordCount - (minuteWordCount * wpm)) / wpm)*60) ;
        }

        public int getMinutes() {
            return minuteWordCount;
        }

        public int getSeconds() {
            return this.remainingWordsPerSecond;
        }

        public String report() {
            return String.format("estimated time at: %d words per minute %02d:%02d",
                    wpm, getMinutes(),  getSeconds());
        }
    }
}
