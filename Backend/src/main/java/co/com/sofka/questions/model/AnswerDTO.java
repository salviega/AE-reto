package co.com.sofka.questions.model;


import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public class AnswerDTO {
    @NotBlank(message = "There must be an id for this answer")
    private String userId;
    @NotBlank
    private String questionId;
    @NotBlank
    private String answer;
    private Integer position;
    private Instant created;
    private Instant updated;


    public AnswerDTO() {

    }

    public AnswerDTO(@NotBlank String questionId,
                     @NotBlank String userId,
                     @NotBlank String answer,
                     @NotBlank Instant created) {
        this.userId = userId;
        this.questionId = questionId;
        this.answer = answer;
        this.created = created;
    }

    public Instant created() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant updated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public Integer position() {
        return Optional.ofNullable(position).orElse(1);
    }

    public void setPosition(Integer position) {
        this.position = position;
    }


    public String userId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String questionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String answer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return Objects.equals(userId, answerDTO.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "AnswerDTO {" +
                "userId='" + userId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                ", position=" + position +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}