package ru.clevertec.ecl.exeption;

public class EntityNotFoundExeption extends RuntimeException{

    public EntityNotFoundExeption(String message, Class<?> clazz) {
        super(message + " " + clazz.getSimpleName());
    }
}
