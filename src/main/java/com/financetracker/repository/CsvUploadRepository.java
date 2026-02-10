package com.financetracker.repository;

import com.financetracker.entity.CsvUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvUploadRepository extends JpaRepository<CsvUpload, Long> {
}
