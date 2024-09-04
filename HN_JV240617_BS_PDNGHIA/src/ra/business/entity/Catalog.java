package ra.business.entity;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String descriptions;

    // Constructor
    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        setCatalogName(catalogName);
        setDescriptions(descriptions);
    }

    // Getter và Setter
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        if (catalogName == null || catalogName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên danh mục không được để trống");
        }
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        if (descriptions == null || descriptions.trim().isEmpty()) {
            throw new IllegalArgumentException("Mô tả không được để trống");
        }
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return "Catalog ID: " + catalogId + ", Name: " + catalogName + ", Description: " + descriptions;
    }
}
