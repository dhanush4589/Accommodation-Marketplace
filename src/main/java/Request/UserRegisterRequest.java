package Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRegisterRequest {

    @NotNull(message = "first_name cannot be null")
    @NotBlank(message = "first_name cannot be blank")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z\\s'-]{1,49}$",
            message = "first name must start with a capital letter"
    )
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "last_name cannot be null")
    @NotBlank(message = "last_name cannot be blank")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z\\s'-]{1,49}$",
            message = "last name must start with a capital letter"
    )
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @Email(message = "email must be valid")
    @JsonProperty("email")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

