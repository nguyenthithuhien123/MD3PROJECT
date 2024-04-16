package program;

import dao.ProductDao;
import dao.ProductImplements;
import entity.Product;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws ParseException {
        productMenu();
    }

    public static void showMenuProduct() {
        System.out.println("******************PRODUCT MANAGEMENT****************");
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Thêm mới sản phẩm");
        System.out.println("3. Cập nhật sản phẩm");
        System.out.println("4. Tìm kiếm sản phẩm");
        System.out.println("5. Cập nhật trạng thái sản phẩm");
        System.out.println("6. Thoát");
    }

    public static void productMenu() {
        Scanner scanner = new Scanner(System.in);
        ProductDao productDao = new ProductImplements();
        boolean check = true;
        do {
            showMenuProduct();
            System.out.println("Mời bạn chọn!");
            try {
                int chose = Integer.parseInt(scanner.nextLine());
                switch (chose) {
                    case 1:
                        showProductList(productDao);
                        break;
                    case 2:
                        addProduct(scanner, productDao);
                        break;
                    case 3:
                        updateProductById(scanner, productDao);
                        break;
                    case 4:
                        searchProductByName(scanner, productDao);
                        break;
                    case 5:
                        updateProductStatusById(scanner, productDao);
                        break;
                    case 6:
                        check = false;
                        break;
                    default:
                        System.err.println("Mời bạn nhập từ 1-6");
                }
            } catch (Exception e) {
                System.err.println("Mời bạn nhập số!");
            }
        } while (check);
    }

    public static void showProductList(ProductDao productDao) {
        List<Product> productList = productDao.getAllProduct();
        for (Product product : productList) {
            product.showProduct();
        }
    }

    public static void addProduct(Scanner scanner, ProductDao productDao) {
        Product product = new Product();
        System.out.println("Mời bạn nhập thông tin sản phẩm");
        product.inputData(scanner, productDao.getAllProduct());
        productDao.addProduct(product);
        if (productDao.addProduct(product)) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Thêm mới thất bại");
        }
    }

    public static void updateProductById(Scanner scanner, ProductDao productDao) {
        System.out.println("Mời bạn nhập Id cần update");
        String idUpdate = scanner.nextLine();
        Product productUpdate = productDao.findByProductId(idUpdate);
        if (productUpdate == null) {
            System.out.println("Không có sản phẩm nào có Id là " + idUpdate);
        } else {
            System.out.println("Sản phẩm bạn muốn cập nhật là:");
            productUpdate.showProduct();
            productUpdate.updateProductById(scanner, productDao.getAllProduct());
            productDao.updateProduct(productUpdate);
        }
    }

    public static void searchProductByName(Scanner scanner, ProductDao productDao) {
        System.out.println("Nhập từ khóa bạn muốn tìm kiếm");
        String productNameSearch = scanner.nextLine();
        List<Product> productListSearched = productDao.searchByProductName(productNameSearch);
        int count = 0;
        for (Product product1 : productListSearched) {
            product1.showProduct();
            count++;
        }
        if (count == 0) {
            System.out.println("Không có sản phẩm nào phù hợp với từ khóa " + productNameSearch);
        }
    }

    public static void updateProductStatusById(Scanner scanner, ProductDao productDao) {
        System.out.println("Mời bạn nhập Id cần update trạng thái sản phẩm");
        String idUpdateProductStatus = scanner.nextLine();
        Product productStatusUpdate = productDao.findByProductId(idUpdateProductStatus);
        if (productStatusUpdate == null) {
            System.out.println("Không có sản phẩm nào có Id là " + idUpdateProductStatus);
        } else {
            System.out.println("Sản phẩm bạn muốn cập nhật là:");
            productStatusUpdate.showProduct();
            productStatusUpdate.inputProductStatus(scanner);
            productDao.updateProductStatus(productStatusUpdate);
        }
    }
}
