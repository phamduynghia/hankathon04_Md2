package ra.service;

import ra.business.entity.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IGenericService<Catalog, Integer> {
    private List<Catalog> catalogList = new ArrayList<>();

    @Override
    public List<Catalog> getAll() {
        return catalogList;
    }

    @Override
    public void save(Catalog catalog) {
        catalogList.add(catalog);
    }

    @Override
    public Catalog findById(Integer id) {
        return catalogList.stream().filter(c -> c.getCatalogId() == id).findFirst().orElse(null);
    }

    @Override
    public void delete(Integer id) {
        catalogList.removeIf(c -> c.getCatalogId() == id);
    }

    public boolean hasProductInCatalog(int catalogId) {
        // Giả sử có logic kiểm tra xem có sản phẩm trong danh mục hay không
        return false;
    }
}
