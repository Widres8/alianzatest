import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '@env/environment';
import { Client } from '@shared/models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ClientsService {
  private readonly _http = inject(HttpClient);
  private readonly baseUrl = environment.apiUrl;

  getClients(username: string | null): Observable<Client[]> {
    const param = username ? `?username=${username}` : '';
    return this._http.get<Client[]>(`${this.baseUrl}/clients${param}`);
  }

  /**
   * The function `getClientById` retrieves a client by their ID using an HTTP GET request.
   * @param {number} id - The `id` parameter in the `getClientById` function is a number that
   * represents the unique identifier of the client you want to retrieve from the server.
   * @returns An Observable of type Client is being returned.
   */
  getClientById(id: number): Observable<Client> {
    return this._http.get<Client>(`${this.baseUrl}/clients/${id}`);
  }

  /**
   * The function `createClient` sends a POST request to the server to create a new client and returns
   * an Observable of the created client.
   * @param {Client} client - The `createClient` function takes a `Client` object as a parameter. This
   * `Client` object likely contains information about a client such as their name, contact details,
   * and any other relevant information needed to create a new client record.
   * @returns An Observable of type Client is being returned.
   */
  createClient(client: Client): Observable<Client> {
    return this._http.post<Client>(`${this.baseUrl}/clients`, client);
  }

  /**
   * The function `updateClient` sends a PUT request to update a client using the provided client data.
   * @param {Client} client - The `client` parameter in the `updateClient` function represents the
   * client object that you want to update. This object contains the information of the client that you
   * want to update in your system.
   * @returns An Observable of type Client is being returned.
   */
  updateClient(client: Client): Observable<Client> {
    return this._http.put<Client>(`${this.baseUrl}/clients`, client);
  }

  /**
   * The `deleteClient` function sends a DELETE request to the server to delete a client with the
   * specified ID.
   * @param {number} id - The `id` parameter in the `deleteClient` method is a number that represents
   * the unique identifier of the client that you want to delete from the server. This identifier is
   * used to specify which client should be removed from the database when the HTTP DELETE request is
   * sent to the server.
   * @returns An Observable of type boolean is being returned.
   */
  deleteClient(id: number): Observable<boolean> {
    return this._http.delete<boolean>(`${this.baseUrl}/clients/${id}`);
  }
}
