package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.WrongQuestionAmountException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

@Service
public class ExaminerServiceImpl implements ExaminerService{

    private Random random;
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> randomQuestions = new HashSet<>();
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new WrongQuestionAmountException();
        }
        if (amount == questionService.getAll().size()) {
            return randomQuestions = questionService.getAll();
        }
        while (randomQuestions.size() < amount) {
            if (randomQuestions.contains(questionService.getRandomQuestion())) {
                continue;
            }
            randomQuestions.add(questionService.getRandomQuestion());
        }
        return randomQuestions;
    }
}
