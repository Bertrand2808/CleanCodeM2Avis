package fr.esgi.avis.exception;

public class EditeurInexistantException extends RuntimeException {

    public EditeurInexistantException(String message) {
        super(message);
    }

    public EditeurInexistantException() {
        super();
    }

}
