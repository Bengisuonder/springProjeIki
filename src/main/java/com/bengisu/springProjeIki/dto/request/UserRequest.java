package com.bengisu.springProjeIki.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest
{
    @NotBlank(message = "Kullanıcı adı boş bırakılamaz.")
    private String userName;
    @Email(message = "Geçersiz e-mail adresi.")
    private String email;
    @Size(min = 8, max = 15, message = "Şifreniz 8 ile 15 karakter arasında olmak zorundadır.")
    private String password;
}
