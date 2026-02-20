package com.financetracker.dto.response;

import com.financetracker.entity.CsvUpload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvUploadResponse {

    private Long id;
    private String fileName;
    private Long accountId;
    private String accountName;
    private Long fileSize;
    private Integer totalRows;
    private Integer importedRows;
    private Integer failedRows;
    private CsvUpload.UploadStatus status;
    private String errorMessage;
    private LocalDateTime uploadedAt;
    private LocalDateTime processedAt;
    private List<ImportError> errors;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImportError {
        private Integer row;
        private String error;
    }
}
