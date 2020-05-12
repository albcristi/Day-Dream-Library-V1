package web.controller;

import core.model.Books;
import core.model.Clients;
import core.service.BookService;
import core.service.ClientService;
import core.service.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.converter.SaleConverter;
import web.dto.SaleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class SaleController {
    public static final Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleConverter saleConverter;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BookService bookService;


    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public List<SaleDto> getSales(){
        logger.trace("getSales:  method entered --- no params");
        List<SaleDto> salesDto = saleService.getAll()
                .stream()
                .map(s->saleConverter.convertModelToDto(s))
                .collect(Collectors.toList());
        salesDto
                .forEach(
                        sale -> {
                            Optional<Clients>  cl = clientService.findOne(sale.getClientid());
                            Optional<Books> bk = bookService.findOne(sale.getBookid());
                            sale.setAuthorName(bk.get().getAuthor());
                            sale.setBookName(bk.get().getTitle());
                            sale.setClientName(cl.get().getName());
                        }
                );
        logger.trace("getSales: method finished --- return value List={}",salesDto);
        return salesDto;
    }

    @RequestMapping(value = "/sales", method = RequestMethod.POST)
    public Boolean saveSale(@RequestBody ArrayList<Long> data){
        Long bookID = data.get(0);
        Long clientID = data.get(1);
        logger.trace("saveSale: method entered --- bookID={}, clientID={}",bookID, clientID);
        Boolean result = saleService.buy(bookID, clientID);
        logger.trace("saveSale: method finished --- return value Boolean={}", result);
        return result;
    }
}
