package riccardogulin.u5d7.exceptions;

public class NotFoundException extends RuntimeException {

	public NotFoundException(int id) {
		super("Item with id " + id + " not found!");
	}

}
