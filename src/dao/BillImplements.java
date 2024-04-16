package dao;

import UTIL.ConnectionDTB;
import entity.Account;
import entity.Bill;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillReceiptImplements implements BillDao {
    @Override
    public List<Bill> getAllBillRecept() {
        Connection connection = ConnectionDTB.openConnection();
        List<Bill> billList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT *from bill where bill_Type = 1");
            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
               Bill bill = new Bill();
                bill.setBillId(resultSet.getInt("bill_Id"));
                bill.setBillType(resultSet.getBoolean("bill_Type"));
                bill.setEmpIdCreated(resultSet.getInt("emp_Id_Created"));
                bill.setCreated(resultSet.getDate("created"));
                bill.setAuthDate(resultSet.getDate("auth_date"));
                bill.setBillStatus(resultSet.getByte("bill_Status"));
                bill.setBillCode(resultSet.getString("bill_Code"));
               billList.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDTB.closeConnection(connection);
        }
        return billList;
    }

    @Override
    public boolean addBillRecept(Bill bill) {
        Connection connection = ConnectionDTB.openConnection();
        String sql = "insert into bill(emp_Id_created,created,auth_Date,bill_Status,bill_code) values(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bill.getEmpIdCreated());
           statement.setDate(2,new Date(bill.getCreated().getTime()));
            statement.setDate(3,new Date(bill.getAuthDate().getTime()));
            statement.setByte(4,bill.getBillStatus());
            statement.setString(5,bill.getBillCode());
            int check = statement.executeUpdate();
            if (check>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDTB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public void updateBillRecept(Bill bill) {

    }

    @Override
    public void updateBillReceptStatus(Product productStatusUpdate) {

    }

    @Override
    public Product findByProductId(String productId) {
        return null;
    }

    @Override
    public List<Bill> searchBillReceptByBillID(int searchBillId) {
        return null;
    }
}
