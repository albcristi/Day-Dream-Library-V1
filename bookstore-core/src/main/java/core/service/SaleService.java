package core.service;

import core.model.Sales;

import java.util.List;

public interface SaleService{

    Boolean buy(Long bookID, Long clientID);

    List<Sales> getAll();
}
