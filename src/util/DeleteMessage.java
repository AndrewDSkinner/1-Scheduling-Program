package util;

/**
 * Functional interface for a delete message
 */
public interface DeleteMessage {

    /**
     * Generates a delete message
     * @param i - an integer
     * @param s - a String
     * @return - Returns a string
     */
    String DeleteMessage(int i, String s);

}
