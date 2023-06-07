package com.ll.tenmindaily.boundedContext.board.category;

import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
=======
import java.util.Optional;

>>>>>>> 3630690 (Nagiltae (#9))
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByInvestment(String investmentType);
}
