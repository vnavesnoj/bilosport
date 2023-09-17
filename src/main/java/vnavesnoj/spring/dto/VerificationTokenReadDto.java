package vnavesnoj.spring.dto;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public final class VerificationTokenReadDto implements BaseTokenReadDto {

    private final Long id;

    private final String token;

    private final LocalDateTime createdAt;

    private final UserReadDto user;

    public VerificationTokenReadDto(Long id, String token, LocalDateTime createdAt, UserReadDto user) {
        this.id = id;
        this.token = token;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public String getToken() {
        return this.token;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public UserReadDto getUser() {
        return this.user;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof VerificationTokenReadDto)) return false;
        final VerificationTokenReadDto other = (VerificationTokenReadDto) o;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$token = this.getToken();
        final Object other$token = other.getToken();
        if (this$token == null ? other$token != null : !this$token.equals(other$token)) return false;
        final Object this$createdAt = this.getCreatedAt();
        final Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $token = this.getToken();
        result = result * PRIME + ($token == null ? 43 : $token.hashCode());
        final Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        return result;
    }

    public String toString() {
        return "VerificationTokenReadDto(id=" + this.getId() + ", token=" + this.getToken() + ", createdAt=" + this.getCreatedAt() + ", user=" + this.getUser() + ")";
    }
}
