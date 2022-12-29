package com.example.udpm14sellcomputerpartsbackend.service;

import java.io.ByteArrayInputStream;

public interface ExportService {
    ByteArrayInputStream exportPdfOrder(Long idOrder);
}
