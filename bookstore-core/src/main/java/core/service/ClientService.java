package core.service;

import core.model.Clients;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Boolean addClient(Clients client);

    Boolean removeClient(Long id);

    Boolean updateClient(Clients updatedClient);

    List<Clients> getAllClients();

    List<Clients> clientsSortedAlphabetically();

    List<Clients> clientsSortedByMoneySpent();

    List<Clients>  filterByName(String searchString);

    List<Clients> sortByNameAndID();

    Optional<Clients> findOne(Long id);
}
