package com.mycompany.transactionphaseeventlistenertest.control;

import com.mycompany.transactionphaseeventlistenertest.entity.MyEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class EventHandler {
    
    @Inject
    Event<MyEvent> event;    
    
    public void fireEvent() {
        event.fire(new MyEvent("Hi", false));        
    }
    
    public void fireEventWithException() {
        event.fire(new MyEvent("Hi", true));
    }
    
    public void doSomethingWithEvent(@Observes MyEvent event) {
        System.out.println("Got Event: " + event.getMessage());
        if (event.isException()) {
            throw new RuntimeException();
        }
    }
    
    public void afterSuccess(@Observes(during = TransactionPhase.AFTER_SUCCESS) MyEvent event) {
        System.out.println("AFTER SUCCESS");
    }
    
    public void afterFailure(@Observes(during = TransactionPhase.AFTER_FAILURE) MyEvent event) {
        System.out.println("AFTER FAILURE");
    }
    
    public void beforeCompletion(@Observes(during = TransactionPhase.BEFORE_COMPLETION) MyEvent event) {
        System.out.println("BEFORE COMPLETION");
    }
    
    public void afterCompletion(@Observes(during = TransactionPhase.AFTER_COMPLETION) MyEvent event) {
        System.out.println("AFTER COMPLETION");
    }
}
