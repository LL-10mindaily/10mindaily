package com.ll.tenmindaily.boundedContext.board.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<String> getinvestmentType(){
        List<String> catgoryList =new ArrayList<>();
        List<Category> fCategory =  this.categoryRepository.findAll();
        for(Category c : fCategory){
            catgoryList.add(c.getInvestment());
        }
        return catgoryList;
    }

    public Category getCategory(String category) {

        Category cate= this.categoryRepository.findByInvestment(category);

        return  cate;
    }
}
