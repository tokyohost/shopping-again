package com.scmpi.book.service;
import java.util.*;

import com.scmpi.book.entity.*;
public interface ProductService {
    public void deleteProduct(Integer id)throws Exception;
    public Product queryByName(String name)throws Exception;
    public Product queryById(Integer id)throws Exception;
    public List<Product> queryAll()throws Exception;
    public void updateProduct(Product p)throws Exception;
    //以下是productType方法
    public List<ProductType> getProductTypes()throws Exception;
    public List<Product> queryPdtsById(int typeid)throws Exception;
    public void updateProductType(ProductType pt)throws Exception;
    public void insertProductType(ProductType pt)throws Exception;
}
