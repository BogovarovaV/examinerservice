package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.exception.QuestionCollectionIsEmptyException;
import pro.sky.java.course2.examinerservice.exception.QuestionDoesNotExistException;
import pro.sky.java.course2.examinerservice.exception.QuestionExistsException;
import pro.sky.java.course2.examinerservice.exception.QuestionOrAnswerIsNullException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.course2.examinerservice.constants.JavaExamConstants.*;

class JavaQuestionServiceTest {

    private final JavaQuestionService out = new JavaQuestionService();
    private Question questionForTest;
    private Question questionForTest5;
    private final Set<Question> questionsForTest = new HashSet<>();

    public static Stream<Arguments> provideParamsForEmptyQATest() {
        return Stream.of(Arguments.of(EMPTY_QUESTION, EMPTY_ANSWER),
                        Arguments.of(EMPTY_QUESTION, ANSWER_1),
                        Arguments.of(QUESTION_1, EMPTY_ANSWER));
    }

    @BeforeEach
    public void createQuestionForTest() {
        questionForTest = new Question(QUESTION_1, ANSWER_1);
        questionForTest5 = new Question(QUESTION_5, ANSWER_5);
        questionsForTest.add(questionForTest5);
        out.add(QUESTION_5, ANSWER_5);
    }

    @Test
    void shouldAddQuestionAndAnswer() {
        out.add(QUESTION_1, ANSWER_1);
        Question actualQuestion = new Question(QUESTION_1, ANSWER_1);
        assertEquals(questionForTest, out.find(actualQuestion));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForEmptyQATest")
    void shouldThrowQuestionOrAnswerIsNullException(String q, String a) {
        assertThrows(QuestionOrAnswerIsNullException.class,
                ()-> out.add(q, a));
    }

    @Test
    void shouldThrowQuestionExistsException() {
        assertThrows(QuestionExistsException.class,
                ()-> out.add(QUESTION_5, ANSWER_5));
    }

    @Test
    void shouldAddQuestionLikeObject() {
        Question actualQuestion = new Question(QUESTION_1, ANSWER_1);
        out.add(questionForTest);
        assertEquals(questionForTest, out.find(actualQuestion));
    }

    @Test
    void shouldFindQuestion() {
        assertEquals(questionForTest5, out.find(questionForTest5));
    }

    @Test
    void shouldThrowQuestionDoesNotExistException() {
        assertThrows(QuestionDoesNotExistException.class,
                ()-> out.find(questionForTest));
    }

    @Test
    void shouldNotThrowExceptionAndRemoveQuestion() {
        assertDoesNotThrow(()-> out.remove(questionForTest5));
    }

    @Test
    void shouldThrowQuestionDoesNotExistExceptionWhenRemove() {
        assertThrows(QuestionDoesNotExistException.class,
                ()-> out.remove(questionForTest));
    }

    @Test
    void shouldGetAll() {
        assertEquals(questionsForTest, out.getAll());
    }

    @Test
    void shouldThrowQuestionCollectionIsEmptyException() {
        out.getAll().clear();
        assertThrows(QuestionCollectionIsEmptyException.class,
                out::getAll);
    }

    @Test
    void getRandomQuestion() {
        assertEquals(questionForTest5, out.getRandomQuestion());
    }
}