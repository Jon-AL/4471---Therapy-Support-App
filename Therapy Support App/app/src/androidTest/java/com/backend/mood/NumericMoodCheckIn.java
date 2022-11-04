/*
Author: Jonathan Alexander
 */
package com.backend.mood;


public class NumericMoodCheckIn {
    private long id;
    private String shortDescription;
    private String longDescription;
    private String colour;
    private int moodValue;
    private int daysInterval = 1; /*1 = every day; is default*/
    private int[] reminderTimes = new int[4]; /*the time(s) during the day; doesn't need to be more than once per hour. Only picking 1 per hour is sufficient. Doesn't need to be a dynamic array, 4 times per day should suffice*/

    public NumericMoodCheckIn(int moodInputValue) {
        moodValue = moodInputValue;
        sliderValueToDescription(moodInputValue);
        /*TODO: Connect with UI component*/
    }

    /**
     * Simple method to assign the correct values to local variables.
     * Yasin - you use these for the UI.
     *
     * @param value moodInputValue.
     */
    private void sliderValueToDescription(int value) {
        /*can't use switch in this architecture for whatever reason? used if instead*/
        switch (value) {
            case 0:
                shortDescription = "Severe Depression";
                longDescription = "Endless suicidal thoughts, no way out, no movement. Everything is bleak and it will always be like this";
                colour = "red"; /*"Alarm bells red"*/
                break;
            case 1:
                shortDescription = "Severe Depression";
                longDescription = "Feelings of hopelessness and guilt. Thoughts of suicide, little movement  and it feels impossible to do anything.";
                colour = "red"; /*"Alarm bells red"*/
                break;
            case 2:
                shortDescription = "Mild to Moderate Depression";
                longDescription = "Slow thinking, no appetite, need to be alone, excessive sleep or disturbed sleep. Everything feels like a struggle.";
                colour = "orange"; /*"warning orange"*/
                break;
            case 3:
                shortDescription = "Mild to Moderate Depression";
                longDescription = "Feelings of panic and anxiety, concentration difficult and memory poor, some comfort in routine.";
                colour = "orange"; /*"warning orange"*/
                break;
            case 4:
                shortDescription = "Balanced";
                longDescription = "Slight withdrawal from social situations, less concentration than usual, slight agitation.";
                colour = "green"; /*"positive green orange"*/
                break;
            case 5:
                shortDescription = "Balanced";
                longDescription = "Mood in balance, making good decisions. Life is going well and the outlook is good.";
                colour = "green"; /*"positive green orange"*/
                break;
            case 6:
                shortDescription = "Balanced";
                longDescription = "Self-esteem is good, optimistic, sociable and articulate. Making good decisions adn getting work done.";
                colour = "green"; /*"positive green orange"*/
                break;
            case 7:
                shortDescription = "Hypomania";
                longDescription = "Very productive, charming and talkative. Doing everything to excess (e.g.: phone calls, writing, smoking, tea).";
                colour = "orange"; /*"warning orange"*/
                break;
            case 8:
                shortDescription = "Hypomania";
                longDescription = "Inflated self-esteem, rapid thoughts and speech. Doing too many things at once and not finishing any tasks";
                colour = "orange"; /*"warning orange"*/
                break;
            case 9:
                shortDescription = "Mania";
                longDescription = "Lost touch with reality, incoherent, no sleep. Feeling paranoid and vindictive. Behaviour is reckless.";
                colour = "red"; /*"Alarm bells red"*/
                break;
            case 10:
                shortDescription = "Mania";
                longDescription = "Total loss of judgement, out-of-control spending, religious delusions and hallucinations.";
                colour = "red"; /*"Alarm bells red"*/
                break;
        }
    }


    private void pushCheckInNotification() {
        /*TODO: method to create a push notification, based on frequency of required self reporting tool*/
    }

    private void confirmDone() {

        /*TODO: UI Connection; listener event.*/
        /*TODO: update database: inventory=-1*/
        /*TODO: update database: confirm taken, dosage*/
    }

}
