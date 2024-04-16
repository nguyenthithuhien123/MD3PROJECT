package dao;

import entity.Bill;
import entity.Product;

import java.util.List;

public interface BillReceiptDao {
    List<Bill> getAllBillRecept();
    boolean addBillRecept(Bill bill);
    void updateBillRecept(Bill bill);
    void updateBillReceptStatus(Product productStatusUpdate);
    Product findByProductId(String productId);
    List<Bill> searchBillReceptByBillID(int searchBillId);
}
