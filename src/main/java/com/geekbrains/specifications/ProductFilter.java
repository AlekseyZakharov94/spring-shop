package com.geekbrains.specifications;

import com.geekbrains.entities.Product;
import com.geekbrains.repositories.ProductSpecifications;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;

    public ProductFilter(Map<String, String> map, List<String> categories) {
        this.spec = Specification.where(null);
        String minPrice = map.get("min_price");
        String maxPrice = map.get("max_price");
        String title = map.get("title");

        if (StringUtils.isNotEmpty(minPrice)) {
            int minPriceInteger = Integer.parseInt(minPrice);
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPriceInteger));
        }

        if (StringUtils.isNotEmpty(maxPrice)) {
            int maxPriceInteger = Integer.parseInt(maxPrice);
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPriceInteger));
        }

        if (StringUtils.isNotEmpty(title)) {
            spec = spec.and(ProductSpecifications.titleLike(title));
        }

        if (!CollectionUtils.isEmpty((categories))){
            Specification specCategories = null;
            for (String c: categories) {
                if (specCategories == null) {
                    specCategories = ProductSpecifications.categoryIs(c);
                } else {
                    specCategories = specCategories.or(ProductSpecifications.categoryIs(c));
                }
                spec = spec.and(specCategories);
            }
        }
    }
}
