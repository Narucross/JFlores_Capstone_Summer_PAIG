package AIHawker.CommunicationContracts;

/**
 * Basically the initial contract between the AIHawker and an individual AI Brain,
 * this simply states that a Hawker is able to get a response or can expect a compatible response
 * from an AI Personality
 * User: jflores
 * Date: 7/23/13
 * Time: 10:24 AM
 */
public interface HawkToBrainComp {
    /**
     * Every kind of personality will have a designated response in the form of IDK(I don't know)
     * This method allows the Hawker can use this method and an equals method to the response to determine
     * if the AI didn't have a response available for the User.
     * @return returns the IDK response of the Personality
     */
    public String getIDKReply();

    /**
     * Send a conventional grammar into this method and receive a response from the AI Personality
     * @param conventional_Grammar to be determined
     */
    public String requestResponce(String conventional_Grammar);
}//end of interface