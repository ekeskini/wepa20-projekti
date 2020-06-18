/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wepa20.entities.Account;
import wepa20.repositories.AccountRepository;
import wepa20.entities.Recommendation;
import wepa20.repositories.RecommendationRepository;
import wepa20.entities.Skill;
import wepa20.repositories.SkillRepository;

/**
 *
 * @author Elias Keski-Nisula
 */
@Service
@Transactional
public class ProfileBuilderService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private RecommendationRepository recommendationRepository;
    
    public Account getAccountById(Long accountid) {
        return accountRepository.getOne(accountid);
    }
    
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    
    public Skill getSkillById(Long skillid) {
        return skillRepository.getOne(skillid);
    }
    
    public void saveNewRecommendation(Recommendation recommendation) {
        recommendationRepository.save(recommendation);
    }

    public void saveNewSkill(Skill skill) {
        skillRepository.save(skill);
    }
}
