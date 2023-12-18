package net.ausiasmarch.foxforumserver.api;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.ausiasmarch.foxforumserver.entity.UserEntity;
import net.ausiasmarch.foxforumserver.helper.JWTHelper;
import net.ausiasmarch.foxforumserver.repository.UserRepository;
import net.ausiasmarch.foxforumserver.service.EmailService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/reset")
public class ResetPasswordController {

    @Autowired
    private EmailService oEmailService;

    @Autowired
    private UserRepository oUserRepository;

    @PostMapping("/password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestBody) {
        String to = requestBody.get("to");
        System.out.println(to);

        Optional<UserEntity> oUser = oUserRepository.findByEmail(to);

        if (oUser.isPresent()) {
            // Generar un nuevo token JWT
            String resetToken = JWTHelper.generateJWT(oUser.get().getUsername());

            // Actualizar el token en la base de datos
            UserEntity userEntity = oUser.get();
            userEntity.setResetPasswordToken(resetToken);
            oUserRepository.save(userEntity);

            // Enviar el correo electrónico con el nuevo token
            oEmailService.sendEmail(to, resetToken);

            return ResponseEntity.ok("\"Verify email by the link sent on your email address\"");
        } else {
            // es mejor lanzar excepciones personalizadas
            return new ResponseEntity<>("\"Error al encontrar el usuario\"", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/new")
    public ResponseEntity<String> newPassword(@RequestParam("token") String token,
            @RequestBody Map<String, String> requestBody) {
        String password = requestBody.get("password");
        String confirmPassword = requestBody.get("confirmPassword");

        if (password.equals(confirmPassword)) {
            // Obtener el nombre de usuario del token
            Optional<UserEntity> oUserFromDatabase = oUserRepository.findByResetPasswordToken(token);

            if (oUserFromDatabase.isPresent()) {
                // Actualizar la contraseña del usuario
                UserEntity oUserToUpdate = oUserFromDatabase.get();
                oUserToUpdate.setPassword(password);
                oUserRepository.save(oUserToUpdate);

                return ResponseEntity.ok("\"Password updated successfully\"");
            } else {
                return ResponseEntity.ok("\"User not found\"");
            }
        } else {
            return ResponseEntity.ok("\"Passwords do not match\"");
        }

    }

}
