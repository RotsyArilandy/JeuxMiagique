package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exceptions.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganisateurService {
    @Autowired
    private SpectateurRepository spectateurRepository;
    @Autowired
    private OrganisateurRepository organisateurRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private ControleurRepository controleurRepository;
    @Autowired
    private DelegationRepository delegationRepository;
    @Autowired
    private EpreuveRepository epreuveRepository;
    @Autowired
    private InfrastructureRepository infrastructureRepository;
    @Autowired
    private ResultatRepository resultatRepository;

    // Afficher une délégation
    public Optional<Delegation> findDelegation(Long id) throws DelegationIntrouvableException {
        Optional<Delegation> d = delegationRepository.findById(id);
        if (d == null) {
            throw new DelegationIntrouvableException("La délégation que vous recherchez n'existe pas");
        }
        return d;
    }

    //Afficher toutes les délégations
    public List<Delegation> findAllDelegation() {
        return delegationRepository.findAll();
    }

    //Supprimer toutes les délégations
    public String deleteAllDelegation(String mail) throws MauvaisDroitsException {
        Organisateur o = organisateurRepository.findByMail(mail);
        if (o == null || o.getRole() != Role.ORGANISATEUR) {
            throw new MauvaisDroitsException("Il faut être un organisteur pour supprimer des délégations");
        } else {
            delegationRepository.deleteAll();
            return "Toutes les délégations sont bien supprimées";
        }
    }

    //Supprimer une délegation
    public String deleteDelegation(String mail, String nomDelegation) throws DelegationIntrouvableException, MauvaisDroitsException {
        Organisateur o = organisateurRepository.findByMail(mail);
        Delegation d = delegationRepository.findByNom(nomDelegation);
        if (d == null) {
            throw new DelegationIntrouvableException("La délégation que vous essayez de supprimer n'existe pas");
        } else if (o == null || o.getRole() != Role.ORGANISATEUR) {
            throw new MauvaisDroitsException("Il faut être un organisteur pour supprimer des délégations");
        } else {
            delegationRepository.delete(d);
            return "La délégation a bien été supprimée: " + nomDelegation;
        }
    }


    //Creer une delegation
    public Delegation saveDelegation(String nomDelegation, String mail) throws DelegationDejaExistantException, MauvaisDroitsException {
        Organisateur o = organisateurRepository.findByMail(mail);
        Delegation d = delegationRepository.findByNom(nomDelegation);
        if (o == null || o.getRole() != Role.ORGANISATEUR) {
            throw new MauvaisDroitsException("Il faut être un organisateur pour créer une délégation");
        }
        if (d != null) {
            throw new DelegationDejaExistantException("Le nom de la délégation que essayez de créer existe déjà pris: " + d.getNom());
        }
        return delegationRepository.save(new Delegation(nomDelegation, 0, 0, 0));
    }

    //Supprimer tous les participants
    public String deleteAllParticipant() {
        participantRepository.deleteAll();
        return "Tous les participants sont supprimés";
    }

    //Récupérer la liste de tous les organisateurs
    public List<Organisateur> findAllOrganisateur () {
        return organisateurRepository.findAll();
    }

    //Créer un participant
    public String saveParticipant(String mail, String nomP, String prenom, String mailP, String del) throws CompteDejaExistantException, MauvaisDroitsException, CompteIntrouvableException, DelegationIntrouvableException {
        Organisateur o1 = organisateurRepository.findByMail(mailP);
        Participant p = participantRepository.findByMail(mailP);
        Organisateur o = organisateurRepository.findByMail(mailP);
        Controleur c = controleurRepository.findByMail(mailP);
        Spectateur s = spectateurRepository.findByMail(mailP);
        Delegation d = delegationRepository.findByNom(del);
        {
            if (o1 == null || o1.getRole() != Role.ORGANISATEUR) {
                throw new MauvaisDroitsException("Il faut être un organisateur pour créer une délégation");
            }
            if (p == null) {
                if (s != null) {
                    throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Spectateur : " + mail);
                } else if (p != null) {
                    throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Participant : " + mail);
                } else if (c != null) {
                    throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Controleur : " + mail);
                } else if (d == null) {
                    throw new DelegationIntrouvableException("La délégation est introuvable");
                }
            }
            Participant part = new Participant(nomP, prenom, mail);
            part.setDelegation(d);
            participantRepository.save(part);
            return "Le participant a été créé :" + part;
        }
    }

    public Controleur saveControleur(String nom, String prenom, String mail) throws CompteDejaExistantException {
        Participant p = participantRepository.findByMail(mail);
        Organisateur o = organisateurRepository.findByMail(mail);
        Controleur c = controleurRepository.findByMail(mail);
        Spectateur s = spectateurRepository.findByMail(mail);
        if (c == null) {
            if (s != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Spectateur : " + mail);
            } else if (p != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Participant : " + mail);
            } else if (o != null) {
                throw new CompteDejaExistantException("Ce mail est déjà utilisé pour un compte Organisateur : " + mail);
            }
        }
        Controleur cont = new Controleur(nom, prenom, mail);
        return controleurRepository.save(cont);
    }

    //Créer un organisateur + vérification
    public Organisateur saveOrganisateur (Organisateur organisateur) throws CompteDejaExistantException {
        Organisateur o = organisateurRepository.findByMail(organisateur.getMail());
        if (o != null) {
            throw new CompteDejaExistantException("Cet organisateur existe déjà");
        }
        return organisateurRepository.save(organisateur);
    }



        /*N'est pas utilisé
        //Afficher un participant
        public Optional<Participant> findParticipant (Long id) throws CompteIntrouvableException {
            Optional<Participant> p = participantRepository.findById(id);
            if (p == null) {
                throw new CompteIntrouvableException("Le participant n'existe pas");
            }
            return p;
        }*/

        //Supprimer une participant par Id
        public String supprimerParticipant(String mail, Participant participant) throws CompteIntrouvableException{
            Participant p = participantRepository.findByMail(participant.getMail());
            if (p == null) {
                throw new CompteIntrouvableException("Le participant que vous essayez de supprimer n'existe pas");
            }
            participantRepository.deleteById(p.getId());
            return "Le participant " + p.getId() + " a été supprimé";
        }




        //Supprimer un organisateur par ID
        public String deleteOrganisationById (Long id) throws CompteIntrouvableException {
            Optional<Organisateur> o = organisateurRepository.findById(id);
            if (o == null) {
                throw new CompteIntrouvableException("Cet organisateur n'existe pas");
            }
            return "L'organisateur " + id + " a bien été supprimé";

        }




        //Ajouter une épreuve
        public String addEpreuve (String mail, String nomE, LocalDate dateE, String infrastructure, int nbPlaceDispo) throws
        EpreuveDejaExistantException, InfrastructureIntrouvableException, MauvaisDroitsException {
            Organisateur o = organisateurRepository.findByMail(mail);
            Epreuve e = epreuveRepository.findByName(nomE);
            Infrastructure i = infrastructureRepository.findByName(infrastructure);
            if (e != null) {
                throw new EpreuveDejaExistantException("L'épreuve que vous essayez de créer existe déjà");
            }
            if (i == null) {
                throw new InfrastructureIntrouvableException("L'infrastructure que vous renseignez est inconnu");
            }
            if ((o == null) || (o.getRole() != Role.ORGANISATEUR)) {
                throw new MauvaisDroitsException("Vous ne pouvez pas les droits pour créer/supprimer une épreuve");
            } else {
                Epreuve epreuve = new Epreuve(nomE);
                epreuve.setDateE(dateE);
                epreuve.setInfrastructure(infrastructure);
                epreuve.setNbPlaceDispo(nbPlaceDispo);
                epreuveRepository.save(epreuve);
                return "L'épreuve a été créé : " + epreuve;
            }
        }

        //Supprimer une épreuve
        public String deleteEpreuve (String mail, String nomEpreuve, LocalDate dateE, String infrastructure,
        int nbPlaceDispo) throws MauvaisDroitsException, EpreuveIntrouvableException {
            Organisateur o = organisateurRepository.findByMail(mail);
            Epreuve e = epreuveRepository.findByName(nomEpreuve);
            if (o.getRole() != Role.ORGANISATEUR) {
                throw new MauvaisDroitsException("Vous ne pouvez pas les droits pour créer/supprimer/modifier une épreuve");
            } else if (e == null) {
                throw new EpreuveIntrouvableException("L'épreuve que vous voulez supprimer n'existe pas");
            } else {
                epreuveRepository.delete(e);
            }
            return "L'épreuve a bien été supprimé : " + e;
        }

        //Supprimer toutes les épreuves + Sans vérification rôle
        public String deleteAllEpreuve () {
            epreuveRepository.deleteAll();
            return "Toutes les épreuves ont été supprimées";
        }


        //Modifier le nombre de participant max d'une épreuve
        public String updateNbParticipant(String mail, Long idE, int nbMax) throws
                EpreuveIntrouvableException, MauvaisDroitsException {
            Organisateur o = organisateurRepository.findByMail(mail);
            if (o == null || o.getRole() != Role.ORGANISATEUR) {
                throw new MauvaisDroitsException("Vous ne possédez pas les droits pour créer/supprimer/modifier une épreuve");
            }

            Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idE);
            if (!optionalEpreuve.isPresent()) {
                throw new EpreuveIntrouvableException("L'épreuve que vous voulez modifier n'existe pas");
            }

            Epreuve epreuve = optionalEpreuve.get();
            epreuve.setNbMax(nbMax);
            epreuveRepository.save(epreuve);

            return "Modification ok : " + epreuve;
        }

        //Modifier le nombre de billet max d'une épreuve
        public String updateNbBillet(String mail, Long idE, int nbMax) throws
                EpreuveIntrouvableException, MauvaisDroitsException {
            Organisateur o = organisateurRepository.findByMail(mail);
            if (o == null || o.getRole() != Role.ORGANISATEUR) {
                throw new MauvaisDroitsException("Vous ne possédez pas les droits pour créer/supprimer/modifier une épreuve");
            }

            Optional<Epreuve> optionalEpreuve = epreuveRepository.findById(idE);
            if (!optionalEpreuve.isPresent()) {
                throw new EpreuveIntrouvableException("L'épreuve que vous voulez modifier n'existe pas");
            }

            Epreuve epreuve = optionalEpreuve.get();
            epreuve.setNbMaxBillet(nbMax);
            epreuveRepository.save(epreuve);

            return "Modification ok : " + epreuve;
        }


    //Ajouter un résultat
        public String addResultat (String mail, Resultat resultat) throws MauvaisDroitsException {
            Organisateur o = organisateurRepository.findByMail(mail);
            if (o.getRole() != Role.ORGANISATEUR) {
                throw new MauvaisDroitsException("Vous ne pouvez pas les droits pour créer/supprimer/modifier une épreuve");
            } else {
                Participant p = participantRepository.findByMail(String.valueOf(participantRepository.findById(resultat.getIdP())));
                int nbOr = p.getDelegation().getNbOr();
                int nbArgent = p.getDelegation().getNbOr();
                int nbBronze = p.getDelegation().getNbOr();
                if (resultat.getPos() == 1) {
                    nbOr++;
                    p.getDelegation().setNbOr(nbOr);
                } else if (resultat.getPos() == 2) {
                    nbArgent++;
                    p.getDelegation().setNbOr(nbArgent);
                } else if (resultat.getPos() == 3) {
                    nbBronze++;
                    p.getDelegation().setNbOr(nbBronze);
                }
                resultatRepository.save(resultat);
                return "Le résultat a bien été inséré";
            }


           /* //Afficher les résultats
            public List<Delegation> classerDelegation () {
                resultatRepository.findAllByOrderByNomAsc()

            }

            */


        }

    public String setDate(String mail, String nomEpreuve, LocalDate date) throws MauvaisDroitsException, EpreuveIntrouvableException {
        Organisateur o = organisateurRepository.findByMail(mail);
        Optional<Epreuve> optionalEpreuve = Optional.ofNullable(epreuveRepository.findByName(nomEpreuve));
        if (o.getRole() != Role.ORGANISATEUR) {
            throw new MauvaisDroitsException("Vous ne pouvez pas les droits pour créer/supprimer/modifier une épreuve");
        } else if (!optionalEpreuve.isPresent()) {
                throw new EpreuveIntrouvableException("L'épreuve que vous voulez modifier n'existe pas");}
        else {
            Epreuve epreuve = optionalEpreuve.get();
            epreuve.setDateE(date);
            epreuveRepository.save(epreuve);

            return "Modification ok : " + epreuve;

        }

    }

    public void deleteOrganisateurById(Long id) {
            organisateurRepository.deleteById(id);

    }
}





