package server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuditModel {
    @CreatedDate
    private LocalDateTime db_createdAt;

    @LastModifiedDate
    private LocalDateTime db_updatedAt;
}
