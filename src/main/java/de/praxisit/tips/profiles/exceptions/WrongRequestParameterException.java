package de.praxisit.tips.profiles.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception if a request parameter is missing or not valid.
 *
 * @author Bernd Kursawe (bernd.kursawe@praxisit.de)
 * @since 08.01.17
 */
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class WrongRequestParameterException extends RuntimeException {

    public WrongRequestParameterException() {
        super( );
    }

    public WrongRequestParameterException( final String parameterName ) {
        super( "Request parameter " + parameterName + " is missing or not valid" );
    }

    public WrongRequestParameterException( final String parameterName, Exception ex ) {
        super( "Request parameter " + parameterName + " is missing or not valid", ex );
    }

}
