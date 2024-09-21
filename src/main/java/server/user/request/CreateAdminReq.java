package server.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.user.model.Role;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminReq {
    private String name;
    private String email;
    private Role role;
}
