package com.financetracker.repository;

import com.financetracker.entity.CsvUpload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CsvUploadRepository extends JpaRepository<CsvUpload, Long> {

    //find upload by uploadId and userId
    @Query("SELECT c FROM CsvUpload c WHERE c.id = :id AND c.user.id = :userId")
    Optional<CsvUpload> findUploadsByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    //find all uploads for a user
    @Query("SELECT c FROM CsvUpload c WHERE c.user.id = :userId ORDER BY c.uploadedAt DESC")
    Page<CsvUpload> findUploadsByUserId(@Param("userId") Long userId);

    //find uploads by their status
    @Query("SELECT c FROM CsvUpload c WHERE c.user.id = :userId AND c.status = :status ORDER BY c.uploadedAt DESC")
    List<CsvUpload> findUploadsByUserIdAndStatus(@Param("userId") Long userId, @Param("status") CsvUpload.UploadStatus status);

    //find uploads by account
    @Query("SELECT c FROM CsvUpload c WHERE c.account.id = :accountId AND c.user.id = :userId ORDER BY c.uploadedAt DESC")
    Page<CsvUpload> findUploadsByAccountIdAndUserId(@Param("accountId") Long accountId, @Param("userId") Long userId, Pageable pageable);
}
