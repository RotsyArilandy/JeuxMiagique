package com.example.demo.service;

import com.example.demo.exceptions.CompteIntrouvableException;
import com.example.demo.entity.*;
import com.example.demo.repository.ControleurRepository;
import com.example.demo.repository.OrganisateurRepository;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.SpectateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompteService {

    private ParticipantRepository participantRepository;
    private SpectateurRepository spectateurRepository;
    private OrganisateurRepository organisateurRepository;
    private ControleurRepository controleurRepository;


    public Login login(String mail) throws CompteIntrouvableException {
        Participant p = participantRepository.findByMail(mail);
        Organisateur o = organisateurRepository.findByMail(mail);
        Controleur c = controleurRepository.findByMail(mail);
        Spectateur s = spectateurRepository.findByMail(mail);
        Login login = new Login();
        login.setMail(mail);

        if (p != null){
            login.setRole(Role.PARTICIPANT);
            return login;
        }
        else if ( o != null ){
            login.setRole(Role.ORGANISATEUR);
            return login;
        }
        else if ( c != null ) {
            login.setRole(Role.CONTROLLEUR);
            return login;
        }
        else if ( s != null ) {
            login.setRole(Role.SPECTATEUR);
            return login;
        }
        else{
            throw new CompteIntrouvableException("Aucun compte lié à votre mail n'a été trouvé : "+ mail);
        }
    }

}
