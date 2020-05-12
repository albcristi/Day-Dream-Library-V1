package web.controller;

import core.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import web.converter.ClientConverter;
import web.dto.ClientDto;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientController {
    public static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<ClientDto> getAll(){
        logger.trace("getAll:  method entered --- getAll()");
        List<ClientDto>  clientsDto = clientService.getAllClients()
                .stream()
                .map(c->clientConverter.convertModelToDto(c))
                .collect(Collectors.toList());
        logger.trace("getAll:  method finished --- return value List={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Boolean saveClient(@RequestBody ClientDto clientDto){
        logger.trace("saveClient:   method entered  --- saveClient(clientDto={})",clientDto);
        Boolean result = clientService.addClient(clientConverter.convertDtoToModel(clientDto));
        logger.trace("saveClient:   method finished --- return value Boolean={}",result);
        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    public Boolean updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto){
        logger.trace("updateClient:  method entered --- updateClient(id={}, clientDto={})", id, clientDto);
        Boolean result = clientService.updateClient(clientConverter.convertDtoToModel(clientDto));
        logger.trace("updateClient:  method finished --- return value Boolean={}", result);
        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public Boolean removeClient(@PathVariable Long id){
        logger.trace("removeClient:  method entered --- removeClient(id={})", id);
        Boolean result = clientService.removeClient(id);
        logger.trace("removeClient:  method finished --- return value boolean={}",result);
        return result;
    }

    @RequestMapping(value = "/clients-sorted-alpha", method = RequestMethod.GET)
    public List<ClientDto> clientsSortedAlphabetically(){
        logger.trace("clientsSortedAlphabetically:  method entered --- no params");
        List<ClientDto> clientsDto = clientService.clientsSortedAlphabetically()
                .stream()
                .map(c->clientConverter.convertModelToDto(c))
                .collect(Collectors.toList());
        logger.trace("clientsSortedAlphabetically: method finished --- return value List={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/clients-sorted-money", method = RequestMethod.GET)
    public List<ClientDto> clientsSortedByMoney(){
        logger.trace("clientsSortedByMoney: method entered --- no params");
        List<ClientDto> clientsDto = clientService.clientsSortedByMoneySpent()
                .stream()
                .map(c->clientConverter.convertModelToDto(c))
                .collect(Collectors.toList());
        logger.trace("clientsSortedByMoney: method finished --- return value List={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/clients-sorted-name-id",method = RequestMethod.GET)
    public List<ClientDto> sortByNameAndId(){
        logger.trace("sortByNameById: method entered --- no params");
        List<ClientDto> clientsDto = clientService.sortByNameAndID()
                .stream()
                .map(c->clientConverter.convertModelToDto(c))
                .collect(Collectors.toList());
        logger.trace("sortByNameById: method finished --- return value List={}", clientsDto);
        return clientsDto;
    }

    @RequestMapping(value = "/clients-filter-name/{name}", method = RequestMethod.GET)
    public List<ClientDto> filterByName(@PathVariable String name){
        logger.trace("filterByName: method entered --- filterByName(name={})", name);
        List<ClientDto> clientsDto = clientService.filterByName(name)
                .stream()
                .map(c->clientConverter.convertModelToDto(c))
                .collect(Collectors.toList());
        logger.trace("filterByName: method finished --- return value List={}", clientsDto);
        return clientsDto;
    }

}
