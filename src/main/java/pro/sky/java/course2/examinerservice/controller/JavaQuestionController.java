package pro.sky.java.course2.examinerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.JavaQuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping
    public Collection<Question> getAllQuestions() {
        return javaQuestionService.getAll();
    }

    @GetMapping("/add")
    public String addQuestion(@RequestParam String q, @RequestParam String a) {
        javaQuestionService.add(q, a);
        return "Вопрос [" + q + "] с ответом [" + a + "] успешно добавлены.";
    }

    @GetMapping("/remove")
    public String removeQuestion(@RequestParam String q, @RequestParam String a) {
        javaQuestionService.remove(new Question(q, a));
        return "Вопрос [" + q + "] с ответом [" + a + "] успешно удалены.";
    }

    @GetMapping("/find")
    public String findQuestion(@RequestParam String q, @RequestParam String a) {
        javaQuestionService.find(new Question(q, a));
        return "Вопрос [" + q + "] с ответом [" + a + "] найден.";
    }

}
