package ra.run;

import ra.business.entity.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args) {
        CatalogService catalogService = new CatalogService();
        ProductService productService = new ProductService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("**************************BASIC-MENU**************************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Chọn chức năng: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    catalogManagement(catalogService, productService, scanner);
                    break;
                case 2:
                    productManagement(productService, catalogService, scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void catalogManagement(CatalogService catalogService, ProductService productService, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("********************CATALOG-MANAGEMENT********************");
            System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.println("2. Hiển thị thông tin tất cả các danh mục");
            System.out.println("3. Sửa tên danh mục theo mã danh mục");
            System.out.println("4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
            System.out.println("5. Quay lại");
            System.out.println("Chọn chức năng: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng danh mục cần thêm: ");
                    int catalogCount = scanner.nextInt();
                    scanner.nextLine(); // Bỏ qua ký tự xuống dòng
                    for (int i = 0; i < catalogCount; i++) {
                        System.out.print("Nhập ID danh mục: ");
                        int catalogId = scanner.nextInt();
                        scanner.nextLine(); // Bỏ qua ký tự xuống dòng
                        System.out.print("Nhập tên danh mục: ");
                        String catalogName = scanner.nextLine();
                        System.out.print("Nhập mô tả danh mục: ");
                        String descriptions = scanner.nextLine();
                        catalogService.save(new Catalog(catalogId, catalogName, descriptions));
                    }
                    break;
                case 2:
                    List<Catalog> catalogList = catalogService.getAll();
                    for (Catalog catalog : catalogList) {
                        System.out.println(catalog);
                    }
                    break;
                case 3:
                    System.out.print("Nhập mã danh mục cần sửa: ");
                    int catalogIdToEdit = scanner.nextInt();
                    scanner.nextLine(); // Bỏ qua ký tự xuống dòng
                    Catalog catalogToEdit = catalogService.findById(catalogIdToEdit);
                    if (catalogToEdit != null) {
                        System.out.print("Nhập tên danh mục mới: ");
                        String newCatalogName = scanner.nextLine();
                        catalogToEdit.setCatalogName(newCatalogName);
                        System.out.println("Cập nhật thành công!");
                    } else {
                        System.out.println("Danh mục không tồn tại.");
                    }
                    break;
                case 4:
                    System.out.print("Nhập mã danh mục cần xóa: ");
                    int catalogIdToDelete = scanner.nextInt();
                    if (!productService.getAll().stream().anyMatch(p -> p.getCatalog().getCatalogId() == catalogIdToDelete)) {
                        catalogService.delete(catalogIdToDelete);
                        System.out.println("Xóa thành công!");
                    } else {
                        System.out.println("Không thể xóa danh mục có sản phẩm.");
                    }
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void productManagement(ProductService productService, CatalogService catalogService, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("********************PRODUCT-MANAGEMENT********************");
            System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("4. Xóa sản phẩm theo mã");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Thay đổi thông tin của sản phẩm theo mã");
            System.out.println("7. Quay lại");
            System.out.println("Chọn chức năng: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng sản phẩm cần thêm: ");
                    int productCount = scanner.nextInt();
                    scanner.nextLine(); // Bỏ qua ký tự xuống dòng
                    for (int i = 0; i < productCount; i++) {
                        System.out.print("Nhập mã sản phẩm: ");
                        String productId = scanner.nextLine();
                        System.out.print("Nhập tên sản phẩm: ");
                        String productName = scanner.nextLine();
                        System.out.print("Nhập giá sản phẩm: ");
                        double productPrice = scanner.nextDouble();
                        scanner.nextLine(); // Bỏ qua ký tự xuống dòng
                        System.out.print("Nhập mô tả sản phẩm: ");
                        String description = scanner.nextLine();
                        System.out.print("Nhập số lượng tồn kho: ");
                        int stock = scanner.nextInt();
                        System.out.print("Nhập mã danh mục: ");
                        int catalogId = scanner.nextInt();
                        Catalog catalog = catalogService.findById(catalogId);
                        if (catalog != null) {
                            productService.save(new Product(productId, productName, productPrice, description, stock, catalog));
                        } else {
                            System.out.println("Danh mục không tồn tại.");
                        }
                    }
                    break;
                case 2:
                    List<Product> productList = productService.getAll();
                    for (Product product : productList) {
                        System.out.println(product);
                    }
                    break;
                case 3:
                    productService.sortProductsByPriceDesc();
                    System.out.println("Sản phẩm đã được sắp xếp theo giá giảm dần.");
                    break;
                case 4:
                    System.out.print("Nhập mã sản phẩm cần xóa: ");
                    String productIdToDelete = scanner.next();
                    productService.delete(productIdToDelete);
                    System.out.println("Xóa thành công!");
                    break;
                case 5:
                    System.out.print("Nhập tên sản phẩm cần tìm: ");
                    scanner.nextLine();
                    String productNameToSearch = scanner.nextLine();
                    List<Product> foundProducts = productService.findByName(productNameToSearch);
                    for (Product product : foundProducts) {
                        System.out.println(product);
                    }
                    break;
                case 6:
                    System.out.print("Nhập mã sản phẩm cần sửa: ");
                    scanner.nextLine();
                    String productIdToEdit = scanner.nextLine();
                    Product productToEdit = productService.findById(productIdToEdit);
                    if (productToEdit != null) {
                        System.out.print("Nhập tên sản phẩm mới: ");
                        String newProductName = scanner.nextLine();
                        productToEdit.setProductName(newProductName);
                        System.out.print("Nhập giá sản phẩm mới: ");
                        double newProductPrice = scanner.nextDouble();
                        productToEdit.setProductPrice(newProductPrice);
                        System.out.print("Nhập số lượng tồn kho mới: ");
                        int newStock = scanner.nextInt();
                        productToEdit.setStock(newStock);
                        System.out.println("Cập nhật sản phẩm thành công!");
                    } else {
                        System.out.println("Sản phẩm không tồn tại.");
                    }
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
