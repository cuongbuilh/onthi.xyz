package com.wru.onthi.controller;

import com.wru.onthi.entity.*;
import com.wru.onthi.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    QuestionLevelService classroomService;

    @Autowired
    LessonService lessonService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ExamService examService;

    @Autowired
    NewsService newsService;

    @Autowired
    ResultService resultService;

    @Autowired
    QuestionService questionService;

    @GetMapping(value = {"/"})
    public String index(Model model){

        // get list class menu
        List<Classroom> listClass = classroomService.getAllClassroom();
        if(!listClass.isEmpty()){
            model.addAttribute("listClass",listClass);
        }
        //get list-subject
        List<Subject> listSubject = subjectService.getlistSubject().subList(0,8);
        if(!listSubject.isEmpty()){
            model.addAttribute("listSubject",listSubject);
        }
        // list class -slicker
        List<Classroom> listClassTH= classroomService.listClassBySchool(1);
        model.addAttribute("listClassTH",listClassTH);
        List<Classroom> listClassTHCS= classroomService.listClassBySchool(2);
        model.addAttribute("listClassTHCS",listClassTHCS);
        List<Classroom> listClassTHPT= classroomService.listClassBySchool(3);
        model.addAttribute("listClassTHPT",listClassTHPT);

        // get list-exam
        List<Exam> listNewExam = examService.getListExamOrderByViews();
        if(!listNewExam.isEmpty()){
            model.addAttribute("listNewExam",listNewExam.size()>5 ? listNewExam.subList(0,5) : listNewExam);
        }

        List<Exam> listExam= listNewExam.subList(0,3);
        if(!listExam.isEmpty()){
            model.addAttribute("listExam",listExam);
        }

        // get list-lesson
        List<Lesson> lessonList = lessonService.getListLessonOrderById().subList(0,5);
        if(!lessonList.isEmpty()){
            model.addAttribute("lessonList",lessonList);
        }
        // get news service
        List<News> listNews = newsService.getListNewsOrderByTime().subList(0,5);
        model.addAttribute("listNews",listNews);

        List<News> listnewStudy= newsService.getNewsStudy().subList(0,4) ;
        model.addAttribute("listnewStudy",listnewStudy);

        List<Result> listScore = resultService.getResultScoreDESC();
        List<Result> listScoreDesc= listScore.size() > 5 ? listScore.subList(0,5) : listScore;
        model.addAttribute("userScore",listScoreDesc);

        List<Question> listTopQuestion = questionService.getTopQuestion();
        model.addAttribute("listTopQuestion", listTopQuestion.size() >5? listTopQuestion.subList(0,5) : listTopQuestion);

        return "index";
    }
}
