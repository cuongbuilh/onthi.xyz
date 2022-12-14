package com.wru.onthi.repository;

import com.wru.onthi.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query(value = "select * from question where exam_id=?1 ", nativeQuery = true)
    List<Question> getListQuestionByExamId(Integer examId);

    @Query(value = "select qs  from Question qs " +
            "join ExamQuestion eq on qs.id= eq.questionExam.id " +
            "join Exam ex on ex.id= eq.examQuestion.id " +
            "and ex.id=?1")
    Page<Question> getPageQuestionByExamId(Integer examId, Pageable pageable);

    @Query(value = "select qs  from Question qs " +
            "join ExamQuestion eq on qs.id= eq.questionExam.id " +
            "join Exam ex on ex.id= eq.examQuestion.id " +
            "and ex.id=?1")
    Page<Question> getPageQuestionByClassAndSubject(Integer classId, Integer subjectId, Pageable pageable);


    @Query(value = "select q from Question q " +
            "where q.question_subject.id= ?1 " +
            "and q.question_classroom.id= ?2")
    Page<Question> getPageQuestionBySubjectAndClass(Integer subjectId, Integer classId, Pageable pageable);

    @Query(value = "select q from Question q " +
            "where q.question_subject.id= ?1 " +
            "and q.question_classroom.id= ?2")
    List<Question> getListQuestionBySubjectAndClass(Integer subjectId, Integer classId);

    @Query(value = "select distinct  q from Question q order by q.id desc ")
    List<Question> findTop();
}
