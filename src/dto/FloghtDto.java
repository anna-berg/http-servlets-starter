package dto;

import java.util.Objects;

public class FloghtDto {

    private final Long id;
    private final String description;

    public FloghtDto(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloghtDto floghtDto = (FloghtDto) o;
        return Objects.equals(id, floghtDto.id) && Objects.equals(description, floghtDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "FloghtDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
