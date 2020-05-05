package com.thinkgem.jeesite.modules.akec.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("TaskService")
public class TaskService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SellproductService sellproductService;
    @Autowired
    private DealerService dealerService;
    @Autowired
    private ProductService productService;
   public void excute(){
        logger.info("run task begin");
       dealerService.tbtoday();
       productService.tbtoday();
       sellproductService.tbtoday();
       logger.info("run task end");
    }

}
