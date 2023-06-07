package com.ll.tenmindaily.boundedContext.board.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByInvestment(String investmentType);
}