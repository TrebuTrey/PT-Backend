package mlb.teams.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private String resource;
	private String field;
	private Object value;
	
	public ResourceNotFoundException(String resource, String field, Object value) {
		super(String.format("%s was not found with the identifer \"%s\": %s", resource, field, value));
		this.resource = resource;
		this.field = field;
		this.value = value;
	
	}
	
}
