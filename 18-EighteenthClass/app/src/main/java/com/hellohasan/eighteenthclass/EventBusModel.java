package com.hellohasan.eighteenthclass;

public class EventBusModel {
    private String eventTag;

    public EventBusModel(String eventTag) {
        this.eventTag = eventTag;
    }

    public boolean isTagMatched(String tag){
        return eventTag.equals(tag);
    }

    public String getEventTag(){
        return eventTag;
    }
}
