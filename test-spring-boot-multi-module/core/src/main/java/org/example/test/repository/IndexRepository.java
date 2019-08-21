package org.example.test.repository;

import org.example.test.domain.Index;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexRepository extends PagingAndSortingRepository<Index, Long> {

}
