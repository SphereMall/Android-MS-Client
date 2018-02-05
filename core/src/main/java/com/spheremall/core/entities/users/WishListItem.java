package com.spheremall.core.entities.users;

import com.spheremall.core.entities.products.Media;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.jsonapi.annotations.Type;
import com.spheremall.core.entities.Entity;

import java.util.List;

@Type("wishListItems")
public class WishListItem extends Entity {
    public int userId;
    public int productId;
    public String createDate;
    public String updateDate;

    public List<Product> products;
    public List<Media> images;
}
