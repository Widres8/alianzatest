package co.alianza.demo.common.dtos;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ClientDto {
  private Long id;

  @NotBlank(message = "El usuario no puede estar vacío")
  @Size(min = 3, max = 10, message = "El usuario debe tener entre 3 y 10 caracteres")
  private String username;

  @NotBlank(message = "El nombre no puede estar vacío")
  @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
  private String name;

  @Email(message = "El correo electrónico debe ser válido")
  @NotBlank(message = "El correo electrónico es obligatorio")
  private String email;

  @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe tener 10 dígitos")
  private String phone;

  private Date startDate;
  private Date endDate;
}
