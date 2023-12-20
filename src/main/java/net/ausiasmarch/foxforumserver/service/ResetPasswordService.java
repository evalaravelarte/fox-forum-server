package net.ausiasmarch.foxforumserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import net.ausiasmarch.foxforumserver.entity.UserEntity;
import net.ausiasmarch.foxforumserver.exception.ResourceNotFoundException;
import net.ausiasmarch.foxforumserver.helper.JWTHelper;
import net.ausiasmarch.foxforumserver.repository.UserRepository;

@Service
public class ResetPasswordService {

    @Autowired
    private JavaMailSender javaMailSender;

     @Autowired
    private UserRepository oUserRepository;


    public String sendEmail(String to) {
        Optional<UserEntity> oUser = oUserRepository.findByEmail(to);

        if (oUser.isPresent()) {
            // Generar un nuevo token JWT
            String resetToken = JWTHelper.generateJWT(oUser.get().getUsername());

            // Enviar el correo electrónico con el nuevo token
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Reset Password");
            message.setText("Haz clic en el siguiente enlace para restablecer tu contraseña: " +
            "http://localhost:4200/admin/user/new-password/" + resetToken);
            javaMailSender.send(message);

            // Actualizar el token en la base de datos
            UserEntity userEntity = oUser.get();
            userEntity.setResetPasswordToken(resetToken);
            oUserRepository.save(userEntity);

            return "\"Verify email by the link sent on your email address\"";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
       
    }


    public String updatePassword(String token, String password, String confirmPassword) {

        if (password.equals(confirmPassword)) {
            // Obtener el nombre de usuario del token
            Optional<UserEntity> oUserFromDatabase = oUserRepository.findByResetPasswordToken(token);

            if (oUserFromDatabase.isPresent()) {
                // Actualizar la contraseña del usuario
                UserEntity oUserToUpdate = oUserFromDatabase.get();
                oUserToUpdate.setPassword(password);
                oUserToUpdate.setResetPasswordToken(null);
                oUserRepository.save(oUserToUpdate);

                return "\"Password updated successfully\"";
            } else {
                return "\"User not found\"";
            }
        } else {
            return "\"Passwords do not match\"";
        }
    }

   

}
