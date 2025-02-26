package co.alianza.demo.services.interfaces;

import java.util.List;

import co.alianza.demo.common.dtos.ClientDto;

public interface IClientService {

  /**
   * The function getAll() returns a list of ClientDto objects.
   * 
   * @return A List of ClientDto objects is being returned.
   */
  public List<ClientDto> getAll();

  /**
   * The function `getById` in Java returns a `ClientDto` object based on the provided `id`.
   * 
   * @param id The parameter "id" is of type Long and represents the unique identifier of a client.
   * @return A `ClientDto` object with the specified `id` is being returned.
   */
  public ClientDto getById(Long id);

  /**
   * The function creates a new client using the provided ClientDto object and returns the created
   * client.
   * 
   * @param clientDto The `create` method seems to be a method for creating a new client based on the
   * provided `ClientDto` object. The `clientDto` parameter is likely an object that contains the data
   * necessary to create a new client, such as the client's name, address, contact information, etc.
   * @return The method `create` is returning a `ClientDto` object.
   */
  public ClientDto create(ClientDto clientDto);

  /**
   * The function `update` in Java takes a `ClientDto` object as input and returns a boolean value
   * indicating whether the update was successful or not.
   * 
   * @param clientDto The `clientDto` parameter is likely an object of type `ClientDto`, which contains
   * data related to a client. This method `update` is expected to take this `clientDto` object as a
   * parameter and return a boolean value indicating whether the update operation was successful or
   * not.
   * @return The method is returning a boolean value, which indicates whether the update operation was
   * successful or not.
   */
  public boolean update(ClientDto clientDto);

  /**
   * The function `delete` takes a `Long` id as a parameter and is used to delete an item with the
   * specified id.
   * 
   * @param id The `delete` method takes a single parameter `id` of type `Long`. This `id` parameter is
   * used to identify the specific entity that needs to be deleted from the system or database.
   */
  public void delete(Long id);

}
