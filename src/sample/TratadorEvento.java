package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TratadorEvento implements EventHandler<ActionEvent> { // 1
    public void handle(ActionEvent evento) { // 2
        System.out.println("Evento tratado por uma classe externa");
    }
}