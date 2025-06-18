package com.project.DocumentMIS.events;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class newUserAddedEvent extends ApplicationEvent {


    public newUserAddedEvent(Object source) {
        super(source);
    }

    public newUserAddedEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
