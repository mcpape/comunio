package com.example.comunio.service;

import com.example.comunio.entity.TableContainerEntity;
import com.example.comunio.repository.TableContainerEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TableContainerService {

    private final TableContainerEntityRepository tableContainerEntityRepository;

    public List<TableContainerEntity> removeAllAndResaveTableContainerEntities(List<String> scrapedResult) {
        removeAllTableContainerEntities();
        List<TableContainerEntity> entities = scrapedResult.stream()
                .map(result -> TableContainerEntity.builder()
                        .tableData(result)
                        .build())
                .toList();
        return tableContainerEntityRepository.saveAll(entities);
    }

    public void removeAllTableContainerEntities() {
        tableContainerEntityRepository.deleteAll();
    }

}
