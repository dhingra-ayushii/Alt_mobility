package server.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.user.model.FleetUser;
import server.user.model.Role;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String id;
    private String name;
    private String email;
    private Role role;

    public UserResponse(FleetUser user) {
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
