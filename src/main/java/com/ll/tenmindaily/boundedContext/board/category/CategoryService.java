package com.ll.tenmindaily.boundedContext.board.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


<<<<<<< HEAD
    public List<String> getinvestmentType() { //카테고리 이름들 String 으로 가져오기
        List<String> catgoryList = new ArrayList<>();
        List<Category> fCategory = this.categoryRepository.findAll();
        for (Category c : fCategory) {
=======
    public List<String> getinvestmentType(){ //카테고리 이름들 String 으로 가져오기
        List<String> catgoryList =new ArrayList<>();
        List<Category> fCategory =  this.categoryRepository.findAll();
        for(Category c : fCategory){
>>>>>>> 3630690 (Nagiltae (#9))
            catgoryList.add(c.getInvestment());
        }
        return catgoryList;
    }

    public Category getCategory(String category) { //특정 카테고리 가져오기
        return this.categoryRepository.findByInvestment(category);
    }

<<<<<<< HEAD
    public List<Category> getAllCategory() {
=======
    public List<Category> getAllCategory(){
>>>>>>> 3630690 (Nagiltae (#9))
        return categoryRepository.findAll();
    }
}
