package com.example.udpm14sellcomputerpartsbackend.model.dto.thongKe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeTrangThaiDonHang {
    private Integer soDonChoXacNhan;
    private Integer soDonDangXuLy;
    private Integer soDonDangVanChuyen;
    private Integer soDonDaGiao;
    private Integer soDonDaHuy;
    private Integer soDonDaHoanThanh;
}
