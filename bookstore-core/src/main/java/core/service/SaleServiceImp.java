package core.service;
import core.model.BaseEntity;
import core.model.Books;
import core.model.Clients;
import core.model.Sales;
import core.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class SaleServiceImp implements SaleService {
    public static final Logger logger = LoggerFactory.getLogger(SaleServiceImp.class);

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientService clientServiceImp;

    @Autowired
    private BookService bookServiceImp;

    @Override
    @Transactional
    public Boolean buy(Long bookID, Long clientID) {
        logger.trace("buy:  method entered  ---- buy(bookID={}, clientID={}",bookID, clientID);
        if(!clientServiceImp.findOne(clientID).isPresent()){
            logger.trace("buy:  can not find client with ID={}", clientID);
            logger.trace("buy:  method finished  --- return value boolean=false");
            return false;
        }
        Clients oldClient = clientServiceImp.findOne(clientID).get();
        if(!bookServiceImp.findOne(bookID).isPresent()){
            logger.trace("buy:  can not find book with ID={}", bookID);
            logger.trace("buy:  method finished  --- return value boolean=false");
            return false;
        }
        Books book = bookServiceImp.findOne(bookID).get();

        Clients updatedClient = new Clients();
        updatedClient.setName(oldClient.getName());
        updatedClient.setId(clientID);
        updatedClient.setMoneySpent(oldClient.getMoneySpent() + book.getPrice());
        clientServiceImp.updateClient(updatedClient);
        Sales sales = new Sales(bookID,clientID);
        sales.setId(getNextId());
        saleRepository.save(sales);
        logger.trace("buy:  method finished  --- return value boolean=true");
        return true;
    }

    @Override
    public List<Sales> getAll() {
        logger.trace("getAll:  entered method  --- getAll()");
        List<Sales> sales = saleRepository.findAll();
        logger.trace("getAll: finished method --- return value list<sale>={}", sales);
        return sales;
    }

    private Long getNextId(){
        if(getAll().size() == 0)
            return 1L;
        Long nextID = getAll().stream()
                .map(BaseEntity::getId)
                .max(Comparator.naturalOrder()).get();
        return nextID+1;
    }
}
