package com.evan.os.service.impl.impl2;

import com.evan.os.dao.OrderDao;
import com.evan.os.dao.ProductDao;
import com.evan.os.entity.Order;
import com.evan.os.entity.Product;
import com.evan.os.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;

@Service
public class orderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void addOrder(final Order order) {
        order.setCreateTime(new Date());
        order.setStatus("代付款");

       transactionTemplate.execute(new TransactionCallback() {
           public Object doInTransaction(TransactionStatus transactionStatus) {
               try {
                   orderDao.insert(order);
                   Product product = productDao.select(order.getProductsId());
                   product.setStock(product.getStock()-order.getNumber());
                   productDao.update(product);
               }catch (Exception e){
                   e.printStackTrace();
                   transactionStatus.setRollbackOnly();
               }
               return null;
           }
       });
    }
}
