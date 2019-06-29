package com.evan.os.service.impl.impl1;

import com.evan.os.dao.OrderDao;
import com.evan.os.dao.ProductDao;
import com.evan.os.entity.Order;
import com.evan.os.entity.Product;
import com.evan.os.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.Date;

@Service
public class orderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    public void addOrder(Order order) {
        order.setCreateTime(new Date());
        order.setStatus("代付款");

        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {
            orderDao.insert(order);
            Product product = productDao.select(order.getProductsId());
            product.setStock(product.getStock()-order.getNumber());
            productDao.update(product);
            transactionManager.commit(transactionStatus);
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(transactionStatus);
        }
    }
}
