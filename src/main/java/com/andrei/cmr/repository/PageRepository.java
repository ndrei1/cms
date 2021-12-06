package com.andrei.cmr.repository;

import com.andrei.cmr.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

    Page findBySlug(String slug);

    List<Page> findAllByPublishedIsTrueOrderByPriority();

}
