package by.example.bot.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(Long id){
        super("City not found with id: " + id);
    }

}

