package com.wru.onthi.services;

import com.wru.onthi.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    void save(Result result);
    Optional<Result> findById(Integer id);
    Optional<Result> checkResultExist(Integer timeout, Integer userId, Integer ExamId,Integer status);
    Page<Result> getListResultExam(Pageable pageable);
    Page<Result> searchResultExam(String username, String examCode, Pageable pageable);
    void deleteResult(Result result);
    List<Result> getResultScoreDESC();
    long countResult();
    Page<Result> getListResultExamByUserId(Integer userId, Pageable pageable);
}
