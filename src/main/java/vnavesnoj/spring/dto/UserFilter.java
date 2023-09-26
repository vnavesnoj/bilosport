package vnavesnoj.spring.dto;

import lombok.Builder;
import vnavesnoj.spring.database.entity.Role;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Builder
public class UserFilter {

    private String name;
    private LocalDate afterBirthDate;
    private LocalDate beforeBirthDate;
    private Role role;

    public String getName() {
        return this.name;
    }

    public LocalDate getAfterBirthDate() {
        return this.afterBirthDate;
    }

    public LocalDate getBeforeBirthDate() {
        return this.beforeBirthDate;
    }

    public Role getRole() {
        return this.role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAfterBirthDate(LocalDate afterBirthDate) {
        this.afterBirthDate = afterBirthDate;
    }

    public void setBeforeBirthDate(LocalDate beforeBirthDate) {
        this.beforeBirthDate = beforeBirthDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserFilter)) return false;
        final UserFilter other = (UserFilter) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$afterBirthDate = this.getAfterBirthDate();
        final Object other$afterBirthDate = other.getAfterBirthDate();
        if (this$afterBirthDate == null ? other$afterBirthDate != null : !this$afterBirthDate.equals(other$afterBirthDate))
            return false;
        final Object this$beforeBirthDate = this.getBeforeBirthDate();
        final Object other$beforeBirthDate = other.getBeforeBirthDate();
        if (this$beforeBirthDate == null ? other$beforeBirthDate != null : !this$beforeBirthDate.equals(other$beforeBirthDate))
            return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserFilter;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $afterBirthDate = this.getAfterBirthDate();
        result = result * PRIME + ($afterBirthDate == null ? 43 : $afterBirthDate.hashCode());
        final Object $beforeBirthDate = this.getBeforeBirthDate();
        result = result * PRIME + ($beforeBirthDate == null ? 43 : $beforeBirthDate.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        return result;
    }

    public String toString() {
        return "UserFilter(name=" + this.getName() + ", afterBirthDate=" + this.getAfterBirthDate() + ", beforeBirthDate=" + this.getBeforeBirthDate() + ", role=" + this.getRole() + ")";
    }
}
