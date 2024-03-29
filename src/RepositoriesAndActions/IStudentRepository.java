package RepositoriesAndActions;

import CalculationsValidations.AgeValidationException;
import CalculationsValidations.IdValidationException;
import CalculationsValidations.ValidationException;

public interface IStudentRepository<T> {
    void add(T obj) throws ValidationException;

    void delete(String deletedId) throws IdValidationException;

    void retrieveByAge(String age) throws AgeValidationException;

    void listByLastName();

    void listByDateOfBirth();
}
