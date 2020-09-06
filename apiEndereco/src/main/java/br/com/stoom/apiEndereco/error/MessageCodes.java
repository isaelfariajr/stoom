package br.com.stoom.apiEndereco.error;

public class MessageCodes {
	
    // Internal Server Errors
    public static final String INTERNAL_SERVER_ERROR = "500.001";

    // Unprocessable Requests
    public static final String REQUIRED_FIELD = "412.001";

    //Not Found
    public static final String BEER_NOT_FOUND = "404.001";

    // Avoid instantiation
    private MessageCodes() {
    }

}
