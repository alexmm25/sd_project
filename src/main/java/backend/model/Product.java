package backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long productID;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private String category;

    @Column
    private String description;

    @Column
    private Integer stock;

    @OneToOne
    @JoinColumn(name = "sponsorID")
    private Sponsor sponsor;

    @OneToMany(mappedBy = "product")
    private List<CartProduct> cartProducts = new ArrayList<>();

    public Product(String name,
                   Integer price,
                   String category,
                   String description,
                   Integer stock,
                   Sponsor sponsor) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.stock = stock;
        this.sponsor = sponsor;
    }

    public Product() {}

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductID() {
        return productID;
    }

    public Long getSponsorID() {
        return sponsor.getUserID();
    }

    public String getSponsorName() {
        return sponsor.getName();
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
