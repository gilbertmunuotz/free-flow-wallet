package free_flow_wallet.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RegisterRequestDto {
    // Define expected types &annotations here
    @NotBlank(message = "Name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be Valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Pin is required")
    @Size(min = 4, message = "Pin must be at least 4 characters")
    private String pin; // hashed in backend
}

