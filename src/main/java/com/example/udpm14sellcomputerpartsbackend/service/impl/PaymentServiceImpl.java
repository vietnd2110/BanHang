package com.example.udpm14sellcomputerpartsbackend.service.impl;

import com.example.udpm14sellcomputerpartsbackend.contants.FolderContants;
import com.example.udpm14sellcomputerpartsbackend.exception.NotFoundException;
import com.example.udpm14sellcomputerpartsbackend.model.dto.PaymentDto;
import com.example.udpm14sellcomputerpartsbackend.model.entity.ChipEntity;
import com.example.udpm14sellcomputerpartsbackend.model.entity.PaymentEntity;
import com.example.udpm14sellcomputerpartsbackend.repository.PaymentRepository;
import com.example.udpm14sellcomputerpartsbackend.service.PaymentService;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryServiceImpl cloudinaryService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper,CloudinaryServiceImpl cloudinaryService){
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    public List<PaymentDto> paymentEntityList(){
        List<PaymentEntity> list = paymentRepository.findAll();
        return list.stream().map(paymentEntity -> modelMapper.map(paymentEntity,PaymentDto.class)).collect(Collectors.toList());
    }

    @Override
    public PaymentDto getOnePayment(Long id){
        PaymentEntity findById = paymentRepository.findById(id)
                .orElseThrow(() ->new NotFoundException(HttpStatus.NOT_FOUND.value(),"Payment id not found:" + id));
        return modelMapper.map(paymentRepository.findById(id),PaymentDto.class);
    }

    @Override
    public PaymentDto create(PaymentDto paymentDto, MultipartFile file){

        PaymentEntity paymentEntity = modelMapper.map(paymentDto,PaymentEntity.class);
        String images = null;
        if (file != null) {
            images = cloudinaryService.uploadImage(file, FolderContants.PAYMENT_IMAGES_FOLDER);
        } else {
            images = "https://res.cloudinary.com/ducnd1306/image/upload/v1663338173/sample.jpg";
        }
        paymentEntity.setImage(images);

        return modelMapper.map(paymentRepository.save(paymentEntity),PaymentDto.class);
    }

    @Override
    public PaymentDto update(Long id, PaymentDto paymentDto, MultipartFile file){
        PaymentEntity findById = paymentRepository.findById(id)
                .orElseThrow(() ->new NotFoundException(HttpStatus.NOT_FOUND.value(),"Payment id not found:" + id));

        PaymentEntity paymentEntity = modelMapper.map(paymentDto,PaymentEntity.class);
        String images = null;
        if (file != null) {
            images = cloudinaryService.uploadImage(file, FolderContants.PAYMENT_IMAGES_FOLDER);
        } else {
            images = "https://res.cloudinary.com/ducnd1306/image/upload/v1663338173/sample.jpg";
        }
        paymentEntity.setId(id);
        paymentEntity.setImage(images);

        return modelMapper.map(paymentRepository.save(paymentEntity),PaymentDto.class);
    }

    @Override
    public void delete(Long id){
        PaymentEntity findById = paymentRepository.findById(id)
                .orElseThrow(() ->new NotFoundException(HttpStatus.NOT_FOUND.value(),"Payment id not found:" + id));
        paymentRepository.deleteById(id);
    }



}
