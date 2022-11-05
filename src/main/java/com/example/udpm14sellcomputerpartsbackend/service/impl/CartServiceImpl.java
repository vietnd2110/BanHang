package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.exception.BadRequestException;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.CartDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.CartEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ImageEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ProductEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.response.CartResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.CartRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ImagesRepository;
import com.example.udpm14sellcomputerpartsbackend.repository.ProductRepository;
import com.example.udpm14sellcomputerpartsbackend.security.CustomerDetailService;
import com.example.udpm14sellcomputerpartsbackend.service.CartService;
import com.example.udpm14sellcomputerpartsbackend.ultil.CurrentUserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private ImagesRepository imagesRepository;


    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CartEntity> getAllByUser(Long id) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        return cartRepository.findAllByUserId(uDetailService.getId());
    }

    @Override
    public List<CartEntity> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public CartResponse sumTotalPriceAndQuantity(Long id) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        CartResponse cartResponse = new CartResponse();
        cartResponse.setTotalAmount(cartRepository.sumPrice(uDetailService.getId()));
        cartResponse.setQuantityCart(cartRepository.countCart(uDetailService.getId()));
        return cartResponse;
    }

    @Override
    public CartDto addToCart(CartDto cartDto) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        if (uDetailService == null) {

        } else {//Su dung database de luu
            ProductEntity productEntity = productRepository.findById(cartDto.getProductId())
                    .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "product id not found: " + cartDto.getProductId()));
            CartEntity cart = cartRepository.findAllByUserIdAndProductId(uDetailService.getId(), cartDto.getProductId());
            List<ImageEntity> imageEntity = imagesRepository.getImageByProduct(cartDto.getProductId());
            if (cart == null) {
                cart = new CartEntity();
                cart.setUserId(uDetailService.getId());
                cart.setProductId(productEntity.getId());
                cart.setImage(imageEntity.get(0).getLink());
                cart.setPrice(productEntity.getPrice());
                Double price = Double.parseDouble(productEntity.getPrice().toString());
                cart.setTotal(BigDecimal.valueOf(price * 1));
                cart.setQuantity(1);
            } else {//Neu san pham da co trong database tang so luong them 1
                cart.setQuantity(cart.getQuantity() + 1);
                if (productEntity.getQuantity() < cart.getQuantity()) {
                    throw new BadRequestException("Bạn chỉ có thể mua tối đa :"+productEntity.getQuantity()+" của sản phẩm này");
                } else {
                    Double price = Double.parseDouble(cart.getPrice().toString());
                    cart.setTotal(BigDecimal.valueOf(price * cart.getQuantity()));
                }
            }
            return modelMapper.map(cartRepository.save(cart), CartDto.class);
        }
        return cartDto;
    }

    @Override
    public CartDto updateQuantity(Long idProduct, Integer quantity) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        //Su dung database de luu
        CartEntity cart = cartRepository.findAllByUserIdAndProductId(uDetailService.getId(), idProduct);
        if (cart == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "người dùng chưa có sản phẩm id: " + idProduct + " trong giỏ hàng");
        }
        ProductEntity findQuantity = productRepository.findById(idProduct).get();
        if (findQuantity.getQuantity() < quantity) {
            throw new BadRequestException("Bạn chỉ có thể mua tối đa :"+findQuantity.getQuantity()+" của sản phẩm này");
        }
        cart.setQuantity(quantity);
        Double price = Double.parseDouble(cart.getPrice().toString());
        cart.setTotal(BigDecimal.valueOf(price * cart.getQuantity()));
        return modelMapper.map(cartRepository.save(cart), CartDto.class);
    }

    @Override
    public void delete(Long id) {
        CustomerDetailService uDetailService = CurrentUserUtils.getCurrentUserUtils();
        CartEntity cart = cartRepository.findAllByUserIdAndProductId(uDetailService.getId(), id);
        if (cart == null) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Không tồn tại sản phẩm: " + id + " trong giỏ hàng");
        }
        cartRepository.deleteById(cart.getId());
    }
}
